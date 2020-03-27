package vn.edu.tdtu.lab07.exercise04;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tdtu.lab07.R;

public class Exercise04Activity extends AppCompatActivity {

  public static final String INTENT_EVENT_ACTION = "INTENT_EVENT_ACTION";
  public static final String INTENT_EVENT_ID = "EVENT_ID";

  public static final String INTENT_NEW_EVENT_ACTION = "NEW";
  public static final String INTENT_EDIT_EVENT_ACTION = "EDIT";

  private RecyclerView mRecyclerView;
  private EventAdaptor mAdapter;
  private List<Event> mData;
  private Switch switchEvent;
  private AppDatabase mAppDB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exercise04_activity);

    // bind ui controls
    mRecyclerView = findViewById(R.id.recyclerView);

    mAppDB = AppDatabase.getInstance(this);

    // Initialize the adapter and attach it
    mAdapter = new EventAdaptor(this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    // populate values
    createDefaultEvents();

    registerForContextMenu(mRecyclerView);
  }

  private void createDefaultEvents() {
    AppExecutors.getInstance().diskIO().execute(new Runnable() {
      @Override
      public void run() {
        final List<Event> events = mAppDB.eventDao().getAll();
        if (events.size() == 0) {
          Event event1 = new Event("Sinh hoat chu nhiem db", "c120", "09/03/2020", "04:43");
          Event event2 = new Event("Huong dan luan van", "c120", "09/03/2020", "04:43");
          mAppDB.eventDao().insertAll(event1, event2);
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.exercise03_option_menu, menu);

    MenuItem menuItem = menu.findItem(R.id.miSwitch);
    menuItem.setActionView(R.layout.exercise03_actionbar_switch);

    switchEvent = menuItem.getActionView().findViewById(R.id.sw_event);
    switchEvent.setChecked(false);

    switchEvent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        showAllEventsFromDatabase();
      }
    });
    showAllEventsFromDatabase();
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.miDeleteAll) {
      showDeleteAllConfirmDialog();
    } else if (item.getItemId() == R.id.miAbout) {
      showAboutDialog();
    } else if (item.getItemId() == R.id.miCreate) {
      openCreateNewEventActivity();
    }
    return super.onOptionsItemSelected(item);
  }

  private void showAllEventsFromDatabase() {
    AppExecutors.getInstance().diskIO().execute(new Runnable() {
      @Override
      public void run() {
        final List<Event> events = mAppDB.eventDao().loadAllByCompleted(switchEvent.isChecked() ? 1 : 0);
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            mAdapter.setEvents(events);
          }
        });
      }
    });
  }

  private void openCreateNewEventActivity() {
    // create intent to call NewEventActivity
    Intent newEventActivityIntent = new Intent(Exercise04Activity.this, NewEventActivity.class);
    newEventActivityIntent.putExtra(INTENT_EVENT_ACTION, INTENT_NEW_EVENT_ACTION);
    startActivityForResult(newEventActivityIntent, 100);
  }

  private void showDeleteAllConfirmDialog() {
    Builder builder = new Builder(this);
    builder.setTitle("Confirm");
    builder.setMessage("Are you sure to delete all events?");
    builder.setPositiveButton("OK", new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
          @Override
          public void run() {
            mAppDB.eventDao().deleteAll();
          }
        });
        showAllEventsFromDatabase();
        Toast.makeText(Exercise04Activity.this, "All events are deleted!", Toast.LENGTH_LONG)
            .show();
      }
    });
    builder.setNegativeButton("CANCEL", new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        // do nothing
      }
    });

    AlertDialog confirmDialog = builder.create();
    confirmDialog.show();
  }

  private void showAboutDialog() {
    Builder builder = new Builder(this);
    builder.setTitle("About");
    builder.setMessage("The application is developed by thChung!");
    builder.setPositiveButton("OK", new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(Exercise04Activity.this, "GOOD LUCK!", Toast.LENGTH_LONG).show();
      }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
      showAllEventsFromDatabase();
    }
  }

}
