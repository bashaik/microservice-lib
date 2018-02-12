
package com.jshop.auth;

import java.io.Serializable;

public class TokenData implements Serializable
{
	private static final long serialVersionUID = 3666984033038378849L;
	
	private String tenant;
	private String userName;
    
    public static final String TENANT_KEY = "tenantKey";
    public static final String USER_NAME = "userName";

    public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
    
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userNameParm)
    {
        userName = userNameParm;
    }
    
}
