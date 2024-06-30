package poly.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();

    public Optional<AbstractCustomer> getCustomerById(String id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

            for (String line : lines) {
                String[] parts = line.split(";");
                String type = parts[0];
                String customerId = parts[1];
                String name = parts[2];
                int bonusPoints = Integer.parseInt(parts[3]);

                if (customerId.equals(id)) {
                    if (type.equals("GOLD")) {
                        return Optional.of(new GoldCustomer(customerId, name, bonusPoints));
                    } else {
                        LocalDate date = LocalDate.parse(parts[4]);
                        return Optional.of(new RegularCustomer(id, name, bonusPoints, date));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void remove(String id) {
        Optional<AbstractCustomer> customer = getCustomerById(id);
        customer.ifPresent(customers::remove);

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            List<String> newFileContent = new ArrayList<>();
            for (String line : lines) {
                String[] parts = line.split(";");
                String customerId = parts[1];
                if (!customerId.equals(id)) {
                    newFileContent.add(line);
                }
            }

            Files.write(Paths.get(FILE_PATH), newFileContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to remove customer data from file: " + e.getMessage());
        }
    }

    public void save(AbstractCustomer customer) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            StringBuilder newFileContent = new StringBuilder();
            boolean customerExists = false;

            for (String line : lines) {
                String[] parts = line.split(";");
                String customerId = parts[1];

                if (customerId.equals(customer.getId())) {
                    String customerData = getData(customer);
                    newFileContent.append(customerData);
                    customerExists = true;
                } else {
                    newFileContent.append(line).append(System.lineSeparator());
                }
            }

            if (!customerExists) {
                String customerData = getData(customer);
                newFileContent.append(customerData);
            }

            Files.write(Paths.get(FILE_PATH), newFileContent.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save customer data to file: " + e.getMessage());
        }
    }

    private static String getData(AbstractCustomer customer) {
        String customerData;
        if (customer instanceof RegularCustomer regularCustomer) {
            customerData = "REGULAR;" +
                    regularCustomer.getId() + ";" +
                    regularCustomer.getName() + ";" +
                    regularCustomer.getBonusPoints() + ";" +
                    regularCustomer.getLastOrderDate() + System.lineSeparator();
        } else if (customer instanceof GoldCustomer) {
            customerData = "GOLD;" +
                    customer.getId() + ";" +
                    customer.getName() + ";" +
                    customer.getBonusPoints() + ";" +
                    System.lineSeparator();
        } else {
            throw new IllegalArgumentException("Unknown customer type");
        }
        return customerData;
    }

    public int getCustomerCount() {
        return customers.size();
    }
}