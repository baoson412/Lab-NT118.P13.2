package com.example.bai4_bson_22521247;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bai4_bson_22521247.Employeeapp.CustomAdapter;
import com.example.bai4_bson_22521247.Employeeapp.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextFullname;
    private CheckBox checkBoxManager;
    private Button buttonAdd;
    private ListView listViewEmployee;
    private ArrayList<Employee> employeeList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view
        editTextId = findViewById(R.id.editTextId);
        editTextFullname = findViewById(R.id.editTextFullname);
        checkBoxManager = findViewById(R.id.checkBoxManager);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewEmployee = findViewById(R.id.listViewEmployee);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, employeeList);
        listViewEmployee.setAdapter(customAdapter);

        // Xử lý sự kiện nút "Add"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String fullname = editTextFullname.getText().toString();
                boolean isManager = checkBoxManager.isChecked();

                // Tạo đối tượng Employee và thêm vào danh sách
                Employee employee = new Employee(id, fullname, isManager);
                employeeList.add(employee);
                customAdapter.notifyDataSetChanged();

                // Xóa dữ liệu sau khi nhập
                editTextId.setText("");
                editTextFullname.setText("");
                checkBoxManager.setChecked(false);
            }
        });
    }
}
