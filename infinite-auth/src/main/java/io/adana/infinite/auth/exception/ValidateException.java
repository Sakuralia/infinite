package io.adana.infinite.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author sakura
 * @version 1.0
 * @description <p>
 * the exception of Validator.
 * </p>
 * @date 2020/11/4 15:55
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }
}
