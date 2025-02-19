package com.example.s3image.exception;

import com.example.s3image.error.ErrorCode;
import com.example.s3image.error.S3ImageException;

public class ImageNotFoundException extends S3ImageException {

    public static final S3ImageException EXCEPTION = new ImageNotFoundException();

    private ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }

}