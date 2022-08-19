package com.zuoshiyue.genshin.genshin_tool.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import static com.zuoshiyue.genshin.genshin_tool.util.DSUtil.getDS;

/**
 * @author zuoshyiue
 */
public abstract class BaseRepository {
    public static String getHttpResponse(String allConfigUrl, String roleId, String cookies) {
        BufferedReader in = null;
        StringBuffer result;
        try {
            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("DS", getDS(roleId));
            /**
             # 1:  ios
             # 2:  android
             # 4:  pc web
             # 5:  mobile web
             */
            connection.setRequestProperty("x-rpc-client_type", "5");
            connection.setRequestProperty("x-rpc-app_version", "2.33.1");
            connection.setRequestProperty("x-rpc-channel", "baidu");
            connection.setRequestProperty("Referer", "https://webstatic.mihoyo.com/");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/103.0.5060.134");

            connection.setRequestProperty("Cookie", cookies);
            connection.connect();
            result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static String postHttpResponse(String allConfigUrl, String roleId, String cookies) {
        BufferedReader in = null;
        StringBuffer result;
        try {
            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = null;

            if (connection instanceof HttpURLConnection){
                httpURLConnection = (HttpURLConnection) connection;
            }

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setRequestProperty("DS", getDS(roleId));
            /**
             # 1:  ios
             # 2:  android
             # 4:  pc web
             # 5:  mobile web
             */
            httpURLConnection.setRequestProperty("x-rpc-client_type", "5");
            httpURLConnection.setRequestProperty("x-rpc-app_version", "2.33.1");
            httpURLConnection.setRequestProperty("x-rpc-channel", "baidu");
            httpURLConnection.setRequestProperty("Referer", "https://webstatic.mihoyo.com/");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1 Edg/103.0.5060.134");

            httpURLConnection.setRequestProperty("Cookie", cookies);
            httpURLConnection.connect();
            result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
