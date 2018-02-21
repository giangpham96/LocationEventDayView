package leo.me.la.giang.locationeventdayview;

/**
 * Created by giang on 2/16/18.
 */

abstract class Slot {

    private long startTime;
    private long endTime;

    Slot(long startTime, long endTime) {
        this.startTime = (startTime / 60000) * 60000;
        this.endTime = (endTime / 60000) * 60000;
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
}

class TimeIndicator extends Slot {
    public TimeIndicator(long startTime, long endTime) {
        super(startTime, endTime);
    }
}

class UnreservedSlot extends Slot {
    public UnreservedSlot(long startTime, long endTime) {
        super(startTime, endTime);
    }
}