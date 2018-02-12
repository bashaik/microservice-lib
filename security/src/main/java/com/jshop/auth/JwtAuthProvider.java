
package com.jshop.auth;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.jshop.core.logging.JShopLogger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthProvider implements IAuthProvider
{
    private static final JShopLogger LOGGER = new JShopLogger(JwtAuthProvider.class);
    
    private static final String TOKEN_KEY = "Siw2HiX3q0qy0OWwbizAz3Eh2IacYNuR"; 
    private long tokenLifetimeMinutes = 480;
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    
    @Override
    public String generateToken(TokenData authTokenDataParm) throws AuthProviderException
    {        
        String tokenString = null;
        
        Date now = new Date(); 
        Date nowPlusTokenLife = new Date(now.getTime() + TimeUnit.MINUTES.toMillis(tokenLifetimeMinutes));
        String subject = authTokenDataParm.getTenant() + "_" + authTokenDataParm.getUserName();

        LOGGER.info("token generated time={}}", now);
        
        try 
        {
            tokenString = Jwts.builder()
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(nowPlusTokenLife)
                    .setSubject(subject)
                    .claim(TokenData.TENANT_KEY, authTokenDataParm.getTenant())
                    .claim(TokenData.USER_NAME, authTokenDataParm.getUserName())
                    .signWith(signatureAlgorithm, TOKEN_KEY)
                    .compact();
        } 
        catch (Exception e)
        {
            throw new AuthProviderException("token generation FAILED", e);
        }
        
        return tokenString;
    }

    @Override
    public TokenData verifyToken(String tokenStringParm) throws AuthProviderException
    {
        TokenData authTokenData = null;
        try 
        {
            Claims claims = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(tokenStringParm).getBody();
            authTokenData = new TokenData();
            authTokenData.setTenant((String) claims.get(TokenData.TENANT_KEY));
            authTokenData.setUserName((String) claims.get(TokenData.USER_NAME));
        }
        catch (Exception e)
        {
            throw new AuthProviderException("Erorr verifying token", e);
        }
        return authTokenData;
    }
    
    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithmParm)
    {
        signatureAlgorithm = signatureAlgorithmParm;
    }

}
