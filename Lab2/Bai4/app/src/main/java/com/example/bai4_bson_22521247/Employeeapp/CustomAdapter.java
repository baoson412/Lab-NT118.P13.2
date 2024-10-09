package com.example.bai4_bson_22521247.Employeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai4_bson_22521247.R;//import package chứa R

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Employee> employees;

    public CustomAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_employee, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewRole = convertView.findViewById(R.id.textViewRole);
        ImageView imageViewIcon = convertView.findViewById(R.id.imageViewIcon);

        Employee employee = employees.get(position);

        // Set tên nhân viên
        textViewName.setText(employee.getFullname());

        // Kiểm tra là Manager hay Staff để hiển thị icon hoặc chữ Staff
        if (employee.isManager()) {
            textViewRole.setVisibility(View.GONE);
            imageViewIcon.setVisibility(View.VISIBLE); // Hiện icon Manager
        } else {
            textViewRole.setVisibility(View.VISIBLE);
            textViewRole.setText("Staff");
            imageViewIcon.setVisibility(View.GONE);
        }

        // Đổi màu nền xen kẽ giữa các hàng
        if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }

        return convertView;
    }
}

