
package com.jshop.boot.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jshop.boot.multitenancy.TenantIdentifierInterceptorAdapter;
import com.jshop.core.common.Constants;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

    @Inject
    private TenantIdentifierInterceptorAdapter multiTenancyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(multiTenancyInterceptor)
            		.addPathPatterns("/**")
            		.excludePathPatterns(Constants.INTERCEPTER_EXCLUDE_PATHS);
    }
}