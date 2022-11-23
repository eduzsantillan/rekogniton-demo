package com.demo.rekognitiondemo.liveness;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class LivenessRequest {

    private String bucketName;
    private String username;
    private MultipartFile file;

}
