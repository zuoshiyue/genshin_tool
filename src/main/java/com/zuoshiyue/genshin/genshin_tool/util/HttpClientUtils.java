package com.zuoshiyue.genshin.genshin_tool.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lupengfei
 * @date 2022/8/3 20:14
 * @desc
 **/
public class HttpClientUtils {
    private static CloseableHttpClient httpclient = null;

    /**
     * monitor Thread
     */
    private static ScheduledExecutorService monitorExecutor;

    /**
     * 保活连接策略
     */
    private static ConnectionKeepAliveStrategy keepAliveStrategy;


    /**
     * 保活时间
     */
    private static int keepAlive = 30;

    /**
     * 这里就直接默认固定了,因为以下三个参数在新建的method中仍然可以重新配置并被覆盖.
     * ms毫秒,从池中获取链接超时时间
     */
    static final int connectionRequestTimeout = 5000;


    /**
     * ms毫秒,建立链接超时时间
     */
    static final int connectTimeout = 5000;


    /**
     * ms毫秒,读取超时时间
     */
    static final int socketTimeout = 30000;


    /**
     * 总配置,主要涉及是以下两个参数,如果要作调整没有用到properties会比较后麻烦,但鉴于一经粘贴,随处可用的特点,就不再做依赖性配置化处理了.
     * 而且这个参数同一家公司基本不会变动.
     * 最大总并发,很重要的参数
     */
    static final int maxTotal = 500;
    /**
     * 每路并发,很重要的参数
     */
    static final int maxPerRoute = 100;

    /**
     * 正常情况这里应该配成MAP或LIST
     * 细化配置参数,用来对每路参数做精细化处理,可以管控各ip的流量,比如默认配置请求baidu:80端口最大100个并发链接,
     * <p>
     * 每个细化配置之ip(不重要,在特殊场景很有用)
     */
    static final String detailHostName = "localhost";
    /**
     * 每个细化配置之port(不重要,在特殊场景很有用)
     */
    static final int detailPort = 80;
    /**
     * 每个细化配置之最大并发数(不重要,在特殊场景很有用)
     */
    static final int detailMaxPerRoute = 100;

    /**
     * 初始化参数
     */
    static {
        initParam();
    }

    private static void initParam() {
        keepAliveStrategy = getKeepAliveStrategy();
    }

    private static ConnectionKeepAliveStrategy getKeepAliveStrategy() {
        ConnectionKeepAliveStrategy keepAliveStrategy = (response, context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase
                        (param)) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return keepAlive * 1000;
        };
        return keepAliveStrategy;
    }

    public static CloseableHttpClient getHttpClient() {
        if (null == httpclient) {
            synchronized (HttpClientUtils.class) {
                if (null == httpclient) {
                    httpclient = init();
                }
            }
        }
        return httpclient;
    }

    /**
     * 链接池初始化 这里最重要的一点理解就是. 让CloseableHttpClient 一直活在池的世界里, 但是HttpPost却一直用完就消掉.
     * 这样可以让链接一直保持着.
     */
    private static CloseableHttpClient init() {
        CloseableHttpClient newHttpclient;

        // 设置连接池
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = getSSLSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        cm.setMaxTotal(maxTotal);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(maxPerRoute);

        // 细化配置开始,其实这里用Map或List的for循环来配置每个链接,在特殊场景很有用.
        // 将每个路由基础的连接做特殊化配置,一般用不着
        HttpHost httpHost = new HttpHost(detailHostName, detailPort);
        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), detailMaxPerRoute);
        // 细化配置结束

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = (exception, executionCount, context) -> {
            if (executionCount >= 2) {// 如果已经重试了2次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            return !(request instanceof HttpEntityEnclosingRequest);
        };

        // 开启监控线程,对异常和空闲线程进行关闭
        monitorExecutor = Executors.newScheduledThreadPool(1);
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //关闭异常连接
                cm.closeExpiredConnections();
                //关闭空闲的连接
                cm.closeIdleConnections(connectTimeout, TimeUnit.SECONDS);
            }
        }, 30, 30, TimeUnit.SECONDS);

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        newHttpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).setRetryHandler(httpRequestRetryHandler).setKeepAliveStrategy(keepAliveStrategy).build();
        return newHttpclient;
    }

    /**
     * 构建sslSocketFactory
     *
     * @return sslFactory
     */
    private static LayeredConnectionSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
            return new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> String doGet(String url, Map<String, T> param, Header[] headers) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = getHttpClient();

        String resultString = "";
        CloseableHttpResponse response = null;
        HttpGet httpGet = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key).toString());
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            httpGet = new HttpGet(uri);
            if (Objects.nonNull(headers) && headers.length > 0) {
                httpGet.setHeaders(headers);
            }

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
        } finally {
            try {
                if (null != httpGet) {
                    httpGet.releaseConnection();
                }
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static <T> String doGet(String url, Map<String, T> param) {
        return doGet(url, param, null);
    }

    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    public static <T> String doPost(String url, Map<String, T> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = null;
        try {
            // 创建Http Post请求
            httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
        } finally {
            try {
                if (null != httpPost) {
                    httpPost.releaseConnection();
                }
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        String resultString = "";
        HttpPost httpPost = null;
        try {
            // 创建Http Post请求
            httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚
        } finally {
            try {
                if (null != httpPost) {
                    httpPost.releaseConnection();
                }
                EntityUtils.consume(response.getEntity());
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
}
