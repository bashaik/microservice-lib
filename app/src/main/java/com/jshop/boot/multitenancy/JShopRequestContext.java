
package com.jshop.boot.multitenancy;

public class JShopRequestContext
{
    private String token;
    private String tenant;   

    public JShopRequestContext(String tokenParm, String tenantParm)
    {
        token = tokenParm;
        tenant = tenantParm;
    }

    public String getToken()
    {
        return token;
    }
    
    public String getTenant()
    {
        return tenant;
    }

}
