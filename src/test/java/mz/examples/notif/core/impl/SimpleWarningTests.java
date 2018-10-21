package mz.examples.notif.core.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleWarningTests {

    @Test
    @DisplayName("It's correctly initialized")
    void itsCorrectlyInitialized() {
        SimpleWarning warning = new SimpleWarning("some.code", "Some message");

        assertEquals("some.code", warning.getCode());
        assertEquals("Some message", warning.getMessage());
    }

    @Test
    @DisplayName("Requires not null arguments in constructor")
    void requiresNotNullArgsInConstructors() {
        assertThrows(NullPointerException.class, () -> {
            new SimpleWarning("some.code", null);
        });
        assertThrows(NullPointerException.class, () -> {
            new SimpleWarning(null, "Some message");
        });
    }
}
