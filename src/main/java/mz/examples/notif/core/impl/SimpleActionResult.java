package mz.examples.notif.core.impl;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.Error;
import mz.examples.notif.core.Warning;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple implementation of an immutable {@link ActionResult}.
 *
 * @param <T> Type of the value of the result.
 */
public class SimpleActionResult<T> implements ActionResult<T> {
    private final T value;
    private List<Error> errors;
    private List<Warning> warnings;

    /**
     * Constructs a successful action result of Void.
     */
    private SimpleActionResult(
        T value,
        List<Error> errors,
        List<Warning> warnings
    ) {
        this.value = value;
        if (errors != null) {
            this.errors = Collections.unmodifiableList(errors);
        }
        if (warnings != null) {
            this.warnings = Collections.unmodifiableList(warnings);
        }
    }

    /**
     * Creates a successful action result.
     *
     * @return The new action result.
     */
    public static SimpleActionResult<Void> createSuccessful() {
        return new SimpleActionResult<Void>(null, null, null);
    }

    /**
     * Creates a successful action result.
     *
     * @param warnings Warnings of the action.
     * @return The new action result.
     */
    public static SimpleActionResult<Void> createSuccessful(List<Warning> warnings) {
        return new SimpleActionResult<Void>(null, null, warnings);
    }

    /**
     * Creates a successful action result.
     *
     * @param value Value produced as a result of the action.
     * @return The new action result.
     */
    public static <T> SimpleActionResult<T> createSuccessful(T value) {
        return new SimpleActionResult<T>(value, null, null);
    }

    /**
     * Creates a successful action result.
     *
     * @param value    Value produced as a result of the action.
     * @param warnings Warnings of the action.
     * @return The new action result.
     */
    public static <T> SimpleActionResult<T> createSuccessful(
        T value,
        List<Warning> warnings
    ) {
        return new SimpleActionResult<T>(value, null, warnings);
    }

    /**
     * Creates an unsuccessful result.
     *
     * @param errors Errors that caused the action to be unsuccessful.
     * @return The new action result.
     */
    public static <T> SimpleActionResult<T> createUnsuccessful(List<Error> errors) {
        return createUnsuccessful(errors, null);
    }

    /**
     * Creates an unsuccessful result.
     *
     * @param errors   Errors that caused the action to be unsuccessful.
     * @param warnings Warnings of the action.
     * @return The new action result.
     */
    public static <T> SimpleActionResult<T> createUnsuccessful(
        List<Error> errors,
        List<Warning> warnings
    ) {
        Objects.requireNonNull(
            errors,
            "Must provide a list of errors for the unsuccessful result"
        );
        if (errors.isEmpty()) {
            throw new IllegalArgumentException(
                "An unsuccessful action result must have at least one error.");
        }
        return new SimpleActionResult<T>(null, errors, warnings);
    }


    @Override
    public T get() {
        return value;
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errs = errors;
        return errs == null ? (errors = List.of()) : errs;
    }

    @Override
    public List<Warning> getWarnings() {
        List<Warning> warns = warnings;
        return warns == null ? (warns = List.of()) : warns;
    }

    @Override
    public boolean isSuccessful() {
        return errors == null || errors.isEmpty();
    }
}
