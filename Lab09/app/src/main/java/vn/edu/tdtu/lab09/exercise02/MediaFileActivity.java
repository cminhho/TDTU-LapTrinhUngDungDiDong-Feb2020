package vn.edu.tdtu.lab09.exercise02;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import vn.edu.tdtu.lab09.R;

public class MediaFileActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private MediaFileAdaptor mAdapter;
  List<MediaFile> mMediaList = new ArrayList<>();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.exercise02_activity);

    for (int i = 0; i < 5; i++) {
      MediaFile mediaFile = new MediaFile();
      mediaFile.setName("Media " + i);
      mediaFile.setPath("music1.mp3");
      mMediaList.add(mediaFile);
    }

    mRecyclerView = findViewById(R.id.recyclerView);

    mAdapter = new MediaFileAdaptor(this);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setData(mMediaList);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(
        new DividerItemDecoration(getBaseContext(), DividerItemDecoration.VERTICAL));

  }


}
