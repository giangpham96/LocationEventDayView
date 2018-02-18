package leo.me.la.giang.locationeventdayview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by giang on 2/18/18.
 */

public abstract class EventViewHolder extends RecyclerView.ViewHolder {
    EventViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(ScheduleItem item);

    final void resize(ScheduleItem item, int slotWidth, long slotLength) {

        int width = (int) ((float) slotWidth * (item.getEndTime() - item.getStartTime()) / slotLength);

        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = width;

        itemView.setLayoutParams(layoutParams);
    }
}

final class DefaultViewHolder extends EventViewHolder {

    DefaultViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ScheduleItem item) {
        TextView tvEvent = itemView.findViewById(R.id.tvEvent);
        TextView tvOrganizer = itemView.findViewById(R.id.tvOrganizer);
        TextView tvTime = itemView.findViewById(R.id.tvTime);
        if (item instanceof TimeIndicatorItem) {
            tvTime.setText(Utils.formatTime(item.getStartTime()));
        }
        if (item instanceof EventItem) {
            tvEvent.setText(((EventItem) item).getTitle());
            tvEvent.setSelected(true);
            tvOrganizer.setText(((EventItem) item).getOrganizer());
        }
    }
}
