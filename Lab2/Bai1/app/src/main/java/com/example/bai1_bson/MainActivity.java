package com.example.bai1_bson;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Khai báo mảng dữ liệu định sẵn
    String[] values = {"Tèo", "Tý", "Bin", "Bo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liên kết các view
        ListView listView = findViewById(R.id.listView);
        TextView textView = findViewById(R.id.textView);

        // Tạo ArrayAdapter để hiển thị mảng dữ liệu lên ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng chọn 1 phần tử trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy giá trị của phần tử được chọn
                String selectedItem = values[position];
                // Hiển thị vị trí và giá trị lên TextView
                textView.setText("Vị trí: " + position + ", Giá trị: " + selectedItem);
            }
        });
    }
}