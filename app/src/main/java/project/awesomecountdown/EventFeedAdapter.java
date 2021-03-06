package project.awesomecountdown;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;
import project.awesomecountdown.ExpiredEventsAdapter.ViewHolder2;

public class EventFeedAdapter extends RecyclerView.Adapter<EventFeedAdapter.ViewHolder> {

    private EventFeedClickListener mListener;

    private Context mContext;

    private List<EventFeedDetails> mEventFeedDetails;

    public EventFeedAdapter(final Context context, final List<EventFeedDetails> eventFeedDetails,
            EventFeedClickListener listener) {
        mContext = context;
        mEventFeedDetails = eventFeedDetails;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mEventFeedDetails.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_event_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Drawable calendarIcon = mContext.getResources().getDrawable(R.drawable.ic_timer_black_24dp, null);
        Drawable locationIcon = mContext.getResources().getDrawable(R.drawable.location_icon, null);

        String title = mEventFeedDetails.get(position).getEventName();
        String localDate = mEventFeedDetails.get(position).getEventLocalDate();
        String eventLocalTime = mEventFeedDetails.get(position).getEventLocalTime();
        String eventImage16_9 = mEventFeedDetails.get(position).getEventImage16_9();
        String eventLocationName = mEventFeedDetails.get(position).getEventLocationName();

        String displayDate = "TBD";

        if (localDate != null) {
            displayDate = localDate;
            if (eventLocalTime != null) {
                displayDate = localDate + " · " + eventLocalTime;
            }
        }

        holder.date.setText(displayDate);

        holder.title.setText(title);

        holder.location.setText(eventLocationName);

        holder.dateIcon.setBackground(calendarIcon);
        holder.locationIcon.setBackground(locationIcon);

        Picasso.get()
                .load(eventImage16_9)
                .into(holder.image);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, location;

        CircleImageView image;

        ImageView dateIcon, locationIcon;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.eventfeed_title_txtview);
            date = itemView.findViewById(R.id.eventfeed_rv_startdate_txv);
            location = itemView.findViewById(R.id.eventfeed_rv_location_txv);

            image = itemView.findViewById(R.id.event_rv_imageview);
            dateIcon = itemView.findViewById(R.id.rv_eventFeed_dateIcon_imgView);
            locationIcon = itemView.findViewById(R.id.rv_eventFeed_locationIcon_imgView);

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    int position = getAdapterPosition();

                    if (mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onClick(position);
                    }
                }
            });
        }

    }

    public interface EventFeedClickListener {

        void onClick(int position);
    }
}
