package com.expensetracker.dao;

import com.expensetracker.util.DatabaseConnection;
import com.expensetracker.model.Category;
import com.expensetracker.model.Expense;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.*;
import java.math.BigDecimal;

public class ExpenseTrackerDAO {

    // cat queries
    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM category ORDER BY created_date DESC";
    private static final String INSERT_CATEGORY = "INSERT INTO category(NAME, created_date) VALUES(?, ?)";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE ID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE category SET NAME = ? WHERE ID = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM category WHERE ID = ?";

    // expense queries
    private static final String SELECT_ALL_EXPENSES = "SELECT e.*, c.NAME as category_name FROM expenses e LEFT JOIN category c ON e.C_ID = c.ID ORDER BY e.created_at DESC";
    private static final String INSERT_EXPENSE = "INSERT INTO expenses(description, rupees, C_ID, created_at) VALUES(?, ?, ?, ?)";
    private static final String SELECT_EXPENSE_BY_ID = "SELECT e.*, c.NAME as category_name FROM expenses e LEFT JOIN category c ON e.C_ID = c.ID WHERE e.id = ?";
    private static final String UPDATE_EXPENSE = "UPDATE expenses SET description = ?, rupees = ?, C_ID = ? WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM expenses WHERE id = ?";

    // Cat

    public int createCategory(Category category) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating category failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating category failed, no ID obtained.");
                }
            }
        }
    }

    private Category getCategoryRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        String name = rs.getString("NAME");
        String description = ""; // Your database doesn't have description column
        LocalDateTime createdAt = rs.getTimestamp("created_date").toLocalDateTime();
        LocalDateTime updatedAt = createdAt; // Use same as created since no updated_at column

        return new Category(id, name, description, createdAt, updatedAt);
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);
                ResultSet res = stmt.executeQuery()) {
            while (res.next()) {
                categories.add(getCategoryRow(res));
            }
        }
        return categories;
    }

    public Category getCategoryById(int categoryId) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            stmt.setInt(1, categoryId);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return getCategoryRow(res);
                }
            }
        }
        return null;
    }

    public boolean updateCategory(Category category) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteCategory(int categoryId) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {
            stmt.setInt(1, categoryId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Expense

    public int createExpense(Expense expense) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_EXPENSE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, expense.getDescription());
            stmt.setInt(2, expense.getAmount().intValue());
            stmt.setInt(3, expense.getCategoryId());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating expense failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating expense failed, no ID obtained.");
                }
            }
        }
    }

    private Expense getExpenseRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("description"); // Use description as title since no title column
        String description = rs.getString("description");
        BigDecimal amount = BigDecimal.valueOf(rs.getInt("rupees"));
        int categoryId = rs.getInt("C_ID");
        String categoryName = rs.getString("category_name");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = createdAt; // Use same as created since no updated_at column

        return new Expense(id, title, description, amount, categoryId, categoryName, createdAt, updatedAt);
    }

    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_EXPENSES);
                ResultSet res = stmt.executeQuery()) {
            while (res.next()) {
                expenses.add(getExpenseRow(res));
            }
        }
        return expenses;
    }

    public Expense getExpenseById(int expenseId) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_EXPENSE_BY_ID)) {
            stmt.setInt(1, expenseId);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return getExpenseRow(res);
                }
            }
        }
        return null;
    }

    public boolean updateExpense(Expense expense) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(UPDATE_EXPENSE)) {
            stmt.setString(1, expense.getDescription());
            stmt.setInt(2, expense.getAmount().intValue());
            stmt.setInt(3, expense.getCategoryId());
            stmt.setInt(4, expense.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deleteExpense(int expenseId) throws SQLException {
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(DELETE_EXPENSE)) {
            stmt.setInt(1, expenseId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}