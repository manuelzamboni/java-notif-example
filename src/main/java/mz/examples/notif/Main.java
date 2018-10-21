package mz.examples.notif;

import mz.examples.notif.core.ActionResult;
import mz.examples.notif.core.Error;
import mz.examples.notif.persistence.CustomerRepository;
import mz.examples.notif.service.Customer;
import mz.examples.notif.service.CustomerService;
import mz.examples.notif.service.NewCustomerData;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            CustomerService service = createCustomerService();

            // Read data from stdin
            Scanner in = new Scanner(System.in);
            PrintStream out = System.out;

            NewCustomerData data = new NewCustomerData();
            out.print("Customer code: ");
            data.setCode(in.nextLine());
            out.print("Name: ");
            data.setName(in.nextLine());

            // Create customer
            ActionResult<Customer> result = service.createCustomer(data);
            if (result.isSuccessful()) {
                out.println("Customer created! Customer id: " + result.get().getId());
            } else {
                out.println("Something went wrong:");
                for (Error error : result.getErrors()) {
                    out.printf("\t- %s\n", error.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    // This would be solved by our IoC container in a production ready
    // environment
    private static CustomerService createCustomerService() {
        CustomerRepository repository = new CustomerRepository();
        CustomerService service = new CustomerService(repository);

        return service;
    }
}
