package com.bobocode.nasapicturesstealer.service;

import com.bobocode.nasapicturesstealer.persistance.entity.Camera;
import com.bobocode.nasapicturesstealer.persistance.repository.CameraRepository;
import com.bobocode.nasapicturesstealer.service.model.NasaCamera;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CameraService {

    private final CameraRepository cameraRepository;

    @Transactional
    public Camera save(NasaCamera nasaCamera) {
        Camera camera = new Camera();
        camera.setNasaId(nasaCamera.id());
        camera.setName(nasaCamera.name());

        return cameraRepository.save(camera);
    }

    public Optional<Camera> getByNasaId(int nasaId) {
        return cameraRepository.findByNasaId(nasaId);
    }

}
