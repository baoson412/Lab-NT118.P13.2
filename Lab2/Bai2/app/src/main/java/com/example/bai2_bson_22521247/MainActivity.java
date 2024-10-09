package com.example.bai2_bson_22521247;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Khai báo ArrayList để lưu trữ dữ liệu
    ArrayList<String> dataList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liên kết các view
        EditText editText = findViewById(R.id.editText);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        ListView listView = findViewById(R.id.listView);
        TextView textViewShow = findViewById(R.id.txtShow);

        // Tạo adapter cho ListView với ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // Xử lý sự kiện nhấn nút "Nhập" để thêm dữ liệu vào ArrayList và hiển thị lên ListView
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                if (!newItem.isEmpty()) {
                    dataList.add(newItem); // Thêm dữ liệu vào ArrayList
                    adapter.notifyDataSetChanged(); // Cập nhật ListView
                    editText.setText(""); // Xóa nội dung nhập sau khi thêm
                }
            }
        });

        // Xử lý sự kiện nhấn vào phần tử trong ListView để hiển thị vị trí và giá trị của phần tử
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = dataList.get(position);
                textViewShow.setText("Vị trí: " + position + ", Giá trị: " + selectedItem);
            }
        });

        // Xử lý sự kiện nhấn lâu vào phần tử để xóa phần tử đó khỏi ArrayList
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dataList.remove(position); // Xóa phần tử khỏi ArrayList
                adapter.notifyDataSetChanged(); // Cập nhật ListView sau khi xóa
                return true; // Trả về true để xác nhận sự kiện đã được xử lý
            }
        });
    }
}