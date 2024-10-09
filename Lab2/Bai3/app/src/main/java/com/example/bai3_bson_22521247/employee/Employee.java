package com.example.bai3_bson_22521247.employee;

public abstract class Employee {
    protected String maNV;
    protected String tenNV;

    public Employee(String maNV, String tenNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
    }

    public abstract double tinhLuong();

    @Override
    public abstract String toString();
}
