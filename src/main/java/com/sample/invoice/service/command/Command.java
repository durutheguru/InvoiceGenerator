package com.sample.invoice.service.command;


import com.sample.invoice.exception.ServiceException;

/**
 * created by julian
 */
public interface Command<T> {


    T execute() throws ServiceException;


}
