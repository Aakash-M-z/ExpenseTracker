package com.expensetracker.model;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Expense {
    private int id;
    private String title;
    private String description;
    private BigDecimal amount;
    private int categoryId;
    private String categoryName;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // Default constructor
    public Expense() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    // Constructor with all fields
    public Expense(int id, String title, String description, BigDecimal amount, int categoryId, String categoryName,
            LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
