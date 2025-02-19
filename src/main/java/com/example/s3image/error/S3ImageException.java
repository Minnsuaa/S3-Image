package com.example.s3image.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class S3ImageException extends RuntimeException {

    private final ErrorCode errorCode;

}