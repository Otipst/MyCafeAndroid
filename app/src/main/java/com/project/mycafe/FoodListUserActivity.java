package com.project.mycafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FoodListUserActivity extends AppCompatActivity {

    GridView gridViewUser;
    ArrayList<Food> list;
    FoodListAdapter adapter = null;

    FloatingActionButton cartFabButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_user);

        cartFabButton = (FloatingActionButton) findViewById(R.id.cartFabButton);


        gridViewUser = (GridView) findViewById(R.id.gridViewUser);
        list = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.food_items, list);
        gridViewUser.setAdapter(adapter);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this, "MycafeDB.sqlite", null, 1);


        cartFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListUserActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        gridViewUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                CharSequence[] items = {"detail", "cart"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(FoodListUserActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Toast.makeText(FoodListUserActivity.this, "Detail", Toast.LENGTH_SHORT).show();

                        } else {
                            // delete
                            Toast.makeText(FoodListUserActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

        Cursor cursor = sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String category = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Food(id, name, category, price, image));
        }
        adapter.notifyDataSetChanged();
    }
}