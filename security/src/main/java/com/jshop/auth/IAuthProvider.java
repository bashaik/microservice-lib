package com.jshop.auth;


public interface IAuthProvider
{
	String generateToken(TokenData tokenDataParm) throws AuthProviderException;
    TokenData verifyToken(String authTokenString) throws AuthProviderException;
}
