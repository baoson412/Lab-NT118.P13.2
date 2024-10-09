package com.example.bai6_bson_22521247.Classes_Folder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai6_bson_22521247.R;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private ArrayList<Employee> employeeList;

    public EmployeeAdapter(Context context, ArrayList<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.textViewName.setText(employee.getFullname());

        if (employee.isManager()) {
            holder.imageViewIcon.setVisibility(View.VISIBLE);
            holder.textViewRole.setVisibility(View.GONE);
        } else {
            holder.imageViewIcon.setVisibility(View.GONE);
            holder.textViewRole.setVisibility(View.VISIBLE);
            holder.textViewRole.setText("Staff");
        }

        // Đổi màu nền xen kẽ
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewRole;
        ImageView imageViewIcon;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRole = itemView.findViewById(R.id.textViewRole);
            imageViewIcon = itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
