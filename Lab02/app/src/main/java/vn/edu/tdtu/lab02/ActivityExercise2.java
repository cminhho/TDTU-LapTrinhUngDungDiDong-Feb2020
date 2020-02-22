package vn.edu.tdtu.lab02;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityExercise2 extends AppCompatActivity {

  private EditText et_result;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise2);

    et_result = findViewById(R.id.et_result);
  }

  public void calculate(View view) {
    String content = view.getTag().toString();
    String currentResult = et_result.getText().toString();
    et_result.setText(currentResult + content);
  }
}
