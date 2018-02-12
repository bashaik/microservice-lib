
package com.jshop.cloud.orm;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jshop.boot.multitenancy.JShopRequestContext;
import com.jshop.boot.multitenancy.JShopRequestContextHolder;

@Component
public class TenantResolverImpl implements CurrentTenantIdentifierResolver
{    
    @Value("${master.schema}")
    private String masterSchema;
    
    @Value("${service.schema}")
    private String serviceSchema;    
        
    @Override
    public String resolveCurrentTenantIdentifier()
    {
        String identifier = masterSchema;
        
        if (!serviceSchema.equals(masterSchema))
        {
            JShopRequestContext requestContext = JShopRequestContextHolder.getRequestContext();
            if (requestContext!=null) 
            {
                String tenant = requestContext.getTenant();
                if (tenant!=null)
                {
                    identifier =  tenant + "_" + serviceSchema;
                }
            }
        }
        
        return identifier;
    }

    public void setMasterSchemaParm(String masterSchemaParm)
    {
        masterSchema = masterSchemaParm;
    }   
    
    @Override
    public boolean validateExistingCurrentSessions()
    {
        return true;
    }
}
