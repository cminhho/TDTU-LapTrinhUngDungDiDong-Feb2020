package vn.edu.tdtu.lab04.exercise04;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class Exercise04Activity extends AppCompatActivity {

  private GridView gvComputers;
  private List<Computer> computers = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise04);

    gvComputers = findViewById(R.id.gvComputers);

    generateRandomItems();
    handleEvents();
  }

  private void handleEvents() {
    ComputerArrayAdapter computerArrayAdapter = new ComputerArrayAdapter(this,
        R.layout.exercise04_grid_item_row, computers);
    gvComputers.setAdapter(computerArrayAdapter);
  }

  private void generateRandomItems() {
    for (int i = 1; i < 30; i++) {
      String computerName = "PC " + i;
      Computer newComputer = new Computer(computerName, true);
      computers.add(newComputer);
    }
  }
}
