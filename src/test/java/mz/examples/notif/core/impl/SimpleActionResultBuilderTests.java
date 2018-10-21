package mz.examples.notif.core.impl;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleActionResultBuilderTests {
    @Test
    @DisplayName("Builds a successful result")
    void buildsASuccessfulResult() {
        // Non void result
        ActionResult<String> stringResult =
            new SimpleActionResultBuilder<String>(String.class)
                .setValue("A successful result")
                .addWarning("warning code", "Warning message")
                .build();

        assertEquals("A successful result", stringResult.get());
        assertEquals("warning code", stringResult.getWarnings().get(0).getCode());

        // Void result
        ActionResult<Void> voidResult =
            new SimpleActionResultBuilder<Void>(Void.class)
                .build();

        assertNull(voidResult.get());
        assertTrue(voidResult.isSuccessful());
        assertEquals("warning code", stringResult.getWarnings().get(0).getCode());
    }

    @Test
    @DisplayName("Builds an unsuccessful result")
    void buildsAnUnsuccessfulResult() {
        ActionResult<String> result = new SimpleActionResultBuilder<String>(String.class)
            .addError("error1", "Error message 1")
            .addError("error2", "Error message 2", Map.of("ref", "reference value"))
            .addWarning("warning1", "Warning message 1")
            .build();

        List<Error> errors = result.getErrors();
        assertTrue(errors.size() == 2);
        Error error1 = errors.get(0);
        Error error2 = errors.get(1);
        assertEquals("error1", error1.getCode());
        assertEquals("error2", error2.getCode());
        assertEquals("reference value", error2.getReferences().get("ref"));
        assertEquals("warning1", result.getWarnings().get(0).getCode());
    }

    @Test
    @DisplayName("Checks unsupported operations")
    void checksUnsupportedOperations() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new SimpleActionResultBuilder<String>(String.class)
                .setValue("Successful result value")
                .addError("error code", "This error must be rejected");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            new SimpleActionResultBuilder<String>(String.class)
                .addError("error code", "Error message")
                .setValue("This successful value must be rejected");
        });
    }

    @Test
    @DisplayName("Requires to set the value of a successful non void result")
    void requiresValueOfASuccessfulNonVoidResult() {
        assertThrows(IllegalStateException.class, () -> {
            new SimpleActionResultBuilder<String>(String.class)
                .build();
        });
    }

    @Test
    @DisplayName("Can tell if errors have been collected")
    void canTellErrorsHaveBeenCollectedOrNot() {
        SimpleActionResultBuilder<String> builder = new SimpleActionResultBuilder<String>(String.class);
        assertFalse(builder.hasErrors());
        assertFalse(builder.hasErrors("error code"));

        builder.addError("error code", "Error message");
        assertTrue(builder.hasErrors());
        assertTrue(builder.hasErrors("error code"));
        assertFalse(builder.hasErrors("non existing code"));
    }


    @Test
    @DisplayName("Resets to its initial state")
    void resetsToItsInitialState() {
        SimpleActionResultBuilder<String> builder =
            new SimpleActionResultBuilder<String>(String.class);
        builder.addError("error code", "Error message");

        builder.reset();

        ActionResult<String> successfulResult = builder
            .setValue("First result, successful")
            .build();
        assertTrue(successfulResult.isSuccessful());
        assertFalse(builder.hasErrors());
    }
}
