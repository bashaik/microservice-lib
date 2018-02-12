package com.jshop.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;

public class MapperConfig {

	@Bean
	Mapper mapper()
	{
	    return new DozerBeanMapper();
	}

}
