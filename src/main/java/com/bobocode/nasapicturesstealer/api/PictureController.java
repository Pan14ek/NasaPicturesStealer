package com.bobocode.nasapicturesstealer.api;

import com.bobocode.nasapicturesstealer.service.PictureStealer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/pictures")
public class PictureController {

    private final PictureStealer pictureStealer;

    @PostMapping("/steal")
    public void stealPicture(@RequestBody UserSol userSol) {
        pictureStealer.stealNasaPictures(userSol.sol());
    }

}
