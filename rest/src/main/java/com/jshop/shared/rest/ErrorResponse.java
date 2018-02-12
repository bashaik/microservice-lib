
package com.jshop.shared.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class ErrorResponse
{
    private HttpStatus code;
    private String details = "";
    private List<String> errors;

    public ErrorResponse()
    {
    }

    public HttpStatus getCode()
    {
        return code;
    }

    public void setCode(HttpStatus badRequestParm)
    {
        this.code = badRequestParm;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String detailsParm)
    {
        this.details = detailsParm;
    }

    public List<String> getErrors()
    {
        return errors;
    }

    public void setErrors(List<String> errorsParm)
    {
        errors = errorsParm;
    }

    
}
