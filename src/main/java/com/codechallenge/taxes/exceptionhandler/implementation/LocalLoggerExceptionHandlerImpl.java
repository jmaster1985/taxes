package com.codechallenge.taxes.exceptionhandler.implementation;

import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import org.springframework.stereotype.Service;

@Service
public class LocalLoggerExceptionHandlerImpl implements ExceptionHandler {
    @Override
    public void handle(Throwable t) {
        t.printStackTrace();
    }
}
