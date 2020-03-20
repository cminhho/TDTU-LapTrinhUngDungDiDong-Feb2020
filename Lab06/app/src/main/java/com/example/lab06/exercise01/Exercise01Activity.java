package com.example.lab06.exercise01;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab06.R;
import java.util.ArrayList;
import java.util.List;

public class Exercise01Activity extends AppCompatActivity {

    private ListView mListView;
    private PhoneAdapter mAdapter;
    private List<Phone> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise01_activity_main);

        initViews();
        initData();

        registerForContextMenu(mListView);
    }

    private void initViews() {
        mListView = findViewById(R.id.listView);
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new Phone(R.drawable.mobile, "Apple"));
        mData.add(new Phone(R.drawable.mobile2, "Samsung"));
        mData.add(new Phone(R.drawable.mobile3, "Nokia"));
        mData.add(new Phone(R.drawable.mobile, "Oppo"));
        mData.add(new Phone(R.drawable.mobile2, "HTC"));
        mData.add(new Phone(R.drawable.mobile3, "Google"));
        mData.add(new Phone(R.drawable.mobile, "Microsoft"));
        mData.add(new Phone(R.drawable.mobile2, "BKav"));
        mData.add(new Phone(R.drawable.mobile3, "VinSmart"));

        mAdapter = new PhoneAdapter(this, R.layout.row_layout, mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        int position = ((AdapterContextMenuInfo) menuInfo).position;
        menu.setHeaderTitle(mData.get(position).getName());

        menu.add("Check/uncheck");
        menu.add("Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
        if (item.getTitle().equals("Check/uncheck")) {
            Phone phone = mData.get(position);
            phone.setChecked(!phone.isChecked());
            mAdapter.notifyDataSetChanged();
        } else if (item.getTitle().equals("Delete")) {
            mData.remove(position);
            mAdapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }
}
