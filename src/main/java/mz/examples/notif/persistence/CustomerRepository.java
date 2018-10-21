package mz.examples.notif.persistence;

import mz.examples.notif.service.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents
 */
public class CustomerRepository {

    // Some preexisting customers
    private static List<Customer> customers = List.of(
        new Customer(1, "ABC-123", "Jane Doe"),
        new Customer(2, "XYZ-456", "John Doe")
    );

    public int nextId() {
        Optional<Customer> maxIdCustomer = customers
            .stream()
            .max(Comparator.comparingInt(Customer::getId));

        return (maxIdCustomer.isPresent() ? maxIdCustomer.get().getId() : 0) + 1;
    }

    /**
     * Finds a preexisting customer by her code.
     *
     * @return Customer found or null.
     */
    public Customer findByCode(String customerCode) {
        Optional<Customer> optional = customers
            .stream()
            .filter(customer -> customer.getCode().equals(customerCode))
            .findFirst();

        return optional.isPresent() ? optional.get() : null;
    }

    public void persist(Customer customer) {
        // Throws when our API is used incorrectly
        Objects.requireNonNull(customer, "You must specify the customer to persist");

        // Persist to the database (maybe through our ORM)...
        System.out.printf("The customer %s was persisted\n", customer.getName());
    }
}
