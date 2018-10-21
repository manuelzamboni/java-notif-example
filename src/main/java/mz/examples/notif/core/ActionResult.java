package mz.examples.notif.core;

import mz.examples.notif.core.impl.SimpleActionResult;

import java.util.List;
import java.util.Objects;

/**
 * Represents the result of an action.
 */
public interface ActionResult<T> {
    /**
     * Returns the value produced as a successful outcome of the action.
     *
     * @return Value of the result, it will be null when the result is
     * unsuccessful.
     */
    T get();

    /**
     * Errors that caused the result to be unsuccessful. It will be empty in the
     * case of a successful result.
     *
     * @return List of errors.
     */
    List<Error> getErrors();

    /**
     * Warnings produced by the action. There can be warnings either in successful
     * or unsuccessful results.
     *
     * @return List of warnings.
     */
    List<Warning> getWarnings();

    /**
     * True when the result is successful, false when unsuccessful.
     */
    boolean isSuccessful();

    /**
     * True when the result is unsuccessful, false when successful.
     */
    default boolean isUnsuccessful() {
        return !isSuccessful();
    }

    /**
     * Creates a default implementation of a successful result.
     */
    static ActionResult<Void> createSuccessful() {
        return SimpleActionResult.createSuccessful();
    }

    /**
     * Creates a default implementation of a successful result.
     *
     * @param value Value produced as a result of the action.
     */
    static <T> ActionResult<T> createSuccessful(T value) {
        return SimpleActionResult.createSuccessful(value);
    }

    /**
     * Creates a default implementation of a successful result.
     *
     * @param value    Value produced as a result of the action.
     * @param warnings Warnings of the action.
     */
    static <T> ActionResult<T> createSuccessful(
        T value,
        List<Warning> warnings
    ) {
        return SimpleActionResult.createSuccessful(value, warnings);
    }

    /**
     * Creates a default implementation of an unsuccessful result.
     *
     * @param errors Errors that caused the action to be unsuccessful.
     */
    static <T> ActionResult<T> createUnsuccessful(List<Error> errors) {
        return SimpleActionResult.createUnsuccessful(errors);
    }

    /**
     * Creates a default implementation of an unsuccessful result.
     *
     * @param errors   Errors that caused the action to be unsuccessful.
     * @param warnings Warnings of the action.
     * @return The new action result.
     */
    static <T> ActionResult<T> createUnsuccessful(
        List<Error> errors,
        List<Warning> warnings
    ) {
        return SimpleActionResult.createUnsuccessful(errors, warnings);
    }

    /**
     * Creates a default implementation of an unsuccessful result.
     *
     * @param errors Errors that caused the result to be unsuccessful.
     */
    static <T> ActionResult<T> createUnsuccessful(Error... errors) {
        return SimpleActionResult.createUnsuccessful(List.of(errors));
    }

    /**
     * Creates a default implementation of an unsuccessful result.
     *
     * @param error Error that caused the result to be unsuccessful.
     */
    static <T> ActionResult<T> createUnsuccessful(Error error) {
        Objects.requireNonNull(error, "Must specify the error");
        return SimpleActionResult.createUnsuccessful(List.of(error));
    }
}