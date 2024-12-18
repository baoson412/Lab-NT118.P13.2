package com.example.bai3_bson_22521247.employee;

public class EmployeeFullTime extends Employee {
    private static final double LUONG = 500.0;

    public EmployeeFullTime(String maNV, String tenNV) {
        super(maNV, tenNV);
    }

    @Override
    public double tinhLuong() {
        return LUONG;
    }

    @Override
    public String toString() {
        return "Mã NV: " + maNV + " - Tên NV: " + tenNV + " --> Loại NV: Chính thức = " + tinhLuong();
    }
}
