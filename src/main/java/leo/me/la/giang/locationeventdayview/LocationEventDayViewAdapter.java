package leo.me.la.giang.locationeventdayview;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.EVENT;
import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.TIME_INDICATOR;
import static leo.me.la.giang.locationeventdayview.Constant.ScheduleItemTypes.UNRESERVED;

/**
 * Created by giang on 2/16/18.
 */

class LocationEventDayViewAdapter extends RecyclerView.Adapter<LocationEventDayViewAdapter.ViewHolder> {

    private List<ScheduleItem> items;
    private long slotLength;
    private int slotWidth = (getScreenWidth() / 10 < 200) ? 200 : getScreenWidth() / 10;
    private int slotHeight;

    LocationEventDayViewAdapter(List<ScheduleItem> items, long slotLength) {
        this.items = items;
        this.slotLength = slotLength;
    }

    @Override
    public int getItemViewType(int position) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id;
        switch (viewType) {
            case TIME_INDICATOR:
                id = R.layout.item_time_indicator;
                break;
            case EVENT:
                id = R.layout.item_event;
                break;
            default:
                id = R.layout.item_unreserved;
                break;
        }

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(id, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleItem item = items.get(position);
        holder.bind(item, slotWidth, slotHeight, slotLength);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEvent, tvOrganizer, tvTime;
        ViewHolder(View itemView) {
            super(itemView);
            tvEvent = itemView.findViewById(R.id.tvEvent);
            tvOrganizer = itemView.findViewById(R.id.tvOrganizer);
            tvTime = itemView.findViewById(R.id.tvTime);
        }

        void bind(ScheduleItem item, int slotWidth, int slotHeight, long slotLength) {

            if (item instanceof TimeIndicatorItem) {
                tvTime.setText(Utils.formatTime(item.getStartTime()));
            }
            if (item instanceof EventItem) {
                tvEvent.setText(((EventItem) item).getTitle());
                tvEvent.setSelected(true);
                tvOrganizer.setText(((EventItem) item).getOrganizer());
            }

            resize(item, slotWidth, slotHeight, slotLength);
        }

        private void resize(ScheduleItem item, int slotWidth, int slotHeight, long slotLength) {

            int width = (int) ((float) slotWidth * (item.getEndTime() - item.getStartTime()) / slotLength);

            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            layoutParams.width = width;

            itemView.setLayoutParams(layoutParams);
        }
    }
}
