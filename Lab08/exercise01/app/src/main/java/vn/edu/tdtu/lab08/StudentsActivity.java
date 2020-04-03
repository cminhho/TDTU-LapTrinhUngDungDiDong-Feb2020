package vn.edu.tdtu.lab08;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentsActivity extends AppCompatActivity {

  private static final String TAG = StudentsActivity.class.getName();

  public static final String INTENT_STUDENT_ID = "STUDENT_ID";
  public static final String INTENT_STUDENT_NAME = "STUDENT_NAME";
  public static final String INTENT_STUDENT_EMAIL = "STUDENT_EMAIL";
  public static final String INTENT_STUDENT_PHONE = "STUDENT_PHONE";

  private RecyclerView mRecyclerView;
  private StudentAdaptor mAdapter;
  private List<Student> listItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.students_activity);

    // bind ui controls
    mRecyclerView = findViewById(R.id.recyclerView);

    listItems = new ArrayList<>();

    fetchStudents();

    // Initialize the adapter and attach it
    mAdapter = new StudentAdaptor(this);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));
    mRecyclerView.addOnItemTouchListener(new OnItemTouchListener() {
      @Override
      public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
      }

      @Override
      public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        Toast.makeText(getBaseContext(), "error: ---------", Toast.LENGTH_SHORT)
            .show();
      }

      @Override
      public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

      }
    });

    registerForContextMenu(mRecyclerView);
  }

  private void fetchStudents() {
    listItems.clear();

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, Constants.URL_LIST, (String) null, new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject response) {
            try {
              JSONArray jsonArray = response.getJSONArray("data");
              for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Student item = new Student(
                    o.getString("id"),
                    o.getString("name"),
                    o.getString("phone"),
                    o.getString("email")
                );
                listItems.add(item);
                mAdapter.setEvents(listItems);
                mAdapter.notifyDataSetChanged();
              }
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {
            // TODO: Handle error
            Toast.makeText(getBaseContext(), "error: " + error.getMessage(), Toast.LENGTH_SHORT)
                .show();
          }
        });

    // Access the RequestQueue through your singleton class.
    RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.exercise03_option_menu, menu);

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.miCreate) {
      openCreateNewEventActivity();
    }
    return super.onOptionsItemSelected(item);
  }

  private void openCreateNewEventActivity() {
    // create intent to call NewEventActivity
    Intent newEventActivityIntent = new Intent(StudentsActivity.this, NewStudentActivity.class);
    startActivityForResult(newEventActivityIntent, 100);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
      fetchStudents();
    }
  }
}
