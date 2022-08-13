package com.laylib.jintl.boot.exception;

/**
 * provider config not found
 *
 * @author Lay
 * @since 1.0.0
 */
public class ProviderConfigNotFoundException extends RuntimeException {
    public ProviderConfigNotFoundException(String type, Throwable e) {
        super(type + " provider config not found", e);
    }
}
