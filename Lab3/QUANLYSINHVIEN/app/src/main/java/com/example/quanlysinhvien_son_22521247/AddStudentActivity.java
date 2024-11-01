package com.example.quanlysinhvien_son_22521247;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etName, etAge;
    private Button btnSaveStudent;
    private DatabaseHelper dbHelper;
    private StudentAdapter studentAdapter; // Tham chiếu đến Adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Hiển thị nút quay lại
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);
        dbHelper = new DatabaseHelper(this);

        // Lấy tham chiếu Adapter từ Intent
        studentAdapter = (StudentAdapter) getIntent().getSerializableExtra("studentAdapter");

        btnSaveStudent.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String ageText = etAge.getText().toString().trim();

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                int age = Integer.parseInt(ageText);
                long studentId = dbHelper.addStudent(name, age); // Lưu thông tin và nhận ID

                if (studentId != -1) { // Kiểm tra nếu thêm thành công
                    // Thêm sinh viên mới vào Adapter và thông báo RecyclerView cập nhật
                    Student newStudent = new Student((int) studentId, name, age);
                    studentAdapter.addStudent(newStudent); // Phương thức thêm sinh viên vào Adapter
                    Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
                    finish(); // Đóng Activity và quay lại màn hình chính
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Đóng Activity và quay lại màn hình chính khi ấn nút quay lại trên ActionBar
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
