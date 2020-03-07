package vn.edu.tdtu.lab04.exercise03;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import vn.edu.tdtu.lab04.R;

public class PhoneArrayAdapter extends ArrayAdapter<Phone> {

  private Context context;
  private int layoutToBeInflated;
  private List<Phone> computers;

  public PhoneArrayAdapter(@NonNull Context context,
      int resource,
      @NonNull List<Phone> computers) {
    super(context, resource, computers);
    this.computers = computers;
    this.layoutToBeInflated = resource;
    this.context = context;
  }

  @NonNull
  @Override
  public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ComputerViewHolder holder;
    View row = convertView;

    if (row == null) {
      LayoutInflater inflater = ((Activity) context).getLayoutInflater();
      row = inflater.inflate(layoutToBeInflated, null);
      holder = new ComputerViewHolder();
      holder.tvPhoneName = row.findViewById(R.id.tv_phone_name);
      holder.cbSelectPhone = row.findViewById(R.id.cb_select_phone);
      row.setTag(holder);
    } else {
      // row was already created- no need to inflate and invoke findViewById
      // getTag() returns the object originally stored in this view
      holder = (ComputerViewHolder) row.getTag();
    }

    Phone phone = computers.get(position);
    holder.tvPhoneName.setText(phone.getName());
    holder.cbSelectPhone.setChecked(phone.isSelected());

    // row listener (user clicks on any other part of the row)
    row.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Phone computer = computers.get(position);
        Toast.makeText(context,
            "PHONE CLICKED - " + computer.getName(), Toast.LENGTH_SHORT).show();
      }
    });

    return row;
  }

  private class ComputerViewHolder {
    TextView tvPhoneName;
    CheckBox cbSelectPhone;
  }
}
