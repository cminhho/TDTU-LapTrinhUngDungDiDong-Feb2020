package vn.edu.tdtu.lab05.exercise02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tdtu.lab05.R;

public class Exercise02ItemAdapter extends RecyclerView.Adapter<Exercise02ItemAdapter.ViewHolder> {
  private List<String> items;

  public Exercise02ItemAdapter(List<String> items) {
    this.items = items;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.exercise_02_item_row, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
    final String item = items.get(position);
    holder.tvItemName.setText(item);
    holder.btnRemove.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        items.remove(position);
        notifyDataSetChanged();
      }
    });
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvItemName;
    Button btnRemove;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvItemName = itemView.findViewById(R.id.exercise_02_tv_item_name);
      btnRemove = itemView.findViewById(R.id.exercise_02_btn_remove);
    }
  }
}
