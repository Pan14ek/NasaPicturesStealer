package com.bobocode.nasapicturesstealer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NasaPicturesStealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaPicturesStealerApplication.class, args);
    }

}
