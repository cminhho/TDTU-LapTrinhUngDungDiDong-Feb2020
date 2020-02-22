package vn.edu.tdtu.lab02;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityExercise1 extends AppCompatActivity {

  private Button btnLogin;
  private EditText etUsername;
  private EditText etPwd;
  private CheckBox cbRememberPwd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise1);

    // mapping ui controls
    btnLogin = findViewById(R.id.btn_login);
    etUsername = findViewById(R.id.et_username);
    etPwd = findViewById(R.id.et_pwd);
    cbRememberPwd = findViewById(R.id.cb_remember_pwd);

    // adding event handlers
    btnLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String username = etUsername.getText().toString();
        String pwd = etPwd.getText().toString();
        boolean isRememberPwd = cbRememberPwd.isChecked();
        if (username.isEmpty() || pwd.isEmpty()) {
          showUserMessage("Username hoặc password rỗng");
        }

        if (username.equals(pwd)) {
          showUserMessage("Đăng nhập thành công!");

          if (isRememberPwd) {
            showUserMessage("Mật khẩu đã được lưu!");
          }
        } else {
          showUserMessage("Đăng nhập thất bại!");
        }
      }
    });
  }

  private void showUserMessage(final String message) {
    Toast.makeText(ActivityExercise1.this, message, Toast.LENGTH_SHORT).show();
  }
}
