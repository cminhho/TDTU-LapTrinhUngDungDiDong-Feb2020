package vn.edu.tdtu.lab04.exercise01;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class Exercise01Activity extends AppCompatActivity {

  private static final int TOTAL_ITEMS = 15;
  private ListView lv_item;
  private List<String> items = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise01);

    // bind UI controls to Java Object
    lv_item = findViewById(R.id.lv_item);

    generateRandomItems();
    setListAdapter();
    addOnItemClickListener();
  }

  private void addOnItemClickListener() {
    // add listview a listener so user can meake selections by tapping an item
    lv_item.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String content = items.get(position);
        Toast.makeText(Exercise01Activity.this, content, Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void setListAdapter() {
    // use adapter to bind items array to GUI layout
    ArrayAdapter<String> itemArrayAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_list_item_1, items);
    lv_item.setAdapter(itemArrayAdapter);
  }

  private void generateRandomItems() {
    for (int i = 0; i < TOTAL_ITEMS; i++) {
      items.add("Item " + i);
    }
  }
}
