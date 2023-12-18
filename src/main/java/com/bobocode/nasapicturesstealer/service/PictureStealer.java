package com.bobocode.nasapicturesstealer.service;

import com.bobocode.nasapicturesstealer.persistance.entity.Camera;
import com.bobocode.nasapicturesstealer.persistance.entity.Picture;
import com.bobocode.nasapicturesstealer.persistance.repository.CameraRepository;
import com.bobocode.nasapicturesstealer.persistance.repository.PictureRepository;
import com.bobocode.nasapicturesstealer.service.model.NasaCamera;
import com.bobocode.nasapicturesstealer.service.model.NasaPicture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PictureStealer {

    private final NasaPictureReceiver nasaPictureReceiver;
    private final CameraRepository cameraRepository;
    private final PictureRepository pictureRepository;

    @Transactional
    public void stealNasaPictures(int sol) {
        List<NasaPicture> nasaPictures = nasaPictureReceiver.receiveAllPictures(sol);
        nasaPictures.forEach(this::savePicture);
    }

    private void savePicture(NasaPicture nasaPicture) {
        Camera camera = getCamera(nasaPicture);

        Optional<Picture> optionalPicture = pictureRepository.findByNasaId(nasaPicture.id());

        if (optionalPicture.isPresent()) {
            return;
        }

        Picture picture = new Picture();

        picture.setNasaId(nasaPicture.id());
        picture.setCamera(camera);
        picture.setImgSrc(nasaPicture.imgSrc());
        picture.setCreatedAt(nasaPicture.earthDate());

        pictureRepository.save(picture);
    }


    private Camera getCamera(NasaPicture nasaPicture) {
        NasaCamera nasaCamera = nasaPicture.camera();

        return cameraRepository.findByNasaId(nasaCamera.id())
                .orElseGet(() -> {
                            Camera camera = new Camera();
                            camera.setNasaId(nasaCamera.id());
                            camera.setName(nasaCamera.name());

                            return cameraRepository.save(camera);
                        }
                );
    }

}
