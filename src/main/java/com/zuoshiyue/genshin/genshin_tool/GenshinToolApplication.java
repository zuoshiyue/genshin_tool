package com.zuoshiyue.genshin.genshin_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan({"com.zuoshiyue.genshin.genshin_tool"})
public class GenshinToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenshinToolApplication.class, args);
    }

}
