
package com.jshop.shared.rest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.jshop.core.logging.JShopLogger;

public class BaseController
{

    private static final JShopLogger LOGGER = new JShopLogger(BaseController.class);

    @Autowired
    private MessageSource messageSource;

    protected String getMessage(String messageKeyParm)
    {
        return getMessage(messageKeyParm, null);
    }

    protected String getMessage(String messageKeyParm, Object[] args)
    {
        return messageSource.getMessage(messageKeyParm, args, Locale.US);
    }
}
