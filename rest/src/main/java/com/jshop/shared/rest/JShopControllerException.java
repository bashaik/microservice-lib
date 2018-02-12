package com.jshop.shared.rest;

import java.util.List;

import org.springframework.http.HttpStatus;


public class JShopControllerException extends RuntimeException
{

	private static final long serialVersionUID = 5805014180717557412L;

	private HttpStatus status;
    private List<String> errors;

    public JShopControllerException(String messageParm, HttpStatus statusParm)
    {
        super(messageParm);
        status = statusParm;
    }

    public HttpStatus getStatus()
    {
        return status;
    }

    public List<String> getErrors()
    {
        return errors;
    }

}
