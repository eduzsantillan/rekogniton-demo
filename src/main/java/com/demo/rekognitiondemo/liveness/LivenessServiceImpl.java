package com.demo.rekognitiondemo.liveness;

import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Label;
import com.demo.rekognitiondemo.DetectLabels;
import com.demo.rekognitiondemo.RekognitionClient;
import com.demo.rekognitiondemo.shared.Constants;
import com.demo.rekognitiondemo.shared.UtilDTO;
import com.demo.rekognitiondemo.shared.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class LivenessServiceImpl implements LivenessService {


    @Autowired
    RekognitionClient rekognitionClient;

    @Autowired
    UtilService utilService;




    @Override
    public LivenessResponse verifyLiveness(LivenessRequest livenessRequest)  {
        try{
            if(challengeSuccess(livenessRequest)){
                if(isIdCard(livenessRequest.getFile())){
                    return LivenessResponse.livenessProcessOk();
                }else {
                    return LivenessResponse.documentIsNotCard();
                }
            }else{
                return LivenessResponse.challengeFailed();
            }
        }catch (Exception e){
            return LivenessResponse.livenessProcessFailed(e.getMessage());
        }
    }

    @Override
    public LivenessResponse uploadToS3(LivenessRequest livenessRequest)  {

        try{
            UtilDTO utilResult = utilService.uploadToBucket(livenessRequest.getBucketName(), livenessRequest.getUsername(), livenessRequest.getFile());

            if(Objects.equals(utilResult.getCode(), Constants.SUCCESS.getCode())) {
                return LivenessResponse.uploadToS3Succed();
            }else{
                return LivenessResponse.uploadToS3Failed("");
            }
        }catch (Exception e){
            return LivenessResponse.uploadToS3Failed(e.getMessage());
        }


    }


    public boolean challengeSuccess(LivenessRequest livenessRequest) {
        return true;
    }


    public boolean isIdCard(MultipartFile file) {
        DetectLabelsResult detectLabelsResult = rekognitionClient.detectLabels(file);
        List<Label> labels = detectLabelsResult.getLabels();
        boolean isIdCard = false;

        for (Label label : labels) {
            if (label.getName().equals(Constants.DETECT_LABELS_IDCARD_LABEL.getCode()) &&
                    label.getConfidence() > Float.parseFloat(Constants.DETECT_LABELS_IDCARD_CONFIDENCE.getCode())) {
                isIdCard=true;
            }
        }
        return isIdCard;
    }







}
