package com.example.lab3_contact_bson_22521247;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText tenLienHe, soDienThoai;
    private Button btnThem, btnCapNhat, btnXoa;
    private ListView danhSachLienHe;

    private DatabaseHandler db;
    private List<Contact> contactList;
    private List<String> contactNames;
    private ArrayAdapter<String> adapter;

    private int idLienHeChon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View
        tenLienHe = findViewById(R.id.tenLienHe);
        soDienThoai = findViewById(R.id.soDienThoai);
        btnThem = findViewById(R.id.btnThem);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);
        danhSachLienHe = findViewById(R.id.danhSachLienHe);

        // Khởi tạo DatabaseHandler
        db = new DatabaseHandler(this);

        // Tải danh sách ban đầu
        taiDanhSachLienHe();

        // Xử lý sự kiện nút Thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenLienHe.getText().toString().trim();
                String soDienThoaiStr = soDienThoai.getText().toString().trim();
                if (!ten.isEmpty() && !soDienThoaiStr.isEmpty()) {
                    db.addContact(new Contact(ten, soDienThoaiStr));
                    Toast.makeText(MainActivity.this, "Đã thêm liên hệ!", Toast.LENGTH_SHORT).show();
                    taiDanhSachLienHe();
                    xoaTruongNhap();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên và số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nút Cập nhật
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenLienHe.getText().toString().trim();
                String soDienThoaiStr = soDienThoai.getText().toString().trim();
                if (idLienHeChon != -1 && !ten.isEmpty() && !soDienThoaiStr.isEmpty()) {
                    db.updateContact(new Contact(idLienHeChon, ten, soDienThoaiStr));
                    Toast.makeText(MainActivity.this, "Đã cập nhật liên hệ!", Toast.LENGTH_SHORT).show();
                    taiDanhSachLienHe();
                    xoaTruongNhap();
                    idLienHeChon = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn liên hệ và điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nút Xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idLienHeChon != -1) {
                    db.deleteContact(idLienHeChon);
                    Toast.makeText(MainActivity.this, "Đã xóa liên hệ!", Toast.LENGTH_SHORT).show();
                    taiDanhSachLienHe();
                    xoaTruongNhap();
                    idLienHeChon = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn liên hệ để xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Xử lý sự kiện khi long click để xóa
        danhSachLienHe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = contactList.get(i);
                idLienHeChon = contact.getId();
                db.deleteContact(idLienHeChon);
                Toast.makeText(MainActivity.this, "Đã xóa liên hệ!", Toast.LENGTH_SHORT).show();
                taiDanhSachLienHe();
                xoaTruongNhap();
                idLienHeChon = -1;
                return true;
            }
        });

        // Xử lý sự kiện khi nhấn vào danh sách
        danhSachLienHe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contactList.get(position);
                idLienHeChon = contact.getId();
                tenLienHe.setText(contact.getName());
                soDienThoai.setText(contact.getPhone());
            }
        });


    }

    private void taiDanhSachLienHe() {
        contactList = db.getAllContacts();
        contactNames = new ArrayList<>();
        for (Contact contact : contactList) {
            contactNames.add("Id: " + contact.getId() + " ,Name: " + contact.getName() + " ,Phone: " + contact.getPhone() );
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        danhSachLienHe.setAdapter(adapter);
    }

    private void xoaTruongNhap() {
        tenLienHe.setText("");
        soDienThoai.setText("");
    }
}
