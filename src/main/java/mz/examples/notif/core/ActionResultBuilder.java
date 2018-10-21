package mz.examples.notif.core;

import mz.examples.notif.core.impl.SimpleActionResultBuilder;

/**
 * Allows you to build an {@link ActionResult} object in a progressive fashion.
 */
public interface ActionResultBuilder<T> extends NotifsCollector {
    /**
     * Sets the value of the successful result to be. If errors were
     * added, it will throw an {@link UnsupportedOperationException}.
     *
     * @param value Value of the successful result to build.
     * @return This same instance.
     */
    ActionResultBuilder<T> setValue(T value);

    /**
     * Builds the action result.
     *
     * @return Action result initialized according to the current builder's
     * configuration.
     */
    ActionResult<T> build();

    /**
     * Reestablishes the builder to its initial state.
     *
     * @return This same instance.
     */
    ActionResultBuilder<T> reset();

    /**
     * Creates a default implementation of {@link ActionResultBuilder}
     *
     * @param <T> Type argument of the default implementation to create.
     * @return Created implementation.
     */
    static <T> ActionResultBuilder<T> create(Class<T> genericType) {
        return new SimpleActionResultBuilder<T>(genericType);
    }
}
