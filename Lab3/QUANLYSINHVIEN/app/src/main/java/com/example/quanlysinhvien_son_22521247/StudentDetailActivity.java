package com.example.quanlysinhvien_son_22521247;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView nameTextView, ageTextView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        // Hiển thị nút quay lại
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameTextView = findViewById(R.id.textViewName);
        ageTextView = findViewById(R.id.textViewAge);
        dbHelper = new DatabaseHelper(this);

        int studentId = getIntent().getIntExtra("student_id", -1);
        if (studentId >= 0) {
            loadStudentDetails(studentId);
        } else {
            Toast.makeText(this, "ID sinh viên không hợp lệ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Đóng Activity và quay lại màn hình chính khi ấn nút quay lại trên ActionBar
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadStudentDetails(int studentId) {
        Cursor cursor = dbHelper.getStudentById(studentId);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            if (nameIndex != -1) {
                nameTextView.setText(cursor.getString(nameIndex));
            }
            if (ageIndex != -1) {
                ageTextView.setText(cursor.getString(ageIndex));
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin sinh viên", Toast.LENGTH_SHORT).show();
        }
    }

}

