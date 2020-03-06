package vn.edu.tdtu.lab04.exercise02;

import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class Exercise02Activity extends AppCompatActivity {

  private static final int TOTAL_ITEMS = 15;
  private ListView lvItem;
  private List<String> items = new ArrayList<>();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise02);

    lvItem = findViewById(R.id.exercise_02_lv_item);

    generateRandomItems();
    setAdapterView();
  }

  private void setAdapterView() {
    ItemsArrayAdapter itemsArrayAdapter = new ItemsArrayAdapter(this,
        R.layout.exercise02_list_row_item, items);
    lvItem.setAdapter(itemsArrayAdapter);
  }

  private void generateRandomItems() {
    for (int i = 0; i < TOTAL_ITEMS; i++) {
      items.add("Item " + i);
    }
  }
}
