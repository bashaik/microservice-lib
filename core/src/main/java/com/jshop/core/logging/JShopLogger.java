
package com.jshop.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JShopLogger
{
	private Logger logger;

    public JShopLogger(Class<?> clazz)
	{
		this.logger = LoggerFactory.getLogger(clazz);
	}

	public void error(String msg)
	{
		this.logger.error(msg);
	}
	
	public void error(String msg, Object... arguments)
	{
		this.logger.error(msg, arguments);
	}
	
	public void error(String msg, Throwable t)
	{
		this.logger.error(msg, t);
	}

	public void warn(String msg)
	{
		this.logger.warn(msg);
	}
	
	public void war(String msg, Object... arguments)
	{
		this.logger.warn(msg, arguments);
	}
	
	public void warn(String msg, Throwable t)
	{
		this.logger.warn(msg, t);
	}

	public void info(String msg)
	{
		this.logger.info(msg);
	}

	public void info(String msg, Object... arguments)
	{
		this.logger.info(msg, arguments);
	}
	
	public void debug(String msg)
	{
		this.logger.debug(msg);
	}
	
	public void debug(String msg, Object... arguments)
	{
		this.logger.debug(msg, arguments);
	}
	
	public void trace(String msg)
	{
		this.logger.trace(msg);
	}
	
	public void trace(String msg, Object... arguments)
	{
		this.logger.trace(msg, arguments);
	}

	public boolean isTraceEnabled() 
	{
		return this.logger.isTraceEnabled();
	}

	public boolean isDebugEnabled() 
	{
		return this.logger.isDebugEnabled();
	}

}