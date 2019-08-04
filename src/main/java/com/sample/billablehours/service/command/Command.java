package com.sample.billablehours.service.command;


import com.sample.billablehours.exception.ServiceException;

/**
 * created by julian
 */
public interface Command<T> {


    T execute() throws ServiceException;


}
