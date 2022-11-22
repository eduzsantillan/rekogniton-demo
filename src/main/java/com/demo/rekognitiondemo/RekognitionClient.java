package com.demo.rekognitiondemo;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class RekognitionClient {


    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();


    public CompareFacesResult compareFaces(MultipartFile document, MultipartFile selfie) throws IOException {

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(new Image()
                        .withBytes(ByteBuffer.wrap(document.getBytes())))
                .withTargetImage(new Image()
                        .withBytes(ByteBuffer.wrap(selfie.getBytes())))
                .withSimilarityThreshold(90F);

        try {
            CompareFacesResult result = rekognitionClient.compareFaces(request);
            List<CompareFacesMatch> faceDetails = result.getFaceMatches();

            if (faceDetails.size() > 0) {
                return result;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public DetectLabelsResult detectLabels(String bucket, String filename) {
        try {
            DetectLabelsRequest request = new DetectLabelsRequest()
                    .withImage(new Image()
                            .withS3Object(new S3Object()
                                    .withName(filename)
                                    .withBucket(bucket)))
                    .withMaxLabels(10)
                    .withMinConfidence(75F);
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();

            for (Label label : labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DetectLabelsResult detectLabels(MultipartFile file) {
        try {

            ByteBuffer imageBytes = ByteBuffer.wrap(file.getBytes());

            DetectLabelsRequest request = new DetectLabelsRequest()
                    .withImage(new Image().withBytes(imageBytes))
                    .withMaxLabels(10)
                    .withMinConfidence(75F);
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List<Label> labels = result.getLabels();
            for (Label label : labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
