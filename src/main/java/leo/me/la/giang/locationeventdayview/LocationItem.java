package leo.me.la.giang.locationeventdayview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class LocationItem {

    private String location;
    private List<EventItem> items;

    public LocationItem(String location, List<EventItem> items) {
        this.location = location;
        this.items = items;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<EventItem> getItems() {
        if (items == null)
            return new ArrayList<>();
        return items;
    }

    public void setItems(List<EventItem> items) {
        this.items = items;
    }
}
