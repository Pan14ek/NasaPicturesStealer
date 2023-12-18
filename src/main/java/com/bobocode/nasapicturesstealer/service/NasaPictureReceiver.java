package com.bobocode.nasapicturesstealer.service;

import com.bobocode.nasapicturesstealer.configuration.NasaUrlProperty;
import com.bobocode.nasapicturesstealer.service.model.NasaCamera;
import com.bobocode.nasapicturesstealer.service.model.NasaPicture;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class NasaPictureReceiver {

    private final RestTemplate restTemplate;
    private final NasaUrlProperty nasaUrlProperty;

    @Cacheable(value = "receiveAllPictures", key = "#sol")
    public List<NasaPicture> receiveAllPictures(int sol) {
        String url = getNasaUrl(sol);

        var nodeOptional = Optional.ofNullable(restTemplate.getForObject(url, JsonNode.class));

        if (nodeOptional.isEmpty()) {
            return new ArrayList<>();
        }

        return createPictures(nodeOptional.get());
    }

    private String getNasaUrl(int sol) {

        return UriComponentsBuilder.fromHttpUrl(nasaUrlProperty.url())
                .queryParam("sol", sol)
                .queryParam("api_key", nasaUrlProperty.apiKey())
                .toUriString();
    }

    private List<NasaPicture> createPictures(JsonNode node) {
        return StreamSupport.stream(node.get("photos").spliterator(), true)
                .parallel()
                .map(value -> NasaPicture.builder()
                        .id(value.get("id").asInt())
                        .imgSrc(value.get("img_src").asText())
                        .earthDate(value.get("earth_date").asText())
                        .camera(createNasaCamera(value.get("camera")))
                        .build()
                )
                .toList();
    }

    private NasaCamera createNasaCamera(JsonNode node) {
        return NasaCamera.builder()
                .id(node.get("id").asInt())
                .name(node.get("name").asText())
                .fullName(node.get("full_name").asText())
                .build();
    }

}
