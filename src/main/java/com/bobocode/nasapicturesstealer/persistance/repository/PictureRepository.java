package com.bobocode.nasapicturesstealer.persistance.repository;

import com.bobocode.nasapicturesstealer.persistance.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findByNasaId(int nasaId);

}
