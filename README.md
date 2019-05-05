# DatePickerTimeline
An android package that provides a horizontal date picker

[ ![Download](https://api.bintray.com/packages/iamvivekkaushik/DatePickerLibrary/datePickerTimeline/images/download.svg) ](https://bintray.com/iamvivekkaushik/DatePickerLibrary/datePickerTimeline/_latestVersion)

<img src="/screenshots/datePicker.png" alt="DatePicker Logo" />

## Gradle

In your module (app-level) Gradle file (usually `app/build.gradle`), Add this line inside the `dependencies` block.

```
dependencies {
    ...
    implementation 'com.vivekkaushik.datepicker:datePickerTimeline:0.0.1'
}
```

## Usage
Add `DatePickerTimeline` View to your layout file

```
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

JAVA
-----

```
DatePickerTimeline datePickerTimeline = findViewById(R.id.dateTimeline);
// Set a Start date (Default, 1 Jan 1970)
datePickerTimeline.setInitialDate(2019, 3, 21);
// Set a date Selected Listener
datePickerTimeline.setOnDateSelectedListener(new OnDateSelectedListener() {
    @Override
    public void onDateSelected(int year, int month, int day, int dayOfWeek) {
        //Do Something
    }
});
```

You can also use the built in methods to change the text color

```
datePickerTimeline.setDateTextColor(Color.RED);
datePickerTimeline.setDayTextColor(Color.RED);
datePickerTimeline.setMonthTextColor(Color.RED);
```


LIMITATIONS
-----

* Text Size currently can't be changed


FIXES
-----
* This project is using androidX artifacts make sure you have migrated your project to androidX.
* If Preview dosen't show up in android Studio then make sure you have added android recycler view.


Creator
-------

* [Vivek Kaushik](http://github.com/iamvivekkaushik/)