package com.demo.rekognitiondemo.rekognition.controller;

import java.io.IOException;

public interface FaceComparasionService {

    public IdentityVerificationResponse compareFaces(IdentityVerificationRequest identityVerificationRequest) throws IOException;

}

