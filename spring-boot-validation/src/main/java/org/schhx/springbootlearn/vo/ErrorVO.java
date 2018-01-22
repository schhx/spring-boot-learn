package org.schhx.springbootlearn.vo;

import lombok.Data;

import java.util.Collections;

@Data
public class ErrorVO {

    private String error;

    private String original_error;

    private ErrorVO(String error, String original_error) {
        this.error = error;
        this.original_error = original_error;
    }

    public static ErrorVO of(String error) {
        return new ErrorVO(error, error);
    }

    public static ErrorVO of(String error, String original_error) {
        return new ErrorVO(error, original_error);
    }
}
