package com.sample.billablehours.exception;


/**
 * created by julian
 */
public class ServiceException extends Exception {


    public ServiceException() {}


    public ServiceException(String message) {
        super(message);
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }


}
