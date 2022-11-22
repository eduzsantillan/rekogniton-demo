package com.demo.rekognitiondemo.rekognition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/rekognition")
public class RekognitionController {

    @Autowired
    private FaceComparasionService faceComparasionService;

    @PostMapping ("/compare-faces")
    public IdentityVerificationResponse compareFaces(@RequestPart(value="document",required = true)MultipartFile document,
                                                     @RequestPart(value="selfie",required = true) MultipartFile selfie) throws IOException {

        IdentityVerificationRequest request = IdentityVerificationRequest.builder()
                .document(document)
                .selfie(selfie)
                .build();
        return faceComparasionService.compareFaces(request);
    }



}
