package com.example.quanlysinhvien_son_22521247;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_STUDENT = 1;

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Student> studentList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        loadData();

        adapter = new StudentAdapter(this, studentList, new StudentAdapter.OnStudentActionListener() {
            @Override
            public void onViewDetail(Student student) {
                Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
                intent.putExtra("student_id", student.getId());
                startActivity(intent);
            }

            @Override
            public void onEdit(Student student) {
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("student_id", student.getId());
                intent.putExtra("student_name", student.getName());
                intent.putExtra("student_age", student.getAge());
                startActivity(intent);
            }

            @Override
            public void onDelete(Student student) {
                dbHelper.deleteStudent(student.getId());
                loadData();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddStudent).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK) {
            loadData(); // Tải lại danh sách sinh viên từ cơ sở dữ liệu
            adapter.notifyDataSetChanged(); // Cập nhật RecyclerView để hiển thị dữ liệu mới
        }
    }

    private void loadData() {
        studentList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                studentList.add(new Student(id, name, age));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}


