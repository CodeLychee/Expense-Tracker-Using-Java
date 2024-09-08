package com.expensetracker;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class ExpenseTracker {
    private Map<String, UserInfo> users = new HashMap<>();
    private UserInfo currentUser;

    public ExpenseTracker() {
        loadData();
        createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new CardLayout());
        JPanel loginPanel = createLoginPanel(panel);
        JPanel mainPanel = createMainPanel(panel);
        
        panel.add(loginPanel, "Login");
        panel.add(mainPanel, "Main");

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createLoginPanel(JPanel panel) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(20);
        
        JCheckBox showPassword = new JCheckBox("Show Password");
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton forgotPasswordButton = new JButton("Forgot Password");

        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passLabel);
        loginPanel.add(passText);
        loginPanel.add(showPassword);  
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginPanel.add(forgotPasswordButton);

        // ActionListener for Show Password
        showPassword.addActionListener((ActionEvent e) -> {
            if (showPassword.isSelected()) {
                passText.setEchoChar((char) 0);  
            } else {
                passText.setEchoChar('*');  
            }
        });

        /** ActionListener for Forgot Password
		* -> checks whether the user exist or not
		* -> if the user exist then the user can retrieve the password
		*/
        forgotPasswordButton.addActionListener((var e) -> {
            String username = JOptionPane.showInputDialog(panel, "Enter your username:", "Forgot Password", JOptionPane.QUESTION_MESSAGE);
            if (username != null && !username.trim().isEmpty()) {
                UserInfo user = users.get(username);
                if (user != null) {
                    JOptionPane.showMessageDialog(panel, "Your password is: " + user.getPassword(), "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "User not found.", "Forgot Password", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //  ActionListener for login
        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            currentUser = loginUser(username, password);
            if (currentUser != null) {
                CardLayout cl = (CardLayout) (panel.getLayout());
                cl.show(panel, "Main");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid login. Try again.");
            }
        });

        // ActionListener for User Registration
        registerButton.addActionListener((var e) -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            registerUser(username, password);
        });

        return loginPanel;
    }

    private JPanel createMainPanel(JPanel panel) {
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));

        JButton addExpenseButton = new JButton("Add Expense");
        JButton listExpenseButton = new JButton("List Expensed");
        JButton categorySumButton = new JButton("Category-wise Expenditure");
        JButton saveExitButton = new JButton("Save & Exit");

        mainPanel.add(addExpenseButton);
        mainPanel.add(listExpenseButton);
        mainPanel.add(categorySumButton);
        mainPanel.add(saveExitButton);

        addExpenseButton.addActionListener((var e) -> {
            JPanel expensePanel = new JPanel(new GridLayout(3, 2));
            JTextField categoryField = new JTextField(20);
            JTextField amountField = new JTextField(20);
            
            expensePanel.add(new JLabel("Category:"));
            expensePanel.add(categoryField);
            expensePanel.add(new JLabel("Amount:"));
            expensePanel.add(amountField);
            
            int result = JOptionPane.showConfirmDialog(panel, expensePanel,
                    "Enter Expense", JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());
                addExpense(currentUser, category, amount, new Date());
            }
        });

        listExpenseButton.addActionListener((ActionEvent e) -> {
            StringBuilder expensesList = new StringBuilder();
            for (Expense expense : currentUser.getExpenses()) {
                expensesList.append(expense).append("\n");
            }
            JOptionPane.showMessageDialog(panel, expensesList.toString(),
                    "List of Expensed", JOptionPane.INFORMATION_MESSAGE);
        });

        categorySumButton.addActionListener((ActionEvent e) -> {
            Map<String, Double> categorySum = new HashMap<>();
            for (Expense expense : currentUser.getExpenses()) {
                categorySum.put(expense.getCategory(),
                        categorySum.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
            }
            
            StringBuilder categorySumList = new StringBuilder();
            categorySum.forEach((category, sum) ->
                    categorySumList.append(category).append(": ").append(sum).append("\n"));
            
            JOptionPane.showMessageDialog(panel, categorySumList.toString(),
                    "Category-wise Expenditure", JOptionPane.INFORMATION_MESSAGE);
        });

        saveExitButton.addActionListener((ActionEvent e) -> {
            saveData();
            System.exit(0);
        });

        return mainPanel;
    }

    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "User already exists.");
        } else {
            users.put(username, new UserInfo(username, password));
            JOptionPane.showMessageDialog(null, "User registered successfully.");
        }
    }

    public UserInfo loginUser(String username, String password) {
        UserInfo user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful.");
            return user;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.");
            return null;
        }
    }

    public void addExpense(UserInfo user, String category, double amount, Date date) {
        user.addExpense(new Expense(category, amount, date));
        JOptionPane.showMessageDialog(null, "Expense added successfully.");
    }

    public void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("expenseData.dat"))) {
            out.writeObject(users);
            JOptionPane.showMessageDialog(null, "Data saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("expenseData.dat"))) {
            users = (Map<String, UserInfo>) in.readObject();
            JOptionPane.showMessageDialog(null, "Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpenseTracker());
    }
}