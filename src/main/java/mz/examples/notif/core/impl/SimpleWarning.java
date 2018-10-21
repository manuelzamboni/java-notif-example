package mz.examples.notif.core.impl;

import mz.examples.notif.core.Warning;

import java.util.Objects;

/**
 * Simple and default implementation of {@link Warning}
 */
public class SimpleWarning implements Warning {
    private String code;
    private String message;

    public SimpleWarning(
        String code,
        String message
    ) {
        Objects.requireNonNull(code, "Must provide a warning code");
        Objects.requireNonNull(message, "Must provide a warning message");

        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
