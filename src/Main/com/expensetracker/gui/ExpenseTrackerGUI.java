package com.expensetracker.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.expensetracker.model.category;
import com.expensetracker.model.expense;

import com.expensetracker.DAO.categoryDAO;
import com.expensetracker.DAO.expenseDAO;

public class ExpenseTrackerGUI extends JFrame {
    public ExpenseTrackerGUI() {
        super("Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}