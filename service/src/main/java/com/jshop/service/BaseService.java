package com.jshop.service;

import java.lang.reflect.ParameterizedType;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService <D, ET>
{

	@Autowired()
	private Mapper mapper;
	
	public D map(ET entity) 
	{
		if(entity == null) 
		{
			return null;
		}
		return (D) mapper.map(entity, getDomainClass());
	}
	
	protected Class<?> getDomainClass() 
	{
        Class<?> genericClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return genericClass;
    }

}
