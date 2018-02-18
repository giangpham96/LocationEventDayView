package leo.me.la.giang.locationeventdayview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giang on 2/16/18.
 */

public class LocationEventDayView extends FrameLayout {

    private LinearLayout headerView;
    private LinearLayout recyclers;
    private List<RecyclerView> recyclerViews;
    private DateSchedule dateSchedule;
    private long slotLength = 30 * 60 * 1000;
    private OnEventClickListener listener;

    public LocationEventDayView(Context context, DateSchedule dateSchedule, long slotLength) {
        super(context);
        this.dateSchedule = dateSchedule;
        this.slotLength = slotLength;
        init(context);
    }

    public LocationEventDayView(Context context, DateSchedule dateSchedule) {
        super(context);
        this.dateSchedule = dateSchedule;
        init(context);
    }

    private LocationEventDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private LocationEventDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private LocationEventDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        View rootView = inflate(context,
                R.layout.layout_location_event_day_view,
                this);
        headerView = rootView.findViewById(R.id.header);
        recyclers = rootView.findViewById(R.id.rcv);

        recyclerViews = new ArrayList<>();

        setupHeader(context);
        setupRecyclerViews(context);
    }

    private void setupHeader(Context context) {
        putTextViewInHeader(context, "", Color.WHITE, 0.3f);
        for (LocationItem locationItem : dateSchedule.getLocationItems()) {
            putTextViewInHeader(context,
                    locationItem.getLocation(),
                    getResources().getDrawable(R.drawable.bg_header),
                    1f);
        }
    }

    private void setupRecyclerViews(Context context) {
        recyclerViews.clear();
        List<ScheduleItem> timeIndicatorItems = new ArrayList<>();
        long numberOfSlots = (long) Math.ceil((dateSchedule.getEndTime()
                - dateSchedule.getStartTime()) / (float) slotLength);
        long startTime = dateSchedule.getStartTime();
        for (int i = 0; i < numberOfSlots; i++) {
            timeIndicatorItems.add(new TimeIndicatorItem(
                    startTime,
                    (i == numberOfSlots - 1)
                            ? dateSchedule.getEndTime()
                            : startTime + slotLength
            ));
            startTime += slotLength;
        }

        putRecyclerViewInRecycles(context, timeIndicatorItems, 0.3f);

        for (LocationItem locationItem : dateSchedule.getLocationItems()) {
            putRecyclerViewInRecycles(context,
                    Utils.addUnreservedItem(locationItem.getItems(),
                            dateSchedule.getStartTime(),
                            dateSchedule.getEndTime()),
                    1f);
        }
    }

    private void putTextViewInHeader(Context context, String label, int color, float weight) {
        TextView textView = new TextView(context);
        textView.setText(label);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(color);
        textView.setTextSize(14f);
        textView.setGravity(Gravity.CENTER);
        headerView.addView(textView);
        textView.setLayoutParams(getVerticalLayoutParamsWithWeight(weight));
    }

    private void putTextViewInHeader(Context context, String label, Drawable drawable, float weight) {
        TextView textView = new TextView(context);
        textView.setText(label);
        textView.setTextColor(Color.WHITE);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackgroundDrawable(drawable);
        } else {
            textView.setBackground(drawable);
        }
        textView.setTextSize(14f);
        textView.setGravity(Gravity.CENTER);
        headerView.addView(textView);
        textView.setLayoutParams(getVerticalLayoutParamsWithWeight(weight));
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            for (RecyclerView rcv :
                    recyclerViews) {
                if (rcv == recyclerView) {
                    continue;
                }
                rcv.removeOnScrollListener(this);
                rcv.scrollBy(dx, dy);
                rcv.addOnScrollListener(this);
            }
        }
    };

    private void putRecyclerViewInRecycles(Context context, List<ScheduleItem> scheduleItems, float weight) {
        RecyclerView recyclerView = new RecyclerView(context);
        LocationEventDayViewAdapter adapter =
                new DefaultAdapter(scheduleItems, slotLength, R.layout.item_event);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL,
                        false);
        if (listener != null)
            adapter.setItemClickListener(listener);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclers.addView(recyclerView);
        recyclerView.setLayoutParams(getVerticalLayoutParamsWithWeight(weight));
        recyclerViews.add(recyclerView);
        recyclerView.addOnScrollListener(scrollListener);
    }

    private LinearLayout.LayoutParams getVerticalLayoutParamsWithWeight(float weight) {
        return new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0, weight);
    }

    public DateSchedule getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(DateSchedule dateSchedule) {
        this.dateSchedule = dateSchedule;
        headerView.removeAllViews();
        recyclers.removeAllViews();
        setupHeader(getContext());
        setupRecyclerViews(getContext());
    }

    public long getSlotLength() {
        return slotLength;
    }

    public void setSlotLength(long slotLength) {
        this.slotLength = slotLength;
        recyclers.removeAllViews();
        setupRecyclerViews(getContext());
    }

    public void setItemClickListener(OnEventClickListener listener) {
        this.listener = listener;
        for (RecyclerView rcv :
                recyclerViews) {
            ((LocationEventDayViewAdapter) rcv.getAdapter()).setItemClickListener(listener);
        }
    }
}
