package com.bobocode.nasapicturesstealer.service.model;

import lombok.Builder;

@Builder
public record NasaPicture(int id, String imgSrc, String earthDate, NasaCamera camera) {
}
