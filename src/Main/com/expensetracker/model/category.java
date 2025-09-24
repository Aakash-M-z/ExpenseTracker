package com.expensetracker.model;
import java.util.Date;

public class category {
    private int id;
    private String name;
    private Date createdAt;
    
    public  category(int id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }   
}
