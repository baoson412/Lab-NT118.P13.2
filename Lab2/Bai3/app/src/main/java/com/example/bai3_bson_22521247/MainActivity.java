package com.example.bai3_bson_22521247;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

//import các class
import com.example.bai3_bson_22521247.employee.Employee;
import com.example.bai3_bson_22521247.employee.EmployeeFullTime;
import com.example.bai3_bson_22521247.employee.EmployeePartTime;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMaNV, editTextTenNV;
    private RadioGroup radioGroupLoaiNV;
    private Button buttonNhapNV;
    private ListView listViewNV;
    private ArrayList<Employee> danhSachNhanVien;
    private ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các View từ layout XML
        editTextMaNV = findViewById(R.id.editTextMaNV);
        editTextTenNV = findViewById(R.id.editTextTenNV);
        radioGroupLoaiNV = findViewById(R.id.radioGroupLoaiNV);
        buttonNhapNV = findViewById(R.id.buttonNhapNV);
        listViewNV = findViewById(R.id.listViewNV);

        // Khởi tạo ArrayList và ArrayAdapter
        danhSachNhanVien = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, danhSachNhanVien);
        listViewNV.setAdapter(adapter);

        // Xử lý sự kiện khi bấm nút "Nhập NV"
        buttonNhapNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV = editTextMaNV.getText().toString();
                String tenNV = editTextTenNV.getText().toString();
                int selectedLoaiNVId = radioGroupLoaiNV.getCheckedRadioButtonId();

                Employee nhanVien;
                if (selectedLoaiNVId == R.id.radioButtonChinhThuc) {
                    nhanVien = new EmployeeFullTime(maNV, tenNV);
                } else {
                    nhanVien = new EmployeePartTime(maNV, tenNV);
                }

                // Thêm nhân viên vào danh sách và cập nhật ListView
                danhSachNhanVien.add(nhanVien);
                adapter.notifyDataSetChanged();

                // Xóa dữ liệu sau khi nhập
                editTextMaNV.setText("");
                editTextTenNV.setText("");
                radioGroupLoaiNV.clearCheck();
            }
        });
    }
}
