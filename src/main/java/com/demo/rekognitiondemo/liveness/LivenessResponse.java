package com.demo.rekognitiondemo.liveness;

import com.demo.rekognitiondemo.shared.Constants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LivenessResponse {

    private String code;
    private String message;
    private Object details;

    static LivenessResponse documentIsNotCard() {
        return LivenessResponse.builder()
                .code(Constants.DETECT_LABELS_IS_NOT_IDCARD.getCode())
                .message(Constants.DETECT_LABELS_IS_NOT_IDCARD.getMessage())
                .build();
    }

    static LivenessResponse challengeFailed() {
        return LivenessResponse.builder()
                .code(Constants.LIVENESS_CHALLENGE_FAILED.getCode())
                .message(Constants.LIVENESS_CHALLENGE_FAILED.getMessage())
                .build();
    }

    static  LivenessResponse livenessProcessOk() {
        return LivenessResponse.builder()
                .code(Constants.LIVENESS_PROCESS_OK.getCode())
                .message(Constants.LIVENESS_PROCESS_OK.getMessage())
                .build();
    }

    static  LivenessResponse livenessProcessFailed(String errorMessage) {
        return LivenessResponse.builder()
                .code(Constants.ERROR.getCode())
                .message(Constants.LIVENESS_PROCESS_OK.getMessage().concat(" ").concat(errorMessage))
                .build();
    }

    static  LivenessResponse uploadToS3Succed() {
        return LivenessResponse.builder()
                .code(Constants.UPLOAD_TO_BUCKET_OK.getCode())
                .message(Constants.UPLOAD_TO_BUCKET_OK.getMessage())
                .build();
    }


    static  LivenessResponse uploadToS3Failed(String errorMessage) {
        return LivenessResponse.builder()
                .code(Constants.UPLOAD_TO_BUCKET_FAILED.getCode())
                .message(Constants.UPLOAD_TO_BUCKET_FAILED.getMessage().concat(" ").concat(errorMessage))
                .build();
    }



}
