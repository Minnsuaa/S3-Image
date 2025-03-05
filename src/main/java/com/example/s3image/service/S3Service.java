package com.example.s3image.service;

import com.example.s3image.error.ErrorCode;
import com.example.s3image.error.S3ImageException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final Set<String> IMAGE_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif");

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        validate(fileName);
        try {
            PutObjectRequest object = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(UUID.randomUUID() + "." + getExtension(fileName))
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(object, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (Exception e) {
            throw new S3ImageException(ErrorCode.UPLOAD_IMAGE_FAILED);
        }
    }

    public void delete(String imageUrl) {
        try {
            URL url = new URI(imageUrl).toURL();
            String decodedKey = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
            String key = decodedKey.startsWith("/") ? decodedKey.substring(1) : decodedKey;

            DeleteObjectRequest object = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();

            s3Client.deleteObject(object);
        } catch (Exception e) {
            throw new S3ImageException(ErrorCode.DELETE_IMAGE_FAILED);
        }
    }

    private void validate(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new S3ImageException(ErrorCode.IMAGE_NOT_FOUND);
        }

        String extension = getExtension(fileName);
        if (!IMAGE_EXTENSIONS.contains(extension)) {
            throw new S3ImageException(ErrorCode.INVALID_IMAGE);
            //throw InvalidImageException.EXCEPTION;
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

}