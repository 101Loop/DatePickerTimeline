package com.vivekkaushik.datepicker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivekkaushik.datepicker.OnDateSelectedListener;
import com.vivekkaushik.datepicker.R;
import com.vivekkaushik.datepicker.TimelineView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    private static final String TAG = "TimelineAdapter";
    private static final String[] WEEK_DAYS = DateFormatSymbols.getInstance().getShortWeekdays();
    private static final String[] MONTH_NAME = DateFormatSymbols.getInstance().getShortMonths();

    private Calendar calendar = Calendar.getInstance();
    private TimelineView timelineView;
    private Date[] deactivatedDates;

    private OnDateSelectedListener listener;

    private View selectedView;
    private int selectedPosition;

    public TimelineAdapter(TimelineView timelineView, int selectedPosition) {
        this.timelineView = timelineView;
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline_item_layout, parent, false);
        return new TimelineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        resetCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, position);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        final boolean isDisabled = holder.bind(month, day, dayOfWeek, year, position);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedView != null) {
                    selectedView.setBackground(null);
                }
                if (!isDisabled) {
                    v.setBackground(timelineView.getResources().getDrawable(R.drawable.background_shape));

                    selectedPosition = position;
                    selectedView = v;

                    if (listener != null) listener.onDateSelected(year, month, day, dayOfWeek);
                } else {
                    if (listener != null) listener.onDisabledDateSelected(year, month, day, dayOfWeek, isDisabled);
                }
            }
        });
    }

    private void resetCalendar() {
        calendar.set(timelineView.getYear(), timelineView.getMonth(), timelineView.getDate(),
                1, 0, 0);
    }

    /**
     * Set the position of selected date
     * @param selectedPosition active date Position
     */
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void disableDates(Date[] dates) {
        this.deactivatedDates = dates;
        notifyDataSetChanged();
    }

    public void setDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView monthView, dateView, dayView;
        private View rootView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            monthView = itemView.findViewById(R.id.monthView);
            dateView = itemView.findViewById(R.id.dateView);
            dayView = itemView.findViewById(R.id.dayView);
            rootView = itemView.findViewById(R.id.rootView);
        }

        boolean bind(int month, int day, int dayOfWeek, int year, int position) {
            monthView.setTextColor(timelineView.getMonthTextColor());
            dateView.setTextColor(timelineView.getDateTextColor());
            dayView.setTextColor(timelineView.getDayTextColor());

            dayView.setText(WEEK_DAYS[dayOfWeek].toUpperCase(Locale.US));
            monthView.setText(MONTH_NAME[month].toUpperCase(Locale.US));
            dateView.setText(String.valueOf(day));

            if (selectedPosition == position) {
                rootView.setBackground(timelineView.getResources().getDrawable(R.drawable.background_shape));
                selectedView = rootView;
            } else {
                rootView.setBackground(null);
            }

            for (Date date : deactivatedDates) {
                Calendar tempCalendar = Calendar.getInstance();
                tempCalendar.setTime(date);
                if (tempCalendar.get(Calendar.DAY_OF_MONTH) == day &&
                        tempCalendar.get(Calendar.MONTH) == month &&
                        tempCalendar.get(Calendar.YEAR) == year) {
                    monthView.setTextColor(timelineView.getDisabledDateColor());
                    dateView.setTextColor(timelineView.getDisabledDateColor());
                    dayView.setTextColor(timelineView.getDisabledDateColor());

                    rootView.setBackground(null);
                    return true;
                }
            }

            return false;
        }
    }


}