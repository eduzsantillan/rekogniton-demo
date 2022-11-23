package com.demo.rekognitiondemo.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilDTO {

    private String code;
    private String message;
    private Object details;

    public static UtilDTO uploadToBucketSucced() {
        return UtilDTO.builder()
                .code(Constants.SUCCESS.getCode())
                .message("File uploaded successfully")
                .build();
    }

    public static UtilDTO uploadToBucketFailed(String errorMessage) {
        return UtilDTO.builder()
                .code(Constants.SUCCESS.getCode())
                .message("Error uploading file, details: ".concat(errorMessage))
                .build();
    }

}
