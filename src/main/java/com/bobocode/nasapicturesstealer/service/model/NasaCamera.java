package com.bobocode.nasapicturesstealer.service.model;

import lombok.Builder;

@Builder
public record NasaCamera(int id, String name, String fullName) {
}
