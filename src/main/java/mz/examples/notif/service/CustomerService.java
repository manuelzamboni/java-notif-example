package mz.examples.notif.service;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.ActionResultBuilder;
import mz.examples.notif.core.NotifsCollector;
import mz.examples.notif.persistence.CustomerRepository;

import java.util.Objects;

/**
 * Manages the customers catalog.
 */
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        Objects.requireNonNull(repository, "A repository is mandatory");

        this.repository = repository;
    }

    /**
     * Creates a new customer.
     */
    public ActionResult<Customer> createCustomer(NewCustomerData data) {
        ActionResultBuilder<Customer> resultBuilder =
            ActionResultBuilder.create(Customer.class);

        Customer customer = new Customer();
        customer.init(data, resultBuilder);

        String code = customer.getCode();
        if (code != null && !code.isEmpty() &&
            !resultBuilder.hasErrors(ErrorCodes.INVALID_CUSTOMER_CODE)) {
            this.checkCodeIsUnique(code, resultBuilder);
        }

        if (!resultBuilder.hasErrors()) {
            // No errors, set an Id and persist
            customer.setId(repository.nextId());
            repository.persist(customer);

            resultBuilder.setValue(customer);
        }

        return resultBuilder.build();
    }


    // Validations in the scope of the service
    private void checkCodeIsUnique(String code, NotifsCollector notifsCollector) {
        if (repository.findByCode(code) != null) {
            notifsCollector.addError(ErrorsFactory.duplicatedCustomerCode(code));
        }
    }
}
