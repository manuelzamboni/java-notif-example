package mz.examples.notif.service;

import mz.examples.notif.core.Error;

import java.util.Map;
import java.util.Objects;

import static mz.examples.notif.service.ErrorCodes.*;

/**
 * Factory of {@link Error} objects.
 */
public final class ErrorsFactory {

    public final static Error requiredProperty(String propertyName) {
        Objects.requireNonNull(propertyName);

        return Error.create(
            REQUIRED_PROPERTY,
            String.format("\"%s\" is a required property", propertyName),
            Map.of("property", propertyName)
        );
    }

    public final static Error invalidCustomerCode(String customerCode) {
        Objects.requireNonNull(customerCode);

        return Error.create(
            INVALID_CUSTOMER_CODE,
            String.format("\"%s\" is an invalid customer code", customerCode)
        );
    }

    public final static Error customerMustHaveAName() {
        return Error.create(
            CUSTOMER_MUST_HAVE_A_NAME,
            "You must specify the customer's name"
        );
    }

    public final static Error duplicatedCustomerCode(String code) {
        Objects.requireNonNull(code);

        return Error.create(
            DUPLICATED_CUSTOMER_CODE,
            "Duplicated customer code",
            Map.of("code", code)
        );
    }
}
