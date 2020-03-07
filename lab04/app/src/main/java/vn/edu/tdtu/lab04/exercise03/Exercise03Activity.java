package vn.edu.tdtu.lab04.exercise03;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab04.R;
import vn.edu.tdtu.lab04.exercise05.Exercise05Activity;

public class Exercise03Activity extends AppCompatActivity {

  private ListView lvPhones;
  private List<Phone> phones = new ArrayList<>();
  private Button btnRemoveAll;
  private Button btnRemoveSelected;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise03);

    // bind UI controls to Java objects
    lvPhones = findViewById(R.id.lv_phones);
    btnRemoveAll = findViewById(R.id.btn_remove_all);
    btnRemoveSelected = findViewById(R.id.btn_remove_selected);

    generateRandomItems();

    final PhoneArrayAdapter phoneArrayAdapter = new PhoneArrayAdapter(this,
        R.layout.exercise03_list_row_item, phones);
    lvPhones.setAdapter(phoneArrayAdapter);

    btnRemoveAll.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        phones.clear();
        phoneArrayAdapter.notifyDataSetChanged();
      }
    });

    btnRemoveSelected.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        for (Phone phone : phones) {
          if (phone.isSelected()) {
            phone.setSelected(false);
          }
        }
        phoneArrayAdapter.notifyDataSetChanged();
      }
    });
  }

  private void generateRandomItems() {
    phones.add(new Phone("Apple", true));
    phones.add(new Phone("Samsung", true));
    phones.add(new Phone("Nokia", true));
    phones.add(new Phone("Oppo", true));
  }
}
