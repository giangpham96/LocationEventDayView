package leo.me.la.giang.locationeventdayview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class EventLocation {
    private long startTime;
    private long endTime;
    private List<Location> locations;

    public EventLocation(long startTime, long endTime, List<Location> locations) {
        this.startTime = (startTime / 60000) * 60000;
        this.endTime = (endTime / 60000) * 60000;
        this.locations = locations;
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

    public List<Location> getLocations() {
        if (locations == null) {
            locations = new ArrayList<>();
        }
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
