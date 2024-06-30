package inheritance.calc;

public class TaxFreePayCalculator extends PayCalculator {

    @Override
    public Double getTaxRate() {
        return 0.0;
    }
}
