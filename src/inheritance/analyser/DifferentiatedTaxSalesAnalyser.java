package inheritance.analyser;

import java.util.List;

public final class DifferentiatedTaxSalesAnalyser extends AbstractSalesAnalyser {

    public DifferentiatedTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTotalSales() {
        return super.getTotalSalesWithReducedTaxes() / 1.1 + super.getTotalSalesWithoutReducedTax() / 1.2;
    }

}
