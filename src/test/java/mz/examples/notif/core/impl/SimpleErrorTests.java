package mz.examples.notif.core.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleErrorTests {

    @Test
    @DisplayName("It's correctly initialized")
    void itsCorrectlyInitialized() {
        SimpleError error = new SimpleError("some.code", "Some message");

        assertEquals("some.code", error.getCode());
        assertEquals("Some message", error.getMessage());
        assertTrue(error.getReferences().isEmpty());

        Map<String, String> references = Map.of("ref1", "value 1");
        error = new SimpleError("some.code", "Some message", references);
        assertTrue(references == error.getReferences());
    }

    @Test
    @DisplayName("Requires not null arguments in constructors")
    void requiresNotNullArgsInConstructors() {
        assertThrows(NullPointerException.class, () -> {
            new SimpleError("some.code", null);
        });
        assertThrows(NullPointerException.class, () -> {
            new SimpleError(null, "Some message");
        });
        assertThrows(NullPointerException.class, () -> {
            new SimpleError("some.code", "Some message", null);
        });
    }
}
