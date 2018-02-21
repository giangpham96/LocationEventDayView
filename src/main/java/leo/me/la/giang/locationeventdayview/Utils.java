package leo.me.la.giang.locationeventdayview;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

class Utils {
    static String formatTime(long time) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(time);
    }

    static List<Slot> addUnreservedItem(List<ReservedSlot> reservedSlots, long startTime, long endTime) {
        List<Slot> items = new ArrayList<>();
        items.addAll(reservedSlots);

        for (int i = items.size() - 1; i >= 0; i--) {
            Slot item = items.get(i);
            Slot nextItem = (i > 0)
                    ? items.get(i - 1)
                    : null;
            if (i == 0 && item.getStartTime() > startTime) {
                items.add(0, new UnreservedSlot(startTime, item.getStartTime()));
            } else if (i == items.size() - 1 && item.getEndTime() < endTime) {
                items.add(new UnreservedSlot(item.getEndTime(), endTime));
            }

            if (nextItem != null &&
                    item.getStartTime() > nextItem.getEndTime()) {
                items.add(i, new UnreservedSlot(
                        nextItem.getEndTime(),
                        item.getStartTime()
                ));
            }
        }
        return items;
    }
}
