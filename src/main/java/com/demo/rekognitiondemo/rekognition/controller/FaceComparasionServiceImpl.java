package com.demo.rekognitiondemo.rekognition.controller;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.demo.rekognitiondemo.RekognitionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FaceComparasionServiceImpl implements FaceComparasionService {


    @Autowired
    private RekognitionClient amzRekognitionClient;

    @Override
    public IdentityVerificationResponse compareFaces(IdentityVerificationRequest identityVerificationRequest) throws IOException {

        CompareFacesResult result = amzRekognitionClient.compareFaces(identityVerificationRequest.getDocument(),
                identityVerificationRequest.getSelfie());

        return IdentityVerificationResponse.whenFacesMatched(result);
    }
}





