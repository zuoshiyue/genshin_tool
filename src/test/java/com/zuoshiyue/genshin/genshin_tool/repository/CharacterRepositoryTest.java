package com.zuoshiyue.genshin.genshin_tool.repository;

import com.zuoshiyue.genshin.genshin_tool.util.JsonUtil;
import com.zuoshiyue.genshin.genshin_tool.vo.CharacterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CharacterRepositoryTest {

    @Resource
    private CharacterRepository characterRepository;

    @Test
    void getCharacter() {
        CharacterResponse character = characterRepository.getCharacter("137236712", "ltoken=29OMqli7lvl3y3uNEMvYty7aQxSRfSoDLwWUX8TR; ltuid=229525194; _MHYUUID=2a7d4048-db68-46c4-8a6f-2f74eb082d76; _ga=GA1.2.2099929056.1642861448; _gat=1; _gid=GA1.2.381631720.1659746287; .thumbcache_a5f2da7236017eb7e922ea0d742741d5=h/7tz00RB4gmBEt+pHpwQq2TXWAZh37aPjXuZgTwrgZJdVPNupcibHNLbVuZAoVzjITTUC/ToKf1BEU7nV7Jug%3D%3D; CNZZDATA1275023096=1800741304-1642860363-https%253A%252F%252Fbbs.mihoyo.com%252F%7C1659782394; account_id=229525194; cookie_token=kWSmCBzTcuzJ1I5xv5tBHEUtXNYolgDnBoeIBbe2; smidV2=202208060838086d3971b12db17cd4204b9adc99a5a8e1007bfe33191b2a9a0; UM_distinctid=18235613270d54-0d467e8751b5278-2a6d4933-505c8-18235613271e69; mi18nLang=zh-cn; _ga_KPXDK6J368=GS1.1.1653129004.2.0.1653129005.0; _ga_R8CG4VZ69C=GS1.1.1650861504.5.1.1650861522.0; _ga_6ZKT513CTT=GS1.1.1647677266.1.1.1647677315.0; _ga_XR5VD06Z8Y=GS1.1.1643680794.8.0.1643680803.0");
        System.out.println(JsonUtil.toJson(character));

    }
}