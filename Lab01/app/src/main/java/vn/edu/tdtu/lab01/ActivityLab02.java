package vn.edu.tdtu.lab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLab02 extends AppCompatActivity {

  private CheckBox cbAndroid, cbIOS, cbWindows, cbRIM;
  private RadioButton rbAndroid, rbIOS, rbWindows, rbRIM;
  private TextView txtAndroid, txtIOS, txtWindows, txtRIM;
  private Button btnSeeResult;

  @Override
  protected void onCreate(Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    setContentView(R.layout.activity_exercise02);

    // initialize views
    cbAndroid = findViewById(R.id.cbAndroid);
    cbIOS = findViewById(R.id.cbIOS);
    cbWindows = findViewById(R.id.cbWindows);
    cbRIM = findViewById(R.id.cbRIM);

    txtAndroid = findViewById(R.id.txtAndroid);
    txtIOS = findViewById(R.id.txtIOS);
    txtWindows = findViewById(R.id.txtWindows);
    txtRIM = findViewById(R.id.txtRIM);

    btnSeeResult = findViewById(R.id.btnSeeResult);

    // event handlers
    btnSeeResult.setOnClickListener(onBtnSeeResultClicked);
  }

  private View.OnClickListener onBtnSeeResultClicked = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      txtAndroid.setText(String.valueOf(cbAndroid.isChecked()));
      txtIOS.setText(String.valueOf(cbIOS.isChecked()));
      txtWindows.setText(String.valueOf(cbWindows.isChecked()));
      txtRIM.setText(String.valueOf(cbRIM.isChecked()));
    }
  };
}