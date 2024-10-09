package com.example.bai6_bson_22521247;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bai6_bson_22521247.Classes_Folder.Employee;
import com.example.bai6_bson_22521247.Classes_Folder.EmployeeAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextId, editTextFullname;
    private CheckBox checkBoxManager;
    private Button buttonAdd;
    private RecyclerView recyclerViewEmployee;
    private ArrayList<Employee> employeeList;
    private EmployeeAdapter employeeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view
        editTextId = findViewById(R.id.editTextId);
        editTextFullname = findViewById(R.id.editTextFullname);
        checkBoxManager = findViewById(R.id.checkBoxManager);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerViewEmployee = findViewById(R.id.recyclerViewEmployee);

        // Khởi tạo danh sách và adapter
        employeeList = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(this, employeeList);

        // Thiết lập RecyclerView
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployee.setAdapter(employeeAdapter);

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
                employeeAdapter.notifyDataSetChanged();

                // Xóa dữ liệu sau khi nhập
                editTextId.setText("");
                editTextFullname.setText("");
                checkBoxManager.setChecked(false);
            }
        });
    }
}
