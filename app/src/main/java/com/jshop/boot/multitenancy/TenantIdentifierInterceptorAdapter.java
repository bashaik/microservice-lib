
package com.jshop.boot.multitenancy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jshop.auth.IAuthProvider;
import com.jshop.auth.TokenData;
import com.jshop.core.logging.JShopLogger;

@Component
@PropertySource("classpath:config.properties")
public class TenantIdentifierInterceptorAdapter extends HandlerInterceptorAdapter
{

    private static final JShopLogger LOGGER = new JShopLogger(TenantIdentifierInterceptorAdapter.class);
    
    @Value("${service.schema}")
    private String serviceSchemaQualifier;
    
    @Autowired
    private IAuthProvider authProvider;

    @Override
    public boolean preHandle(HttpServletRequest requestParm, HttpServletResponse responseParm, Object handler) throws Exception
    {   
        TokenData tokenData = null;
        
        String token = extractToken(requestParm);
        if (token == null)
        {
            LOGGER.error("Missing or invalid Authorization header");
            responseParm.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;     
        }
        try
        {
            tokenData = authProvider.verifyToken(token);
        }
        catch (Exception e)
        {
            LOGGER.error("Error occured in verifying token.", e);
            responseParm.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;            
        }
        
        if (tokenData == null)
        {
            LOGGER.error("The tokenData retrieved from token is 'null'");
            responseParm.sendError(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        
        injectRequestContext(token, tokenData);
        
        return true;
    }

    
    @Override
    public void postHandle(HttpServletRequest requestParm, HttpServletResponse responseParm, Object handlerParm, ModelAndView modelAndViewParm)
            throws Exception
    {
        removeRequestContext();
    }    

    String extractToken(HttpServletRequest requestParm)
    {
        String token = null;
        String authHeader = requestParm.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty())
        {
            return null;
        }
        if (!authHeader.startsWith("Bearer "))
        {
            return null;
        }
        
        token = authHeader.substring("Bearer".length() + 1);
        return token;
    }

    private void injectRequestContext(String token, TokenData tokenData)
    {
        String tenant = tokenData.getTenant();
        JShopRequestContext requestContext = new JShopRequestContext(token, tenant);
        JShopRequestContextHolder.resetRequestContext();
        JShopRequestContextHolder.setRequestContext(requestContext);
    }
    
    private void removeRequestContext()
    {
        JShopRequestContextHolder.resetRequestContext();
    }
}