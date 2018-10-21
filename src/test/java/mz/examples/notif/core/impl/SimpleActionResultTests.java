package mz.examples.notif.core.impl;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.Error;
import mz.examples.notif.core.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleActionResultTests {

    @Test
    @DisplayName("Can create successful void results")
    void canCreateSuccessfulVoidResults() {
        Consumer<ActionResult<?>> commonChecks = result -> {
            assertTrue(result.isSuccessful());
            assertFalse(result.isUnsuccessful());
            assertTrue(result.getErrors().isEmpty());
            assertNull(result.get());
        };

        // Without warnings
        ActionResult<Void> result =
            SimpleActionResult.createSuccessful();
        commonChecks.accept(result);

        // With warnings
        result =
            SimpleActionResult.createSuccessful(
                List.of(Warning.create("warning code", "Warning message"))
            );
        commonChecks.accept(result);
        assertTrue(result.getWarnings().size() == 1);
    }

    @Test
    @DisplayName("Can create successful non void results")
    void canCreateSuccessfulNonVoidResults() {
        Consumer<ActionResult<?>> commonChecks = result -> {
            assertTrue(result.isSuccessful());
            assertFalse(result.isUnsuccessful());
            assertTrue(result.getErrors().isEmpty());
            assertEquals("Some value", result.get());
        };

        // Without warnings
        ActionResult<String> result =
            SimpleActionResult.createSuccessful("Some value");
        commonChecks.accept(result);

        // With warnings
        result =
            SimpleActionResult.createSuccessful(
                "Some value",
                List.of(Warning.create("warning code", "Warning message"))
            );
        commonChecks.accept(result);
        assertTrue(result.getWarnings().size() == 1);
    }

    @Test
    @DisplayName("Can create unsuccessful results")
    void canCreateUnsuccessfulResults() {
        List<Error> errors = List.of(
            Error.create("error.code.1", "Message 1"),
            Error.create("error.code.2", "Message 2")
        );
        Consumer<ActionResult<?>> commonChecks = result -> {
            assertFalse(result.isSuccessful());
            assertTrue(result.isUnsuccessful());
            assertNull(result.get());
            assertIterableEquals(errors, result.getErrors());
        };

        // Without warnings
        ActionResult<String> result = SimpleActionResult.createUnsuccessful(errors);
        commonChecks.accept(result);

        // With warnings
        List<Warning> warnings = List.of(
            Warning.create("warning code", "Warning message")
        );
        result = SimpleActionResult.createUnsuccessful(errors, warnings);
        commonChecks.accept(result);
        assertIterableEquals(warnings, result.getWarnings());
    }

    @Test
    @DisplayName("When unsuccessful, it requires non empty list of errors")
    void whenUnsuccessful_requiresNonEmptyListOfErrors() {
        assertThrows(
            NullPointerException.class,
            () -> ActionResult.createUnsuccessful((List<Error>) null)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> SimpleActionResult.createUnsuccessful(List.of())
        );
    }
}
