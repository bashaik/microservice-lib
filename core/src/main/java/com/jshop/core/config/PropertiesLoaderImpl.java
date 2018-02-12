
package com.jshop.core.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoaderImpl implements PropertiesLoader 
{
    private static final List<String> DEFAULT_PROPERTY_FILES = new ArrayList<>(Arrays.asList("config.properties"));
    private static Properties properties;

    @Override
    public Properties asProperties() throws Exception
    {
        if (properties == null || properties.isEmpty()) 
        {
            synchronized (PropertiesLoaderImpl.class) 
            {
                if (properties == null || properties.isEmpty()) 
                {
                		properties = loadResources();
                }
            }
        }
        return properties;
    }

    private Properties loadResources() throws Exception 
    {
        Properties prop = new Properties();
        for (String propertyFile : DEFAULT_PROPERTY_FILES) 
        {
            Enumeration<URL> resources = PropertiesLoaderImpl.class.getClassLoader().getResources(propertyFile);
            while (resources.hasMoreElements()) 
            {
                URL resourceURL = resources.nextElement();
                prop.load(resourceURL.openStream());
            }
        }
        for (Object current : System.getProperties().keySet()) 
        {
            prop.put(current, System.getProperties().get(current));
        }
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) 
        {
            prop.put(envName, env.get(envName));
        }
        return prop;
    }
      
}
