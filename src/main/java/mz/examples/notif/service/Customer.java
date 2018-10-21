package mz.examples.notif.service;

import mz.examples.notif.core.NotifsCollector;

/**
 * Customer entity.
 */
public class Customer {
    private static final String CODE_REGEX = "[A-Z]{3}-[0-9]{3}";

    private int id;
    private String code;
    private String name;

    public Customer() {
    }

    public Customer(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public void init(NewCustomerData data, NotifsCollector notifsCollector) {
        validateCode(data.getCode(), notifsCollector);
        validateName(data.getName(), notifsCollector);

        // Another option would be to set a property only when valid (maybe
        // refactoring the validate* methods so they return a boolean)
        this.code = data.getCode();
        this.name = data.getName();
    }


    //// Validations in the instance scope ////
    private void validateCode(String code, NotifsCollector notifsCollector) {
        if (code == null || code.isEmpty()) {
            notifsCollector.addError(ErrorsFactory.requiredProperty("code"));
        } else if (!code.matches(CODE_REGEX)) {
            notifsCollector.addError(ErrorsFactory.invalidCustomerCode(code));
        }
    }

    private void validateName(String name, NotifsCollector notifsCollector) {
        if (name == null || name.trim().isEmpty()) {
            notifsCollector.addError(ErrorsFactory.customerMustHaveAName());
        }
    }
}
