package com.example.bai6_bson_22521247.Classes_Folder;

public class Employee {
    private String id;
    private String fullname;
    private boolean isManager;

    public Employee(String id, String fullname, boolean isManager) {
        this.id = id;
        this.fullname = fullname;
        this.isManager = isManager;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public boolean isManager() {
        return isManager;
    }
}