
package com.jshop.cloud.orm;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<ET> implements IDao<ET> 
{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public EntityManager getEntityManager()
    {
    		return entityManager;
    }
    
    protected Class<?> getEntityClass() 
    {
        Class<?> genericClass = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return genericClass;
    }
        
	@Override
    @Transactional
    public ET lookupByOid(UUID id) 
    {
        return (ET) entityManager.find(getEntityClass(), id);
    }    
	
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<ET> getAll() 
    {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery< ? > criteriaQuery = builder.createQuery(getEntityClass());
		criteriaQuery.from(getEntityClass());
		criteriaQuery.distinct(true);
		
		List<ET> results = (List<ET>) entityManager.createQuery(criteriaQuery).getResultList();
        
        return results;
    }  	
    
    @Override
    @Transactional
    public ET save(ET entity) 
    {
        if (entity == null)
        {
            throw new IllegalArgumentException("Entity cannot be NULL.");
        }
        entity = entityManager.merge(entity);
        return entity;
    }

}
