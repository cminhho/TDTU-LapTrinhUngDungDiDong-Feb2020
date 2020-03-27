package vn.edu.tdtu.lab07.exercise02;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import vn.edu.tdtu.lab07.R;

public class Exercise02Activity extends AppCompatActivity implements View.OnClickListener {

  private static final String INTERNAL_FILE_NAME = "lab07_exercise02_internal_file.txt";

  private static final String EXTERNAL_FILE_NAME = "lab07_exercise02_external_file.txt";

  private String TAG = Exercise02Activity.class.getName();

  private Button btnReadInternal;
  private Button btnReadExternal;
  private Button btnWriteInternal;
  private Button btnWriteExternal;
  private EditText txtMsg;
  private String mySdPath;
  private TextView txtInternalFilePath;
  private TextView txtSDFilePath;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.exercise02_activity);

    // map ui controls to java objects
    btnReadInternal = findViewById(R.id.btnReadInternal);
    btnReadExternal = findViewById(R.id.btnReadExternal);
    btnWriteInternal = findViewById(R.id.btnWriteInternal);
    btnWriteExternal = findViewById(R.id.btnWriteExternal);
    txtMsg = findViewById(R.id.edtContent);
    txtInternalFilePath = findViewById(R.id.txtInternalFilePath);
    txtSDFilePath = findViewById(R.id.txtSDFilePath);

    // handle events
    btnReadInternal.setOnClickListener(this);
    btnReadExternal.setOnClickListener(this);
    btnWriteInternal.setOnClickListener(this);
    btnWriteExternal.setOnClickListener(this);

    // determine the SD location
    mySdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    // bind default value
    txtInternalFilePath.setText("/data/data/" + getPackageName() + "/" + INTERNAL_FILE_NAME);
    txtSDFilePath.setText(mySdPath + EXTERNAL_FILE_NAME);

  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnReadInternal:
        // READ data from internal file show it in the text box
        clearTextMsg();
        try {
          String internalFileContent = readInternalFileContent();
          txtMsg.setText(internalFileContent);
          Toast.makeText(getApplicationContext(), "Done reading internal file", Toast.LENGTH_SHORT)
              .show();
        } catch (IOException e) {
          Toast.makeText(getApplicationContext(), e.getMessage(),
              Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.btnWriteInternal:
        // WRITE internal file data taken from the text box
        try {
          writeInternalFileContent();
          Toast.makeText(getBaseContext(), "Done writing internal file: " + INTERNAL_FILE_NAME,
              Toast.LENGTH_SHORT).show();
          clearTextMsg();
        } catch (IOException e) {
          Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.btnReadExternal:
        // READ data from SD card file show it in the text box
        clearTextMsg();
        try {
          String externalFileContent = readExternalFileContent();
          txtMsg.setText(externalFileContent);
          Toast.makeText(getApplicationContext(), "Done reading SD " + EXTERNAL_FILE_NAME,
              Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
          Toast.makeText(getApplicationContext(), e.getMessage(),
              Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.btnWriteExternal:
        // WRITE on SD card file data taken from the text box
        try {
          writeExternalFile();
          Toast.makeText(getBaseContext(), "Done writing SD file: " + EXTERNAL_FILE_NAME,
              Toast.LENGTH_SHORT).show();
          clearTextMsg();
        } catch (IOException e) {
          Toast.makeText(getBaseContext(), e.getMessage(),
              Toast.LENGTH_SHORT).show();
        }
        break;
    }
  }

  private String readExternalFileContent() throws IOException {
    StringBuilder stringBuffer = new StringBuilder();
    BufferedReader myReader = new BufferedReader(
        new InputStreamReader(
            new FileInputStream(
                new File(mySdPath + "/" + EXTERNAL_FILE_NAME))));

    String aDataRow = "";
    while ((aDataRow = myReader.readLine()) != null) {
      stringBuffer.append(aDataRow).append("\n");
    }
    myReader.close();
    return stringBuffer.toString();
  }

  private void writeExternalFile() throws IOException {
    // Don't forget to request permission to read and write to SD card, such as mnt/sdcard/file.txt
    File myFile = new File(mySdPath + "/" + EXTERNAL_FILE_NAME);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(myFile));

    outputStreamWriter.append(txtMsg.getText().toString());
    outputStreamWriter.close();
  }

  private void writeInternalFileContent() throws IOException {
    // Use openFileInput() Open a private file linked to this context's application package for reading
    // and create the file if it doesn't already
    // the file's path: /data/data/packageName/FileName
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
        openFileOutput(INTERNAL_FILE_NAME, 0));
    outputStreamWriter.write(txtMsg.getText().toString());
    outputStreamWriter.close();
  }

  private String readInternalFileContent() throws IOException {
    StringBuilder stringBuffer = new StringBuilder();
    InputStream inputStream = openFileInput(INTERNAL_FILE_NAME);
    if (inputStream != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      String aDataRow = "";

      while ((aDataRow = reader.readLine()) != null) {
        stringBuffer.append(aDataRow).append("\n");
      }
      inputStream.close();
    }
    return stringBuffer.toString();
  }

  private void createInternalFileIfNeeded() {
    File dir = getApplication().getFilesDir();
    File myInternalFile = new File(dir, INTERNAL_FILE_NAME);
    if (myInternalFile.exists()) {
      try {
        myInternalFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        Toast.makeText(getBaseContext(), "Error while creating new file", Toast.LENGTH_LONG)
            .show();
      }
    }
  }

  private void readInternalRawFile() throws IOException {
    String str = "";
    StringBuffer buf = new StringBuffer();
    int fileResourceId = R.raw.my_text_file;
    InputStream is = this.getResources().openRawResource(fileResourceId);

    // Use BufferedReader to extract lines from the input buffer
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    if (is != null) {
      while ((str = reader.readLine()) != null) {
        buf.append(str + "\n");
      }
    }
    reader.close();
    is.close();
    txtMsg.setText(buf.toString());
  }

  private void clearTextMsg() {
    // clear text in edittext message
    txtMsg.getText().clear();
  }
}
