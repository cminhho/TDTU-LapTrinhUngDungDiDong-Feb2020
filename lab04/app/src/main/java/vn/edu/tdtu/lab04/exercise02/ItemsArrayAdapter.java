package vn.edu.tdtu.lab04.exercise02;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import vn.edu.tdtu.lab04.R;


public class ItemsArrayAdapter extends ArrayAdapter<String> {

  private Context context;
  private int layoutToBeInflated;
  private List<String> items;

  public ItemsArrayAdapter(@NonNull Context context, int resource,
      @NonNull List<String> items) {
    super(context, resource, items);
    this.items = items;
    this.context = context;
    layoutToBeInflated = resource;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // use View-Holder pattern to reduce calls to inflate, findViewById
    // holder is a POJO for the GUI rows [textview1,textview2, img1, img2]
    ItemViewHolder holder;

    // hopefully convertView is a scrapview already made (but out of sight)
    View row = convertView;

    // has this row-layout been already created?
    if (row == null) {
      // first time this row has to be created: (1) inflate custom layout
      // holding images and text, (2) invoke findViewById to access its
      // sub-components
      LayoutInflater inflater = ((Activity) context).getLayoutInflater();
      row = inflater.inflate(layoutToBeInflated, null);

      holder = new ItemViewHolder();

      // plumbing - provide access to each widget in the inflated layout
      holder = new ItemViewHolder();
      holder.tvItemName = row.findViewById(R.id.tv_item);
      holder.btnRemove = row.findViewById(R.id.btn_remove);

      // identify this row with the POJO holder just created
      row.setTag(holder);
    } else {
      // row was already created- no need to inflate and invoke findViewById
      // getTag() returns the object originally stored in this view
      holder = (ItemViewHolder) row.getTag();
    }
    String item = items.get(position);
    holder.tvItemName.setText(item);

    // This is a CLICK listener of the right button
    holder.btnRemove.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        items.remove(position);
        notifyDataSetChanged();
      }
    });

    // row listener (user clicks on any other part of the row)
    row.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(context,
            "ROW CLICKED - " + position, Toast.LENGTH_SHORT).show();
      }
    });
    return row;
  }

  public class ItemViewHolder {
    TextView tvItemName;
    Button btnRemove;
  }
}
