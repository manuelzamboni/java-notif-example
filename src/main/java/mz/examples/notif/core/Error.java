package mz.examples.notif.core;

import mz.examples.notif.core.impl.SimpleError;

import java.util.Map;

/**
 * Representation of an individual error.
 */
public interface Error {

    /**
     * Code that identifies the specific kind of error.
     */
    String getCode();

    /**
     * Error message. Alternative messages (e.g. in different languages) could
     * be produced by other components though the error code and (optionally) the
     * error references.
     */
    String getMessage();

    /**
     * Map with extra descriptive information about the error.
     */
    Map<String, ?> getReferences();

    /**
     * Creates a default implementation of an error.
     *
     * @param code    Error code.
     * @param message Error message.
     * @return New Error.
     */
    static Error create(String code, String message) {
        return new SimpleError(code, message);
    }

    /**
     * Creates a default implementation of an error with references included.
     *
     * @param code       Error code.
     * @param message    Error message.
     * @param references Map of references.
     * @return New Error.
     */
    static Error create(String code, String message, Map<String, ?> references) {
        return new SimpleError(code, message, references);
    }
}
