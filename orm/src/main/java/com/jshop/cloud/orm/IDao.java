
package com.jshop.cloud.orm;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

public interface IDao<ET> 
{ 
    @Transactional
    ET lookupByOid(UUID oidParm);
    
    @Transactional
    List<ET> getAll();
    
    @Transactional
    ET save(ET entityParm);
    
}
