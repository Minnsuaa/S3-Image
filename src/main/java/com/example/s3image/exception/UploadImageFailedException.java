package com.example.s3image.exception;

import com.example.s3image.error.ErrorCode;
import com.example.s3image.error.S3ImageException;

public class UploadImageFailedException extends S3ImageException {

    public static final S3ImageException EXCEPTION = new UploadImageFailedException();

    private UploadImageFailedException() {
        super(ErrorCode.UPLOAD_IMAGE_FAILED);
    }

    public UploadImageFailedException(ErrorCode errorCode) {
        super(errorCode);
    }
}