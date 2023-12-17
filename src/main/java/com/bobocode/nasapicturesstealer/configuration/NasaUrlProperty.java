package com.bobocode.nasapicturesstealer.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@PropertySource("classpath:nasa.properties")
public class NasaUrlProperty {

    private final Environment env;

    public String url() {
        return env.getProperty("nasa.url");
    }

    public String apiKey() {
        return env.getProperty("nasa.apiKey");
    }

}
