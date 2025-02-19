package com.example.s3image.exception;

import com.example.s3image.error.ErrorCode;
import com.example.s3image.error.S3ImageException;

public class InvalidImageException extends S3ImageException {

    public static final S3ImageException EXCEPTION = new InvalidImageException();

    private InvalidImageException() {
        super(ErrorCode.INVALID_IMAGE);
    }

}