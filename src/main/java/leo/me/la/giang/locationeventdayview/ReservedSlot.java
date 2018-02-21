package leo.me.la.giang.locationeventdayview;

/**
 * Created by giang on 2/16/18.
 */

public class ReservedSlot extends Slot {

    private String title;
    private String organizer;

    public ReservedSlot(long startTime, long endTime) {
        super(startTime, endTime);
    }

    public ReservedSlot(long startTime, long endTime, String title, String organizer) {
        super(startTime, endTime);
        this.title = title;
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}
