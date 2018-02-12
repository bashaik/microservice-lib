package com.jshop.orm;

import org.junit.Test;

import com.jshop.cloud.orm.UuidGenerator;

import java.io.Serializable;

import static org.junit.Assert.*;

public class UuidGeneratorTest 
{

    @Test
    public void testGenerate() 
    {
        UuidGenerator generator = new UuidGenerator();
        Serializable uuid = generator.generate(null,null);
        assertNotNull(uuid);
    }
}