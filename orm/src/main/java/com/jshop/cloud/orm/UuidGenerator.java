package com.jshop.cloud.orm;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UuidGenerator implements IdentifierGenerator 
{
	@Override
	public Serializable generate(SharedSessionContractImplementor sscImplementor, Object obj) throws HibernateException 
	{
		com.eaio.uuid.UUID uuidV1 = new com.eaio.uuid.UUID();
		return uuidV1;
	}

}
