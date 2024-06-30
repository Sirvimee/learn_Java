package poly.customer;

import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getTotal() < 100) {
            return;
        }
        double points = order.getTotal() * 1.5;
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
        GoldCustomer that = (GoldCustomer) obj;
        return this.getId().equals(that.getId()) &&
                this.getName().equals(that.getName()) &&
                Objects.equals(this.getBonusPoints(), that.getBonusPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), this.getBonusPoints());
    }

    @Override
    public String asString() {
        return super.toString();
    }
}
