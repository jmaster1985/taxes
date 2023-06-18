package com.codechallenge.taxes.controller;

import java.io.Serial;
import java.io.Serializable;

class UnsuccessfulResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String message;

    public UnsuccessfulResponse() {

    }

    public UnsuccessfulResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
