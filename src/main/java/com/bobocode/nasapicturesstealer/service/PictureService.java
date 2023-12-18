package com.bobocode.nasapicturesstealer.service;

import com.bobocode.nasapicturesstealer.persistance.entity.Camera;
import com.bobocode.nasapicturesstealer.persistance.entity.Picture;
import com.bobocode.nasapicturesstealer.persistance.repository.PictureRepository;
import com.bobocode.nasapicturesstealer.service.model.NasaPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Transactional
    public Picture save(NasaPicture nasaPicture, Camera camera) {
        Picture picture = new Picture();

        picture.setNasaId(nasaPicture.id());
        picture.setCamera(camera);
        picture.setImgSrc(nasaPicture.imgSrc());
        picture.setCreatedAt(nasaPicture.earthDate());

        return pictureRepository.save(picture);
    }

    public Optional<Picture> getByNasaId(int nasaId) {
        return pictureRepository.findByNasaId(nasaId);
    }

}
