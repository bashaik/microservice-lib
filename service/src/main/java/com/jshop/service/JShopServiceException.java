
package com.jshop.service;

import java.util.List;

public class JShopServiceException extends RuntimeException
{
	private static final long serialVersionUID = 7342580613597028492L;
	private Exception exception;
    private List<String> errors;

    public JShopServiceException(String message)
    {
        super(message);
        exception = new Exception(message);
    }
        
    public JShopServiceException(Exception exceptionParm)
    {
        super(exceptionParm.getMessage());
        exception = exceptionParm;
    }
    
    public JShopServiceException(Exception exceptionParm,  List<String> errorsParm)
    {
        super(exceptionParm.getMessage());
        exception = exceptionParm;
        errors = errorsParm;
    }

    public Exception getException()
    {
        return exception;
    }

    public List<String> getErrors()
    {
        return errors;
    }
    
}
