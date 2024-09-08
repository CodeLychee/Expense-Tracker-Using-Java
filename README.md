# Expense-Tracker-Using-Java
**Expense Tracker** is a Java-based desktop application that allows users to manage and monitor their expenses efficiently. The application features a simple and intuitive graphical user interface (GUI) for tracking expenses, managing user accounts, and organizing expenses by category.

## Features

- **User Registration & Login:**
  - Users can create accounts to personalize and secure their expense data.
  - Login functionality with password visibility toggle and "Forgot Password" feature.
  
- **Expense Entry:**
  - Input expense details including date, category, and amount.

- **Expensed Listing:**
  - View all entered expenses in a clear and organized manner.
  - Sort and filter expenses by date and category.

- **Category-wise Expenditure:**
  - Calculate and display the total expenses for each category.

- **Persistence:**
  - Save and load expense data to and from files, ensuring that user data is preserved across sessions.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 8 or above**
- **An Integrated Development Environment (IDE)** like IntelliJ IDEA, Eclipse, or NetBeans.

### Installation

1. **Clone the repository:**
   ```sh
   https://github.com/CodeLychee/Expense-Tracker-Using-Java.git
   ```
2. **Navigate to the project directory:**
   ```sh
   cd expense-tracker
   ```
3. **Compile and run the project:**
   - Open the project in your preferred IDE.
   - Run the `ExpenseManager` class.

### Running the Application

1. **Register a New User:**
   - Upon launching the application, you'll be prompted to register a new user.
   - Enter your username and password to create an account.

2. **Login:**
   - Use your registered credentials to log in.
   - If you forget your password, click the "Forgot Password" button, enter your username, and retrieve your password.

3. **Add Expenses:**
   - After logging in, you can add new expenses by clicking the "Add Expense" button.
   - Fill in the category, amount, and date fields, and click "OK" to save the expense.

4. **View Expenses:**
   - Click "List Expenses" to see all your recorded expenses.

5. **Category-wise Summation:**
   - Click "Category-wise Sum" to view the total expenses grouped by category.

6. **Save & Exit:**
   - Save your data and exit the application by clicking "Save & Exit."

### Project Structure

- **`ExpenseManager.java`**: The main class that handles the GUI and core functionality.
- **`Expense.java`**: Represents an individual expense, including the category, amount, and date.
- **`UserInfo.java`**: Manages user accounts, including their credentials and associated expenses.

### Screenshots

![Login Screen](link-to-screenshot1)
*Login Screen*

![Main Dashboard](link-to-screenshot2)
*Main Dashboard*

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your changes. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or issues, feel free to reach out via [Gmail](mailto:watermelonpumpkin67@gmail.com).

