package leo.me.la.giang.locationeventdayview;

/**
 * Created by giang on 2/16/18.
 */

abstract class ScheduleItem {

    private long startTime;
    private long endTime;

    ScheduleItem(long startTime, long endTime) {
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

class TimeIndicatorItem extends ScheduleItem {
    public TimeIndicatorItem(long startTime, long endTime) {
        super(startTime, endTime);
    }
}

class UnreservedItem extends ScheduleItem {
    public UnreservedItem(long startTime, long endTime) {
        super(startTime, endTime);
    }
}