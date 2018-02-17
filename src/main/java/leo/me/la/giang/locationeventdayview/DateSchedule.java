package leo.me.la.giang.locationeventdayview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class DateSchedule {
    private long startTime;
    private long endTime;
    private List<LocationItem> locationItems;

    public DateSchedule(long startTime, long endTime, List<LocationItem> locationItems) {
        this.startTime = (startTime / 60000) * 60000;
        this.endTime = (endTime / 60000) * 60000;
        this.locationItems = locationItems;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = (startTime / 60000) * 60000;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = (endTime / 60000) * 60000;
    }

    public List<LocationItem> getLocationItems() {
        if (locationItems == null) {
            locationItems = new ArrayList<>();
        }
        return locationItems;
    }

    public void setLocationItems(List<LocationItem> locationItems) {
        this.locationItems = locationItems;
    }
}
