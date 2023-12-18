package com.bobocode.nasapicturesstealer.service;

import com.bobocode.nasapicturesstealer.persistance.entity.Camera;
import com.bobocode.nasapicturesstealer.persistance.entity.Picture;
import com.bobocode.nasapicturesstealer.service.model.NasaCamera;
import com.bobocode.nasapicturesstealer.service.model.NasaPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PictureStealer {

    private final NasaPictureReceiver nasaPictureReceiver;
    private final CameraService cameraService;
    private final PictureService pictureService;

    @Transactional
    public void stealNasaPictures(int sol) {
        List<NasaPicture> nasaPictures = nasaPictureReceiver.receiveAllPictures(sol);
        nasaPictures.forEach(this::savePicture);
    }

    private void savePicture(NasaPicture nasaPicture) {
        Optional<Picture> optionalPicture = pictureService.getByNasaId(nasaPicture.id());

        if (optionalPicture.isPresent()) {
            return;
        }

        Camera camera = getCamera(nasaPicture);

        pictureService.save(nasaPicture, camera);
    }

    private Camera getCamera(NasaPicture nasaPicture) {
        NasaCamera nasaCamera = nasaPicture.camera();

        Optional<Camera> cameraOptional = cameraService.getByNasaId(nasaCamera.id());

        return cameraOptional.orElseGet(() -> cameraService.save(nasaCamera));
    }

}
