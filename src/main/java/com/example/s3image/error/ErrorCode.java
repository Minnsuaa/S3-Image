package com.example.s3image.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "Image Not Found"),
    INVALID_IMAGE(HttpStatus.BAD_REQUEST, "Invalid Image"),
    UPLOAD_IMAGE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Upload Image Failed"),
    DELETE_IMAGE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Delete Image Failed");

    private final HttpStatus status;
    private final String message;

}