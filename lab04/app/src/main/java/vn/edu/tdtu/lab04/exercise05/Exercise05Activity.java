package vn.edu.tdtu.lab04.exercise05;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class Exercise05Activity extends AppCompatActivity {

  private static final int TOTAL_ITEMS = 5;
  private Button btnCreate;
  private Button btnRemove;
  private TextView txtTotalUsers;
  private ListView lvUsers;
  private List<User> users = new ArrayList<>();
  private UserArrayAdapter phoneArrayAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise05);

    lvUsers = findViewById(R.id.lv_users);
    btnCreate = findViewById(R.id.activity_users_btn_create);
    btnRemove = findViewById(R.id.activity_users_btn_remove);
    txtTotalUsers = findViewById(R.id.activity_users_txt_total_users);

    setCustomAdapter();
    handleEvents();
  }

  private void setCustomAdapter() {
    phoneArrayAdapter = new UserArrayAdapter(this,
        R.layout.exercise05_list_item_row, users);
    lvUsers.setAdapter(phoneArrayAdapter);
  }

  private void handleEvents() {
    btnCreate.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        populateUserDetails(5);
        setTotalUsers();
        phoneArrayAdapter.notifyDataSetChanged();
      }
    });

    btnRemove.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if(users.isEmpty()) {
          Toast.makeText(Exercise05Activity.this, "List of users is empty", Toast.LENGTH_LONG).show();
        } else {
          removeUsers(5);
        }
        setTotalUsers();
        phoneArrayAdapter.notifyDataSetChanged();
      }
    });
  }

  private void setTotalUsers() {
    int numberOfUsers = users.size();
    txtTotalUsers.setText("Total users: " + numberOfUsers);
  }

  private void removeUsers(int numberOfUsers) {
    for (int i = 0; i < numberOfUsers; i++) {
      users.remove(users.size() - 1);
    }
  }

  private void populateUserDetails(int numberOfUsers) {
    int indexFrom = users.size() + 1;
    int indexTo = indexFrom + numberOfUsers;
    for (int i = indexFrom; i < indexTo; i++) {
      users.add(new User("User " + i, "user" + i + "@tdtu.edu.vn"));
    }
  }
}
