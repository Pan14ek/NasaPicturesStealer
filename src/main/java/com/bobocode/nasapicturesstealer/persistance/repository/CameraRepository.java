package com.bobocode.nasapicturesstealer.persistance.repository;

import com.bobocode.nasapicturesstealer.persistance.entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera, Long> {

    Optional<Camera> findByNasaId(int nasaId);
}