package vn.edu.tdtu.lab07.exercise03;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import vn.edu.tdtu.lab07.R;

public class Exercise03Activity extends AppCompatActivity {

  public static final String INTENT_EVENT_ACTION = "INTENT_EVENT_ACTION";
  public static final String INTENT_EVENT_ID = "EVENT_ID";

  public static final String INTENT_NEW_EVENT_ACTION = "NEW";
  public static final String INTENT_EDIT_EVENT_ACTION = "EDIT";

  private ListView mListView;
  private EventAdapter mAdapter;
  private List<Event> mData;
  private EventDatabaseHelper mDbHelper;
  private Switch switchEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exercise03_activity);

    // bind ui controls
    mListView = findViewById(R.id.listView);

    mDbHelper = new EventDatabaseHelper(this);

    // populate values
    mDbHelper.createDefaultEvents();

    registerForContextMenu(mListView);
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
      showConfirmDialog();
    } else if (item.getItemId() == R.id.miAbout) {
      showAboutDialog();
    } else if (item.getItemId() == R.id.miCreate) {
      openCreateNewEventActivity();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);

    int position = ((AdapterContextMenuInfo) menuInfo).position;
    menu.setHeaderTitle(mData.get(position).getName());

    menu.add("Edit");
    menu.add("Delete");
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {
    int position = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
    Event selectedEvent = mData.get(position);
    if (item.getTitle().equals("Edit")) {
      // create intent to call NewEventActivity
      Intent newEventActivityIntent = new Intent(Exercise03Activity.this, NewEventActivity.class);
      newEventActivityIntent.putExtra(INTENT_EVENT_ID, selectedEvent.getId());
      newEventActivityIntent.putExtra(INTENT_EVENT_ACTION, INTENT_EDIT_EVENT_ACTION);
      startActivityForResult(newEventActivityIntent, 100);
    } else if (item.getTitle().equals("Delete")) {
      mDbHelper.deleteEvent(selectedEvent);
      showAllEventsFromDatabase();
    }
    return super.onContextItemSelected(item);
  }

  private void showAllEventsFromDatabase() {
    mData = mDbHelper.getAllEvents(switchEvent.isChecked());

    // set adapter
    mAdapter = new EventAdapter(this, R.layout.exercise03_row_layout, mData, mDbHelper);
    mListView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }

  private void openCreateNewEventActivity() {
    // create intent to call NewEventActivity
    Intent newEventActivityIntent = new Intent(Exercise03Activity.this, NewEventActivity.class);
    newEventActivityIntent.putExtra(INTENT_EVENT_ACTION, INTENT_NEW_EVENT_ACTION);
    startActivityForResult(newEventActivityIntent, 100);
  }

  private void showConfirmDialog() {
    Builder builder = new Builder(this);
    builder.setTitle("Confirm");
    builder.setMessage("Are you sure to delete all events?");
    builder.setPositiveButton("OK", new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        showAllEventsFromDatabase();
        Toast.makeText(Exercise03Activity.this, "The selected phone is deleted!", Toast.LENGTH_LONG)
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
        Toast.makeText(Exercise03Activity.this, "GOOD LUCK!", Toast.LENGTH_LONG).show();
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
