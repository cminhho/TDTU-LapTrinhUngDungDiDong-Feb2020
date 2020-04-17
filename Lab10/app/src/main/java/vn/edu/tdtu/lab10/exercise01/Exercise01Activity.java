package vn.edu.tdtu.lab10.exercise01;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab10.R;

public class Exercise01Activity extends AppCompatActivity {
  private List<Contact> contactsList = new ArrayList<>();
  private RecyclerView recyclerView;
  private ContactsAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exercise01_activity);

    recyclerView = findViewById(R.id.recycler_view);

    mAdapter = new ContactsAdapter(getBaseContext(), contactsList);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    // set the adapter
    recyclerView.setAdapter(mAdapter);

    prepareContactData();
  }

  private void prepareContactData() {
    Contact contact = new Contact("Ho Minh Chung", "0906 246 489");
    contactsList.add(contact);

    Contact contact2 = new Contact("Nguyen Van A", "0906 246 489");
    contactsList.add(contact2);

    Contact contact3 = new Contact("Nguyen Van B", "0906 246 489");
    contactsList.add(contact3);

    mAdapter.notifyDataSetChanged();
  }
}
