package vn.edu.tdtu.lab05.exericse01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab05.R;

public class Exercise01Activity extends AppCompatActivity {

  private RecyclerView rvList;
  private List<String> items = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_01);

    // bind UI controls to Java objects
    rvList = findViewById(R.id.exercise_01_rv_list);

    // generate items
    for (int i = 0; i < 20; i++) {
      items.add("Item " + i);
    }

    // define item adapter
    ItemAdapter itemAdapter = new ItemAdapter(items);

    // set adapter
    rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    rvList.setAdapter(itemAdapter);
  }
}
