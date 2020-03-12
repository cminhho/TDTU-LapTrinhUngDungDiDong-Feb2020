package vn.edu.tdtu.lab05.exercise02;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab05.R;

public class Exercise02Activity extends AppCompatActivity {

  private RecyclerView rvList;
  private List<String> items = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_02);

    // bind UI controls to Java objects
    rvList = findViewById(R.id.exercise_02_rv_list);

    // generate items
    for (int i = 0; i < 20; i++) {
      items.add("Item " + i);
    }

    // define item adapter
    Exercise02ItemAdapter exercise02ItemAdapter = new Exercise02ItemAdapter(items);

    // set adapter
    rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    rvList.setAdapter(exercise02ItemAdapter);
  }
}
