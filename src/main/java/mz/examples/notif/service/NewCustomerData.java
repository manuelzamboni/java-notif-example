package mz.examples.notif.service;

/**
 * Data object to create a new {@link Customer}.
 */
public class NewCustomerData {
    private String code;
    private String name;
    private String lastName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
