
package com.jshop.service;

public class JShopDaoException extends RuntimeException
{

    private static final long serialVersionUID = 604012305132927174L;

    public JShopDaoException(String messageParm)
    {
        super(messageParm);
    }

    public JShopDaoException(String messageParm, Exception excParm)
    {
        super(messageParm, excParm);
    }
}
