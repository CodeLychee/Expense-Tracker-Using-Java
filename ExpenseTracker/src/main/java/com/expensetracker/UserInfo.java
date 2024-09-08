package com.expensetracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {
    private final String username;
    private final String password;
    private final List<Expenses> expenses;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void addExpense(Expenses expense) {
        expenses.add(expense);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}

