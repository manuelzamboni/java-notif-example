package mz.examples.notif.core.impl;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.ActionResultBuilder;
import mz.examples.notif.core.Error;
import mz.examples.notif.core.Warning;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Simple and default implementation of {@link ActionResultBuilder}.
 *
 * @param <T> Type of value of the result.
 */
public class SimpleActionResultBuilder<T> implements ActionResultBuilder<T> {
    private boolean isVoidResult;
    private T value;
    private ArrayList<Error> errors;
    private ArrayList<Warning> warnings;

    public SimpleActionResultBuilder(Class<T> genericType) {
        isVoidResult = genericType.equals(Void.class);
    }

    @Override
    public SimpleActionResultBuilder<T> setValue(T value) {
        if (errors != null && !errors.isEmpty()) {
            throw new UnsupportedOperationException(
                "Errors are already added to this builder");
        }
        this.value = value;
        return this;
    }

    @Override
    public SimpleActionResultBuilder<T> addError(Error error) {
        Objects.requireNonNull(error, "Must specify an error");
        if (value != null) {
            throw new UnsupportedOperationException(
                "A successful result value is already set");
        }
        getErrors().add(error);
        return this;
    }

    @Override
    public SimpleActionResultBuilder<T> addError(String code, String message) {
        addError(Error.create(code, message));
        return this;
    }

    @Override
    public SimpleActionResultBuilder<T> addError(
        String code, String message, Map<String, Object> references
    ) {
        addError(Error.create(code, message, references));
        return this;
    }

    @Override
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    @Override
    public boolean hasErrors(String code) {
        return errors != null &&
            errors
                .stream()
                .anyMatch(error -> error.getCode().equals(code));
    }

    @Override
    public SimpleActionResultBuilder<T> addWaring(Warning warning) {
        Objects.requireNonNull(warning, "Must specify a warning");
        this.getWarnings().add(warning);
        return this;
    }

    @Override
    public SimpleActionResultBuilder<T> addWarning(String code, String message) {
        addWaring(Warning.create(code, message));
        return this;
    }

    @Override
    public boolean hasWarnings() {
        return warnings != null && !warnings.isEmpty();
    }

    @Override
    public ActionResult<T> build() {
        if (errors == null) {
            if (value == null && !isVoidResult) {
                throw new IllegalStateException("The successful result value has not been set");
            }
            return ActionResult.createSuccessful(value, warnings);
        }
        return ActionResult.createUnsuccessful(errors, warnings);
    }

    @Override
    public ActionResultBuilder<T> reset() {
        value = null;
        errors = null;
        return this;
    }


    private ArrayList<Error> getErrors() {
        ArrayList<Error> errs = errors;
        return errs == null ? (errors = new ArrayList<>()) : errs;
    }

    private ArrayList<Warning> getWarnings() {
        ArrayList<Warning> warns = warnings;
        return warns == null ? (warnings = new ArrayList<>()) : warns;
    }
}

