package com.demo.rekognitiondemo.liveness;

import java.io.IOException;

public interface LivenessService {

    LivenessResponse verifyLiveness(LivenessRequest livenessRequest) ;

    LivenessResponse uploadToS3(LivenessRequest livenessRequest);



}
