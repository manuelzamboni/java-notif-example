package mz.examples.notif.core;

import java.util.Map;

/**
 * Collects notifications.
 */
public interface NotifsCollector {
    /**
     * Adds an error to the collection.
     *
     * @param error Error to add.
     * @return This same instance.
     */
    NotifsCollector addError(Error error);

    /**
     * Adds a newly created error to the collection.
     *
     * @param code    Error code.
     * @param message Error message.
     * @return This same instance.
     */
    NotifsCollector addError(String code, String message);

    /**
     * Adds a newly created error to the collection.
     *
     * @param code    Error code.
     * @param message Error message.
     * @return This same instance.
     */
    NotifsCollector addError(
        String code, String message, Map<String, Object> references);

    /**
     * Adds a warning to the collection.
     *
     * @param warning Warning to add.
     * @return This same instance.
     */
    NotifsCollector addWaring(Warning warning);

    /**
     * Adds a newly created warning to the collection.
     *
     * @param code    Warning code
     * @param message Warning message.
     * @return This same instance.
     */
    NotifsCollector addWarning(String code, String message);

    /**
     * Returns true if errors have been collected.
     *
     * @return true if errors have been collected.
     */
    boolean hasErrors();

    /**
     * Returns true if errors at least one error with a give code have been
     * collected.
     *
     * @param code Error code.
     * @return true if errors with the given code have been collected.
     */
    boolean hasErrors(String code);

    /**
     * Returns true if warnings have been collected.
     *
     * @return true if warnings have been collected.
     */
    boolean hasWarnings();
}
