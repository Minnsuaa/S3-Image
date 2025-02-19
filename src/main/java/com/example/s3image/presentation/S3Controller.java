package com.example.s3image.presentation;

import com.example.s3image.presentation.dto.DeleteRequest;
import com.example.s3image.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestPart(value = "image") MultipartFile file) throws IOException {
        s3Service.upload(file);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody DeleteRequest request) {
        s3Service.delete(request.imageUrl());
    }

}