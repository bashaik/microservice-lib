
package com.jshop.boot.multitenancy;

import org.springframework.core.NamedInheritableThreadLocal;

/**
 * The class responsible for saving and retrieving requestConetxts (via ThreadLocal).
 *
 */

public abstract class JShopRequestContextHolder
{
    private static final ThreadLocal<JShopRequestContext> REQUEST_CONTEXT_THREAD_LOCAL = new NamedInheritableThreadLocal<>("JShop Request Context");
    
    public static void setRequestContext(JShopRequestContext requestContextParm) 
    {
        REQUEST_CONTEXT_THREAD_LOCAL.set(requestContextParm);
    }

    public static JShopRequestContext getRequestContext() 
    {
        JShopRequestContext requestContext = REQUEST_CONTEXT_THREAD_LOCAL.get();
        return requestContext;
    }
    
    public static void resetRequestContext() 
    {
        REQUEST_CONTEXT_THREAD_LOCAL.remove();
    }

}
