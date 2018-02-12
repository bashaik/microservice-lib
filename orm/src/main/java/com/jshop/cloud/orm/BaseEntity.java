
package com.jshop.cloud.orm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity implements Serializable 
{

	private static final long serialVersionUID = 4010575189888281924L;

	public static final String OID = "oid";
	public static final String LAST_MODIFIED = "modifiedTime";
	public static final String MODIFIED_BY = "modifiedBy";

	
    @Id
    @GenericGenerator(name = "oid", strategy = "com.jshop.cloud.orm.UuidGenerator")
    @GeneratedValue(generator = "oid")
    @Column(name = "oid", columnDefinition = "BINARY(16)")
    private UUID oid;
    
	@Column(name = "MODIFIED_BY", nullable = true, unique = false)
	private String modifiedBy;

	@UpdateTimestamp
	@Column(name = "MODIFIED_TIME", columnDefinition = "DATETIME", nullable = true, unique = false, insertable = false, updatable = false)
	private Timestamp modifiedTime;

    public void setOid(UUID oidParm) 
    {
        oid = oidParm;
    }

    public UUID getOid() 
    {
        return oid;
    }
	
	public String getModifiedBy() 
	{
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedByParm) 
	{
		modifiedBy = modifiedByParm;
	}

	public Timestamp getModifiedTime() 
	{
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTimeParm) 
	{
		modifiedTime = modifiedTimeParm;
	}


	@Override
	public int hashCode() 
	{
		return Objects.hashCode(getOid());
	}	

	@Override
	public boolean equals(Object obj) 
	{
        if (obj instanceof BaseEntity)
        {
            return false;
        }
        
        if (this == obj)
        {
            return true;
        }
        
        BaseEntity rhs = (BaseEntity) obj;
        return Objects.equals(getOid(), rhs.getOid()); 
    }

}
