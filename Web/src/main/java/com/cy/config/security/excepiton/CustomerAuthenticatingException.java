package com.cy.config.security.excepiton;
import org.springframework.security.core.AuthenticationException;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-24 12:20:56
 */


public class CustomerAuthenticatingException extends AuthenticationException {
    public CustomerAuthenticatingException(String msg) {
        super(msg);
    }
}
