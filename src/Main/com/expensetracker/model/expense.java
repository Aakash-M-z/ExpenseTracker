package com.expensetracker.model;
import java.util.Date;

public class expense {
    private int id;
    private int categoryId;
    private int amount;
    private Date createdAt;
    private String description;

    public expense(int id, int categoryId, int amount, Date createdAt, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public int getAmount() {
        return amount;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public String getDescription() {
        return description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
