# LocationEventDayView

A clone of the calendar view from [Dare to learn][1] mobile application

## Demo behavior
![Demo behavior][gif]

[1]: https://play.google.com/store/apps/details?id=com.daretolearn
[gif]: https://raw.githubusercontent.com/giangpham96/LocationEventDayView/master/repo_asset/demo_implementation.gif

## Installation
Currently, this library is not on Maven or jcenter (it will be in the future). Therefore, you have to clone this
repository and put it in the root of your project.
<br><br>
After that, simply add the module to the dependencies block inside build.gradle (application level):
```
dependencies {
        ...
        implementation project (':locationeventdayview')
        ...
}
```
That's all it takes to integrate this libarary to your project.

## Usage
### Data source
A <b>`ReservedSlot`</b> represents an interval of 
time in which an event occurs
<br><br>
A <b>`Location`</b> represents a place where events, 
represented by <b>`ReservedSlot`</b>, occur . 
It has a list of <b>`ReservedSlot`</b> and the name 
of the location
<br><br>
An <b>`EventLocation`</b> contains a list of 
<b>`Location`</b>, and it has the `startTime` 
and `endTime` of the schedule
<br><br>

#### Example
```java
//create the schedule

EventLocation schedule = new EventLocation(1519887600000L,
        1519930800000L,
        new ArrayList<Location>());

//create the location

Location item1 = new Location("Main stage",
        new ArrayList<ReservedSlot>());

//create reserved slots and add to the location slot list

ReservedSlot reservedSlot1 = new ReservedSlot(1519887600000L,
        1519891200000L,
        "Breakfast and event registration",
        "Laurea Bar");
        ...
        
item1.getItems().add(reservedSlot1);
        ...
        
//create another location

Location item2 = new Location("Yard",
        new ArrayList<ReservedSlot>());
startTime = schedule.getStartTime();
Random random = new Random();
for(int i = 1; i <=12; i++) {
    int n = random.nextInt();
    if (n % 2 == 0) {
        item2.getItems().add(new ReservedSlot(startTime,
                startTime + 60 * 60 * 1000,
                "Yard " + i,
                "Organizer " + i));
    }
    startTime = startTime + 60*60*1000;
}

        ...
        
// add all locations to schedule
schedule.getLocations().add(item1);
schedule.getLocations().add(item2);
        ...
```

For your convenience, you can subclass 
<b>`ReservedSlot`</b>, <b>`Location`</b>, and even <b>`EventLocation`</b> and add extra fields as long as it
meets your desire.

### View

This view should not be added to xml file, add it
to your activity or fragment programmatically instead

```java
    ...
LocationEventDayView locationEventDayView = new LocationEventDayView(MainActivity.this, schedule);
LinearLayout rootView = findViewById(R.id.rootView);
rootView.addView(locationEventDayView, MATCH_PARENT, MATCH_PARENT);
    ...
```

### Slot Click Listener
```java
    ...
locationEventDayView.setItemClickListener(new OnEventClickListener() {
    @Override
    public void onClick(ReservedSlot event) {
        Toast.makeText(MainActivity.this, event.getTitle(), Toast.LENGTH_SHORT)
                .show();
    }
});
    ...
```

## Conclusion
Feel free to contribute to this repo
