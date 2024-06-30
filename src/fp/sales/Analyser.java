package fp.sales;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Analyser {

    private final Repository repository;

    public Analyser(Repository repository) {
        this.repository = repository;
    }

    public Double getTotalSales() {
        double sum = repository.getEntries().stream()
                .mapToDouble(Entry::getAmount)
                .sum();

        return sum;
    }

    public Double getSalesByCategory(String category) {
        double sum = repository.getEntries().stream()
                .filter(entry -> entry.getCategory().equals(category))
                .mapToDouble(Entry::getAmount)
                .sum();

        return sum;
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        double sum = repository.getEntries().stream()
                .filter(entry -> entry.getDate().isAfter(start) && entry.getDate().isBefore(end))
                .mapToDouble(Entry::getAmount)
                .sum();

        return sum;
    }

    public String mostExpensiveItems() {
        String items = repository.getEntries().stream()
                .sorted((e1, e2) -> e2.getAmount().compareTo(e1.getAmount()))
                .limit(3)
                .sorted(Comparator.comparing(Entry::getProductId))
                .map(Entry::getProductId)
                .collect(Collectors.joining(", "));

        return items;
    }

    public String statesWithBiggestSales() {
        String states = repository.getEntries().stream()
                .collect(Collectors.groupingBy(Entry::getState, Collectors.summingDouble(Entry::getAmount)))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .map(e -> e.getKey())
                .collect(Collectors.joining(", "));

        return states;
    }
}
