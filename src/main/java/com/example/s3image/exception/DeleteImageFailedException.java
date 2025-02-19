package com.example.s3image.exception;

import com.example.s3image.error.ErrorCode;
import com.example.s3image.error.S3ImageException;

public class DeleteImageFailedException extends S3ImageException {

    public static final S3ImageException EXCEPTION = new DeleteImageFailedException();

    private DeleteImageFailedException() {
        super(ErrorCode.DELETE_IMAGE_FAILED);
    }

}