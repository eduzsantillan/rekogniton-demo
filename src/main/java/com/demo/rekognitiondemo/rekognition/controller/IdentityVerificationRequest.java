package com.demo.rekognitiondemo.rekognition.controller;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class IdentityVerificationRequest {

    MultipartFile document;
    MultipartFile selfie;

}
