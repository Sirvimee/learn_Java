package inheritance.analyser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public sealed abstract class AbstractSalesAnalyser permits FlatTaxSalesAnalyser, TaxFreeSalesAnalyser, DifferentiatedTaxSalesAnalyser {

    protected final List<SalesRecord> records;

    protected AbstractSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    protected abstract Double getTotalSales();

    protected Double getTotalSalesWithoutReducedTax() {
        Double totalSales = 0d;
        for (SalesRecord record : records) {
            if (!record.hasReducedRate()) {
                totalSales += record.getProductPrice() * record.getItemsSold();
            }
        }
        return totalSales;
    }

    protected Double getTotalSalesWithReducedTaxes() {
        Double totalSales = 0d;
        for (SalesRecord record : records) {
            if (record.hasReducedRate()) {
                totalSales += record.getProductPrice() * record.getItemsSold();
            }
        }
        return totalSales;
    }

    protected Double getTotalSalesByProductId(String id) {
        Double totalSales = 0d;
        for (SalesRecord record : records) {
            if (record.getProductId().equals(id)) {
                totalSales += record.getProductPrice() * record.getItemsSold();
            }
        }
        return totalSales;
    }

    public List<String> getTop3PopularItems() {
        Map<String, Integer> salesByProduct = new HashMap<>();
        for (SalesRecord record : records) {
            salesByProduct.put(record.getProductId(), salesByProduct.getOrDefault(record.getProductId(), 0) +
                    record.getItemsSold());
        }

        return salesByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String getIdOfItemWithLargestTotalSales() {
        Map<String, Double> salesByProduct = new HashMap<>();
        for (SalesRecord record : records) {
            salesByProduct.put(record.getProductId(), salesByProduct.getOrDefault(record.getProductId(), 0d) +
                    record.getProductPrice() * record.getItemsSold());
        }

        return salesByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
