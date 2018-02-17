package leo.me.la.giang.locationeventdayview;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class Utils {
    public static String formatTime(long time) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(time);
    }

    static List<ScheduleItem> addUnreservedItem(List<EventItem> eventItems, long startTime, long endTime) {
        List<ScheduleItem> items = new ArrayList<>();
        items.addAll(eventItems);

        for (int i = items.size() - 1; i >= 0; i--) {
            ScheduleItem item = items.get(i);
            ScheduleItem nextItem = (i > 0)
                    ? items.get(i - 1)
                    : null;
            if (i == 0 && item.getStartTime() > startTime) {
                items.add(0, new UnreservedItem(startTime, item.getStartTime()));
            } else if (i == items.size() - 1 && item.getEndTime() < endTime) {
                items.add(new UnreservedItem(item.getEndTime(), endTime));
            }

            if (nextItem != null &&
                    item.getStartTime() > nextItem.getEndTime()) {
                items.add(i, new UnreservedItem(
                        nextItem.getEndTime(),
                        item.getStartTime()
                ));
            }
        }
        return items;
    }
}
