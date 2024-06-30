package poly.customer;

import java.time.LocalDate;

public final class RegularCustomer extends AbstractCustomer {


    private final LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;
    }

    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {

        if (order.getTotal() < 100) {
            return;
        }

        double points;

        if (lastOrderDate != null && order.getDate().isAfter(lastOrderDate.plusMonths(1))) {
            points = order.getTotal();
        } else {
            points = order.getTotal() * 1.5;
        }
        this.bonusPoints += points;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegularCustomer that = (RegularCustomer) obj;
        return this.getId().equals(that.getId()) &&
                this.getName().equals(that.getName()) &&
                this.getBonusPoints() == that.getBonusPoints() &&
                this.lastOrderDate.equals(that.lastOrderDate);
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode() + this.getName().hashCode() +
                this.getBonusPoints() + this.lastOrderDate.hashCode();
    }

    @Override
    public String asString() {
        return super.toString();
    }

}