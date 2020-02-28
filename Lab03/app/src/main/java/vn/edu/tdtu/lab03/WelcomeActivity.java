package vn.edu.tdtu.lab03;

import static vn.edu.tdtu.lab03.Exercise01MainActivity.EMAIL_BUNDLE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

  public static final String USER_FULLNAME = "user_fullname";

  private TextView tvWelcomneMsg;
  private EditText etUserFullname;
  private Button btnSaveAndQuick;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise01_welcome);

    // bind UI controls to Java objects
    tvWelcomneMsg = findViewById(R.id.tv_welcome_message);
    etUserFullname = findViewById(R.id.et_user_fullname);
    btnSaveAndQuick = findViewById(R.id.btn_save_and_quick);

    // create intent that started this activity
    Intent exercise01Intent = getIntent();

    // get bundle container
    Bundle exercise01Bundle = exercise01Intent.getExtras();

    String emailReceived = exercise01Bundle.getString(EMAIL_BUNDLE);

    // bind data received from MainActivity
    tvWelcomneMsg.setText("Xin chào, " + emailReceived + ". Vui lòng nhập tên:");

    // add ui control's events
    btnSaveAndQuick.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // create intent to call MainActivity
        Intent exercise01Intent = new Intent();

        // create bundle to ship data items
        Bundle exercise01Bundle = new Bundle();
        exercise01Bundle.putString(USER_FULLNAME, etUserFullname.getText().toString());

        // add bundle container to intent
        exercise01Intent.putExtras(exercise01Bundle);

        // lunch MainActivity and close the current activity
        setResult(Activity.RESULT_OK, exercise01Intent);
        finish();
      }
    });
  }
}
