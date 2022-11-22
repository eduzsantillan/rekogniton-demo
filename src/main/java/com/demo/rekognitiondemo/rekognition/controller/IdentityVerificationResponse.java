package com.demo.rekognitiondemo.rekognition.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdentityVerificationResponse {

    private String code;
    private String message;
    private Object apiDetails;


    public static IdentityVerificationResponse whenFacesMatched(Object apiDetails) {
        return IdentityVerificationResponse.builder()
                .code("200")
                .message("Los rostros coinciden")
                .apiDetails(apiDetails)
                .build();
    }

    public static IdentityVerificationResponse whenFacesNotMatch(Object apiDetails) {
        return IdentityVerificationResponse.builder()
                .code("510")
                .message("Los rostros no coinciden")
                .apiDetails(apiDetails)
                .build();
    }

}
