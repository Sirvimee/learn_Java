package inheritance.analyser;

import java.util.*;

public final class FlatTaxSalesAnalyser extends AbstractSalesAnalyser {

    public FlatTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected Double getTotalSales() {
        return (super.getTotalSalesWithoutReducedTax() + super.getTotalSalesWithReducedTaxes()) / 1.2;
    }

    @Override
    protected Double getTotalSalesByProductId(String id) {
        return super.getTotalSalesByProductId(id) / 1.2;
    }

}
