package task1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    public enum Type { INCOME, EXPENSE }

    private Type type;
    private double amount;
    private String category;
    private Date date;

    public Transaction(Type type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public Transaction(Type type, double amount, String category) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = new Date();
    }

    public Transaction(Type type, double amount, String category, Date date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public Type getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public Date getDate() { return date; }

    public String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setAmount(double amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(Date date) { this.date = date; }
}
