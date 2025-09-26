package com.expensetracker;

import com.expensetracker.gui.ExpenseTrackerGUI;
import com.expensetracker.util.DatabaseConnection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Test database connection
            DatabaseConnection.getConnection();
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            System.out.println("Starting Expense Tracker Application...");
            // Launch the GUI on the Event Dispatch Thread
            javax.swing.SwingUtilities.invokeLater(() -> {
                new ExpenseTrackerGUI().setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
