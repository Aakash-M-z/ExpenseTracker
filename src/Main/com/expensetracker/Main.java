package com.expensetracker;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import com.expensetracker.UTIL.DatabaseConnection;
import com.expensetracker.GUI.ExpenseTrackerGUI;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please ensure MySQL is running and the database 'todo' exists");
            return;
        }

        // Launch GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new ExpenseTrackerGUI().setVisible(true);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
    }
}
