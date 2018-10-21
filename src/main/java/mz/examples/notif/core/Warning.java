package mz.examples.notif.core;

import mz.examples.notif.core.impl.SimpleWarning;

/**
 * Contract that implements any representation of an individual warning.
 */
public interface Warning {

    /**
     * Code that identifies the specific kind of warning.
     */
    String getCode();

    /**
     * Warning message. Alternative messages (e.g. in different languages) could
     * be produced by other components though the warning code.
     */
    String getMessage();

    /**
     * Creates a default implementation of a warning
     *
     * @param codigo  Error code.
     * @param message Warning message.
     * @return New Error.
     */
    static Warning create(String codigo, String message) {
        return new SimpleWarning(codigo, message);
    }
}
