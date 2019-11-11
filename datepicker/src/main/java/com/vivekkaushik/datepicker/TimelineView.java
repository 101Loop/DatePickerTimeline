package com.vivekkaushik.datepicker;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vivekkaushik.datepicker.adapter.TimelineAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimelineView extends RecyclerView {
    private static final String TAG = "TimelineView";
    private TimelineAdapter adapter;

    private int monthTextColor, dateTextColor, dayTextColor, selectedColor, disabledColor;
//    private float monthTextSize, dateTextSize, dayTextSize;
    private int year, month, date;

    public TimelineView(@NonNull Context context) {
        super(context);
        init();
    }

    public TimelineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TimelineView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init() {
        year = 1970;
        month = 0;
        date = 1;
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                false));
        adapter = new TimelineAdapter(this, -1);
        setAdapter(adapter);
    }

    public int getMonthTextColor() {
        return monthTextColor;
    }

    public void setMonthTextColor(int color) {
        this.monthTextColor = color;
    }

    public int getDateTextColor() {
        return dateTextColor;
    }

    public void setDateTextColor(int color) {
        this.dateTextColor = color;
    }

    public int getDayTextColor() {
        return dayTextColor;
    }

    public void setDayTextColor(int color) {
        this.dayTextColor = color;
    }

    public void setDisabledDateColor(int color) {
        this.disabledColor = color;
    }

    public int getDisabledDateColor() {
        return disabledColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int color) {
        this.selectedColor = color;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        adapter.setDateSelectedListener(listener);
    }

    public void setInitialDate(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
        invalidate();
    }

    /**
     * Calculates the date position and set the selected background on that date
     * @param activeDate active Date
     */
    public void setActiveDate(Calendar activeDate) {
        try {
            Date initialDate = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(year + "-" + (month + 1) + "-" + this.date);
            long diff =  activeDate.getTime().getTime() - initialDate.getTime();
            int position = (int) (diff / (1000 * 60 * 60 * 24));
            adapter.setSelectedPosition(position);
            invalidate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void deactivateDates(Date[] deactivatedDates) {
        adapter.disableDates(deactivatedDates);
    }
}
