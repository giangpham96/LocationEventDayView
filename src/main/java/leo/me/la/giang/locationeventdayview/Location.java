package leo.me.la.giang.locationeventdayview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class Location {

    private String location;
    private List<ReservedSlot> items;

    public Location(String location, List<ReservedSlot> items) {
        this.location = location;
        this.items = items;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<ReservedSlot> getItems() {
        if (items == null)
            return new ArrayList<>();
        return items;
    }

    public void setItems(List<ReservedSlot> items) {
        this.items = items;
    }
}
