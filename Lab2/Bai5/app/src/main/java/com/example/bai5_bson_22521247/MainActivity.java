package com.example.bai5_bson_22521247;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bai5_bson_22521247.Classes_Folder.Dish;
import com.example.bai5_bson_22521247.Classes_Folder.DishAdapter;
import com.example.bai5_bson_22521247.Classes_Folder.Thumbnail_Spinner_Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDishName;
    private Spinner spinnerThumbnail;
    private CheckBox checkboxPromotion;
    private GridView gridViewDishes;
    private List<Dish> dishList = new ArrayList<>();
    private DishAdapter dishAdapter;
    private int[] thumbnails = {R.drawable.hinh2, R.drawable.hinh3, R.drawable.hinh4, R.drawable.hinh5};
    private String[] thumbnailNames = {"Image 1", "Image 2", "Image 3", "Image 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDishName = findViewById(R.id.editTextDishName);
        spinnerThumbnail = findViewById(R.id.spinnerThumbnail);
        checkboxPromotion = findViewById(R.id.checkboxPromotion);
        gridViewDishes = findViewById(R.id.gridViewDishes);
        Button buttonAddDish = findViewById(R.id.buttonAddDish);

        // Set up spinner
        Thumbnail_Spinner_Adapter thumbnailAdapter = new Thumbnail_Spinner_Adapter(this, thumbnails, thumbnailNames);
        spinnerThumbnail.setAdapter(thumbnailAdapter);

        // Set up gridview adapter
        dishAdapter = new DishAdapter(this, dishList);
        gridViewDishes.setAdapter(dishAdapter);

        // Add dish button click
        buttonAddDish.setOnClickListener(v -> {
            String name = editTextDishName.getText().toString();
            int thumbnail = thumbnails[spinnerThumbnail.getSelectedItemPosition()];
            boolean isPromotion = checkboxPromotion.isChecked();

            if (!name.isEmpty()) {
                dishList.add(new Dish(name, thumbnail, isPromotion));
                dishAdapter.notifyDataSetChanged();

                // Reset fields
                editTextDishName.setText("");
                spinnerThumbnail.setSelection(0);
                checkboxPromotion.setChecked(false);

                // Show toast
                Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
