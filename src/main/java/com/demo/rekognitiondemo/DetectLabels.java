package com.demo.rekognitiondemo;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import java.util.List;

public class DetectLabels {


    public String detectLabels() {

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withS3Object(new S3Object()
                                .withName("DNI1.jpg")
                                .withBucket("rekognition-eduzsantillan")))
                .withMaxLabels(10)
                .withMinConfidence(75F);


        FaceDetection faceDetection = new FaceDetection();
        faceDetection.setFace(new FaceDetail());



        try {
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();

            for (Label label : labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }






}
