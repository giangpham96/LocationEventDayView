package leo.me.la.giang.locationeventdayview;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.EVENT;
import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.TIME_INDICATOR;
import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.UNRESERVED;

/**
 * Created by giang on 2/16/18.
 */

public abstract class LocationEventDayViewAdapter<T extends EventViewHolder> extends RecyclerView.Adapter<EventViewHolder> {

    private List<ScheduleItem> items;
    private long slotLength;
    private int slotWidth = (getScreenWidth() / 10 < 200) ? 200 : getScreenWidth() / 10;
    private int slotViewId;
    private OnEventClickListener listener;

    public LocationEventDayViewAdapter(List<ScheduleItem> items, long slotLength, int slotViewId) {
        this.items = items;
        this.slotLength = slotLength;
        this.slotViewId = slotViewId;
    }

    @Override
    public final int getItemViewType(int position) {
        ScheduleItem item = items.get(position);

        if (item instanceof TimeIndicatorItem) {
            return TIME_INDICATOR;
        } else if (item instanceof EventItem) {
            return EVENT;
        } else {
            return UNRESERVED;
        }
    }

    @Override
    public final EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id;
        switch (viewType) {
            case TIME_INDICATOR:
                id = R.layout.item_time_indicator;
                break;
            case UNRESERVED:
                id = R.layout.item_unreserved;
                break;
            default:
                id = slotViewId;
                return getViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(id, parent, false));
        }

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(id, parent, false);

        return new DefaultViewHolder(itemView);
    }

    @Override
    public final void onBindViewHolder(EventViewHolder holder, int position) {
        final ScheduleItem item = items.get(position);
        holder.resize(item, slotWidth, slotLength);
        holder.bind(item);
        if (item instanceof EventItem) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick((EventItem) item);
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
        }
    }

    @Override
    public final int getItemCount() {
        return items.size();
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public abstract T getViewHolder(View itemView);

    public void setItemClickListener(OnEventClickListener listener) {
        this.listener = listener;
    }
}

final class DefaultAdapter extends LocationEventDayViewAdapter<EventViewHolder> {

    DefaultAdapter(List<ScheduleItem> items, long slotLength, int slotViewId) {
        super(items, slotLength, slotViewId);
    }

    @Override
    public EventViewHolder getViewHolder(View itemView) {
        return new DefaultViewHolder(itemView);
    }
}

