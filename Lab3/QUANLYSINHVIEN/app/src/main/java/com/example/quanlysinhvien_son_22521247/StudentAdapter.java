package com.example.quanlysinhvien_son_22521247;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private Context context;
    private OnStudentActionListener listener;
    private DatabaseHelper dbHelper;

    public interface OnStudentActionListener {
        void onViewDetail(Student student);
        void onEdit(Student student);
        void onDelete(Student student);
    }

    public StudentAdapter(Context context, List<Student> studentList, OnStudentActionListener listener) {
        this.context = context;
        this.studentList = studentList;
        this.listener = listener;
        this.dbHelper = new DatabaseHelper(context); // Khởi tạo dbHelper với context
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        Student student = studentList.get(position);
        holder.nameTextView.setText(student.getName());
        holder.ageTextView.setText(String.valueOf(student.getAge()));

        holder.btnViewDetail.setOnClickListener(v -> listener.onViewDetail(student));
        holder.btnEdit.setOnClickListener(v -> listener.onEdit(student));

        // Xóa 1 sinh viên
        holder.btnDelete.setOnClickListener(v -> {
            dbHelper.deleteStudent(student.getId());
            studentList.remove(position); // Xóa khỏi danh sách hiện tại
            notifyItemRemoved(position); // Cập nhật RecyclerView
            Toast.makeText(holder.itemView.getContext(), "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
        });
    }

    //Cập nhật listview khi ấn button save để thêm sinh viên
    public void addStudent(Student student) {
        studentList.add(student);
        notifyItemInserted(studentList.size() - 1); // Thông báo vị trí mới được thêm
    }


    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == updatedStudent.getId()) {
                studentList.set(i, updatedStudent); // Cập nhật thông tin sinh viên
                notifyItemChanged(i); // Thông báo RecyclerView đã thay đổi
                break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ageTextView;
        Button btnViewDetail, btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            ageTextView = itemView.findViewById(R.id.textViewAge);
            btnViewDetail = itemView.findViewById(R.id.btnViewDetail);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
