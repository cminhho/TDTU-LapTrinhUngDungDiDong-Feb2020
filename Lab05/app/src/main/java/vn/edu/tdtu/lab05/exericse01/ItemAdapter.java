package vn.edu.tdtu.lab05.exericse01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tdtu.lab05.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
  private List<String> items;

  public ItemAdapter(List<String> items) {
    this.items = items;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.exercise_01_item_row, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    String item = items.get(position);
    holder.tvItemName.setText(item);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvItemName;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvItemName = itemView.findViewById(R.id.tv_item_name);
    }
  }
}
