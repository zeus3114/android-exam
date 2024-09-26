package com.example.userprofileandroidexam.data.model.result;

import com.example.userprofileandroidexam.domain.ErrorState;

public class Error {
    private ErrorState errorState;
    private String message;

    public Error(ErrorState errorState) {
        this.errorState = errorState;
    }

    public Error(ErrorState errorState, String message) {
        this.errorState = errorState;
        this.message = message;
    }

    public ErrorState getErrorState() {
        return errorState;
    }

    public String getMessage() {
        return message;
    }
}
