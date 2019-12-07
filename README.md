# DatePickerTimeline
An android package that provides a Horizontal Date Picker.

[ ![Download](https://api.bintray.com/packages/iamvivekkaushik/DatePickerLibrary/datePickerTimeline/images/download.svg) ](https://bintray.com/iamvivekkaushik/DatePickerLibrary/datePickerTimeline/_latestVersion)

<img src="https://github.com/101Loop/DatePickerTimeline/blob/master/screenshots/datePicker.png?raw=true" alt="DatePicker Logo" />


## Gradle

In your module (app-level) Gradle file (usually `app/build.gradle`), Add this line inside the `dependencies` block.

```java
dependencies {
    ...
    implementation 'com.vivekkaushik.datepicker:datePickerTimeline:0.0.4'
}
```

## Usage
Add `DatePickerTimeline` View to your layout file

```xml
<com.vivekkaushik.datepicker.DatePickerTimeline
android:id="@+id/datePickerTimeline"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginStart="8dp"
android:layout_marginTop="8dp"
android:layout_marginEnd="8dp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent" />
```

##### Properties:
You can use the following properties in your XML to change your DatePickerTimeline colors.

* `app:dateTextColor`         (color)   -> default -> Black
* `app:dayTextColor`          (color)   -> default -> Black
* `app:monthTextColor`        (color)   -> default -> Black
* `app:disabledColor`         (color)   -> default -> Grey

JAVA
-----

```java
DatePickerTimeline datePickerTimeline = findViewById(R.id.dateTimeline);
// Set a Start date (Default, 1 Jan 1970)
datePickerTimeline.setInitialDate(2019, 3, 21);
// Set a date Selected Listener
datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
    @Override
    public void onDateSelected(int year, int month, int day, int dayOfWeek) {
        // Do Something
    }

    @Override
    public void onDisabledDateSelected(int year, int month, int day, int dayOfWeek, boolean isDisabled) {
        // Do Something
    }
});

// Disable date
Date[] dates = {Calendar.getInstance().getTime()};
datePickerTimeline.deactivateDates(dates);
```

You can also use the built in methods to change the text color

```java
datePickerTimeline.setDateTextColor(Color.RED);
datePickerTimeline.setDayTextColor(Color.RED);
datePickerTimeline.setMonthTextColor(Color.RED);
```


LIMITATIONS
-----

* Text Size currently can't be changed


FIXES
-----
* This project is using AndroidX artifacts make sure you have migrated your project to androidX.
* If Preview dosen't show up in android Studio then make sure you have added android recycler view.


Flutter
-----

This library is also available for flutter check the github repo [here](https://github.com/iamvivekkaushik/DatePickerTimelineFlutter).
Also available on [pub.dev](https://pub.dev/packages/date_picker_timeline)


Author
------

* [Vivek Kaushik](http://github.com/iamvivekkaushik/)
