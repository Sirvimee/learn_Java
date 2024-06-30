package fp.sales;

import java.time.LocalDate;

public class Entry {

    private String productId;
    private LocalDate date;
    private String state;
    private String category;
    private Double amount;

    public Entry(LocalDate date, String state, String productId, String category, double sales) {
        this.date = date;
        this.state = state;
        this.productId = productId;
        this.category = category;
        this.amount = sales;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
