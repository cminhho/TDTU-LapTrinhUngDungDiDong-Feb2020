package vn.edu.tdtu.lab03;

import static vn.edu.tdtu.lab03.WelcomeActivity.USER_FULLNAME;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Exercise01MainActivity extends AppCompatActivity {

  public static final String EMAIL_BUNDLE = "email";

  private EditText etEmail;
  private Button btnLogin;
  private TextView tvMessage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise01_main);

    // bind UI controls to Java objects
    tvMessage = findViewById(R.id.tv_message);
    etEmail = findViewById(R.id.et_email);
    btnLogin = findViewById(R.id.btn_login);

    // add ui events
    btnLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // create intent to call WelcomeActivity
        Intent mainAWelcomeAIntent = new Intent(Exercise01MainActivity.this, WelcomeActivity.class);

        // create bundle container to ship data
        Bundle exercise01MainABundle = new Bundle();

        // add <key,value> data items
        exercise01MainABundle.putString(EMAIL_BUNDLE, etEmail.getText().toString());

        // attach bundle container to intent
        mainAWelcomeAIntent.putExtras(exercise01MainABundle);

        // launch welcome activity
        startActivityForResult(mainAWelcomeAIntent, 100);
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
      // create bundle with data items
      Bundle welcomeBundle = data.getExtras();
      String userFullname = welcomeBundle.getString(USER_FULLNAME);

      // bind ui values
      etEmail.setText(userFullname);
      tvMessage.setText("Hẹn gặp lại!");
      btnLogin.setVisibility(View.GONE);
    }
  }
}
