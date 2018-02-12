
package com.jshop.auth;

public class AuthProviderException extends Exception
{

	private static final long serialVersionUID = -1220562294145422377L;

	public AuthProviderException(String msg)
    {
        super(msg);
    }

    public AuthProviderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AuthProviderException(Throwable cause)
    {
        super(cause);
    }
}
