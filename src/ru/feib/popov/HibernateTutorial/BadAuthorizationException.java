/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.HibernateTutorial;

/**
 *
 * @author popov
 */
public class BadAuthorizationException extends RuntimeException {
    public BadAuthorizationException() {};
    
    public BadAuthorizationException(String message) {
        super(message);
    }
    
    public BadAuthorizationException(Throwable cause) {
        super(cause);
    }
    
    public BadAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BadAuthorizationException(String message, Throwable cause,
            boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
