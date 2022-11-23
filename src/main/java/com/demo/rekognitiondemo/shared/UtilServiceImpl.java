package com.demo.rekognitiondemo.shared;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UtilServiceImpl implements UtilService {


    @Override
    public UtilDTO uploadToBucket(String bucketName, String fileName, MultipartFile file) throws IOException {
        try{
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .build();
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(file.getContentType());
            data.setContentLength(file.getSize());

            s3Client.putObject(bucketName, fileName, file.getInputStream(), data);
            return UtilDTO.uploadToBucketSucced();

        }catch (Exception e){
            return UtilDTO.uploadToBucketFailed(e.getMessage());
        }
    }

}
