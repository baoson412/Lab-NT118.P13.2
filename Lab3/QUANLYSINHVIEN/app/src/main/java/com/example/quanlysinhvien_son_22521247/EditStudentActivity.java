package com.example.quanlysinhvien_son_22521247;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    private EditText etName, etAge;
    private Button btnSaveChanges;
    private DatabaseHelper dbHelper;
    private StudentAdapter studentAdapter; // Tham chiếu đến Adapter
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etName = findViewById(R.id.etNameEditStudent);
        etAge = findViewById(R.id.etAgeEditStudent);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        dbHelper = new DatabaseHelper(this);

        // Lấy dữ liệu từ Intent
        studentId = getIntent().getIntExtra("student_id", -1);
        String studentName = getIntent().getStringExtra("student_name");
        int studentAge = getIntent().getIntExtra("student_age", -1);

        // Hiển thị thông tin sinh viên hiện tại
        etName.setText(studentName);
        etAge.setText(String.valueOf(studentAge));

        // Lấy tham chiếu Adapter từ Intent
        studentAdapter = (StudentAdapter) getIntent().getSerializableExtra("studentAdapter");

        btnSaveChanges.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String ageText = etAge.getText().toString().trim();

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                int age = Integer.parseInt(ageText);
                dbHelper.updateStudent(studentId, name, age); // Cập nhật thông tin sinh viên

                // Cập nhật thông tin trong Adapter
                Student updatedStudent = new Student(studentId, name, age);
                studentAdapter.updateStudent(updatedStudent); // Phương thức cập nhật sinh viên trong Adapter

                Toast.makeText(this, "Đã cập nhật thông tin sinh viên", Toast.LENGTH_SHORT).show();
                finish(); // Đóng Activity và quay lại màn hình chính
            }
        });
    }
}