package mz.examples.notif.core.impl;

import mz.examples.notif.core.Error;

import java.util.Map;
import java.util.Objects;

/**
 * Simple and default implementation of an {@link Error}
 */
public class SimpleError implements Error {
    private String codigoError;
    private String mensaje;
    private Map<String, ?> references;

    public SimpleError(
        String codigoError,
        String mensaje
    ) {
        Objects.requireNonNull(codigoError, "Must provide an error code");
        Objects.requireNonNull(mensaje, "Must provide an error message");

        this.codigoError = codigoError;
        this.mensaje = mensaje;
    }

    public SimpleError(
        String codigoError,
        String mensaje,
        Map<String, ?> references
    ) {
        this(codigoError, mensaje);
        Objects.requireNonNull(references, "Must provide error references");

        this.references = references;
    }

    @Override
    public String getCode() {
        return codigoError;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

    @Override
    public Map<String, ?> getReferences() {
        Map<String, ?> r = references;
        return r == null ? (references = Map.of()) : r;
    }
}
