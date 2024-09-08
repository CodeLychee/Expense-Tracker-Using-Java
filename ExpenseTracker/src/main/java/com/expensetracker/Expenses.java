package com.expensetracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Expenses implements Serializable {
    private final String category;
    private final double amount;
    private final Date date;

    public Expenses(String category, double amount, Date date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Category: " + category + ", Amount: " + amount + ", Date: " + dateFormat.format(date);
    }
}
