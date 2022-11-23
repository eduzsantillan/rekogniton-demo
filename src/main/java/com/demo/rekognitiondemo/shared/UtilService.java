package com.demo.rekognitiondemo.shared;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UtilService {

    UtilDTO uploadToBucket(String bucketName, String fileName, MultipartFile file) throws IOException;

}
