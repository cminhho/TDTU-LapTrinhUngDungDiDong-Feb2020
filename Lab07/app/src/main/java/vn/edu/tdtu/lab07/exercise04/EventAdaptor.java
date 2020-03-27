package vn.edu.tdtu.lab07.exercise04;

import static vn.edu.tdtu.lab07.exercise04.Exercise04Activity.INTENT_EDIT_EVENT_ACTION;
import static vn.edu.tdtu.lab07.exercise04.Exercise04Activity.INTENT_EVENT_ACTION;
import static vn.edu.tdtu.lab07.exercise04.Exercise04Activity.INTENT_EVENT_ID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.tdtu.lab07.R;


public class EventAdaptor extends RecyclerView.Adapter<EventAdaptor.MyViewHolder> {

  int mPosition;
  private Context context;
  private List<Event> mEventList;

  public EventAdaptor(Context context) {
    this.context = context;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.exercise04_row_layout, viewGroup, false);

    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EventAdaptor.MyViewHolder myViewHolder, int i) {
    myViewHolder.name.setText(mEventList.get(i).getName());
    myViewHolder.place.setText(mEventList.get(i).getPlace());
    myViewHolder.datetime.setText(mEventList.get(i).getDate() + "" + mEventList.get(i).getTime());
    myViewHolder.eventSwitch.setChecked(mEventList.get(i).getCompleted() == 1);

  }

  @Override
  public int getItemCount() {
    if (mEventList == null) {
      return 0;
    }
    return mEventList.size();

  }

  public void setEvents(List<Event> eventList) {
    mEventList = eventList;
    notifyDataSetChanged();
  }

  public List<Event> getEvents() {

    return mEventList;
  }

  public void setPosition(int position) {
    mPosition = position;
  }

  class MyViewHolder extends RecyclerView.ViewHolder implements OnCreateContextMenuListener {

    TextView name;
    TextView place;
    TextView datetime;
    Switch eventSwitch;
    AppDatabase mDb;

    MyViewHolder(@NonNull final View itemView) {
      super(itemView);
      mDb = AppDatabase.getInstance(context);
      name = itemView.findViewById(R.id.tv_name);
      place = itemView.findViewById(R.id.tv_place);
      datetime = itemView.findViewById(R.id.tv_datetime);
      eventSwitch = itemView.findViewById(R.id.sw_switch);

      itemView.setOnCreateContextMenuListener(this);

      eventSwitch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
              Event selectedEvent = mEventList.get(getAdapterPosition());
              boolean checked = selectedEvent.getCompleted() == 1;
              selectedEvent.setCompleted(!checked ? 1 : 0);

              mDb.eventDao().updateAll(selectedEvent);
            }
          });
        }
      });
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view,
        ContextMenuInfo contextMenuInfo) {
      final AppDatabase mDb = AppDatabase.getInstance(context);
      final Event selectedEvent = mEventList.get(getAdapterPosition());
      contextMenu.setHeaderTitle(selectedEvent.getName());

      contextMenu.add("edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
          // create intent to call NewEventActivity
          Intent newEventActivityIntent = new Intent(context, NewEventActivity.class);
          newEventActivityIntent.putExtra(INTENT_EVENT_ID, selectedEvent.getId());
          newEventActivityIntent.putExtra(INTENT_EVENT_ACTION, INTENT_EDIT_EVENT_ACTION);
          ((Activity) context).startActivityForResult(newEventActivityIntent, 100);
          return true;
        }
      });
      contextMenu.add("delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
          AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
              mDb.eventDao().delete(selectedEvent);
            }
          });
          mEventList.remove(selectedEvent);
          setEvents(mEventList);
          return true;
        }
      });
    }
  }
}
