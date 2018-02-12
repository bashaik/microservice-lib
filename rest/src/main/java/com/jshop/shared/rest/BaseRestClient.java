package com.jshop.shared.rest;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jshop.boot.multitenancy.JShopRequestContextHolder;


public abstract class BaseRestClient implements Serializable
{

    private static final long serialVersionUID = 8495713844981294293L;
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.url}")
    private String serviceUrl;


    public <T> T invokeService(ParameterizedTypeReference<T> typeRef, String url, Object payload, HttpMethod method,  Object... params)
    {
        String token = getTokenFromRequestContext();
        HttpHeaders headers = new HttpHeaders();
        String authorizationHeader = "Bearer " + token;
        headers.add("Authorization", authorizationHeader);
        String fullUrl = serviceUrl + url;
        return invokeService(typeRef, fullUrl, payload, headers, method,  params);
    }

    public <T> T invokeService(ParameterizedTypeReference<T> typeReference, String url, Object payload, HttpHeaders headers, HttpMethod method,  Object... params)
    {
        HttpEntity httpEntity = createHttpEntity(payload, headers);        
        
        ResponseEntity<T> response = null;
        if (params == null || params.length == 0)
        {
            response = restTemplate.exchange(url, method, httpEntity, typeReference);
        }
        else
        {
            response = restTemplate.exchange(url, method, httpEntity, typeReference, params);
        }

        return response.getBody();
    }

	private HttpEntity createHttpEntity(Object payload, HttpHeaders headers) {
		HttpEntity httpEntity = null;
        if (payload != null)
        {
            httpEntity = new HttpEntity(payload, headers);
        }
        else
        {
            httpEntity = new HttpEntity(headers);
        }
		return httpEntity;
	} 

    private String getTokenFromRequestContext()
    {
    		String  token = null;
    		if (JShopRequestContextHolder.getRequestContext() != null) {
    			JShopRequestContextHolder.getRequestContext().getToken();
    		}
        return token;
    }
    
    public void setRestTemplate(RestTemplate restTemplateParm)
    {
        restTemplate = restTemplateParm;
    }
}
