package com.demo.rekognitiondemo.liveness;

import com.demo.rekognitiondemo.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/liveness")
public class LivenessController {

    @Autowired
    private LivenessService livenessService;

    @PostMapping("/updload")
    public LivenessResponse upload(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @RequestPart(value = "username", required = true) String username,
            @RequestPart(value = "bucketName", required = true) String bucketName
    ) {
        LivenessRequest request = LivenessRequest.builder()
                .file(file)
                .username(username)
                .bucketName(bucketName)
                .build();

        LivenessResponse response = livenessService.verifyLiveness(request);
        if(response.getCode().equals(Constants.SUCCESS.getCode())) {
            return livenessService.uploadToS3(request);
        }else{
            return response;
        }

    }





}
