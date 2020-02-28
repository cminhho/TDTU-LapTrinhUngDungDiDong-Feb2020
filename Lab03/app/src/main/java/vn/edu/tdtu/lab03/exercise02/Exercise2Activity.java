package vn.edu.tdtu.lab03.exercise02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import vn.edu.tdtu.lab03.R;

public class Exercise2Activity extends AppCompatActivity {

  private TextView tvWebsiteMsg;
  private EditText etWebsiteUrl;
  private Button btnOpenBrower;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise02);

    // bind ui controls
    tvWebsiteMsg = findViewById(R.id.tv_website_message);
    etWebsiteUrl = findViewById(R.id.et_website_url);
    btnOpenBrower = findViewById(R.id.btn_open_browser);

    // handle ui events
    btnOpenBrower.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String uriStr = etWebsiteUrl.getText().toString();
        // create intent to open web browser with given uri
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriStr));
        // lunch intent
        startActivity(browserIntent);
      }
    });
  }
}
