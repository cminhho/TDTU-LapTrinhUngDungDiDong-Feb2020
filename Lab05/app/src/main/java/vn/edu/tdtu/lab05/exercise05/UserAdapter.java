package vn.edu.tdtu.lab05.exercise05;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tdtu.lab05.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

  private List<User> users;

  public UserAdapter(List<User> users) {
    this.users = users;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    return new ViewHolder(layoutInflater.inflate(R.layout.exercise_05_item_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    User user = users.get(position);
    holder.tvUserName.setText(user.getName());
    holder.tvUserEmail.setText(user.getEmail());
  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvUserName;
    TextView tvUserEmail;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvUserName = itemView.findViewById(R.id.exercise_05_tv_user_name);
      tvUserEmail = itemView.findViewById(R.id.exercise_05_tv_user_email);
    }
  }
}
