package com.vivekkaushik.datepicker.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivekkaushik.datepicker.OnDateSelectedListener;
import com.vivekkaushik.datepicker.R;
import com.vivekkaushik.datepicker.TimelineView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    private static final String[] WEEK_DAYS = DateFormatSymbols.getInstance().getShortWeekdays();
    private static final String[] MONTH_NAME = DateFormatSymbols.getInstance().getShortMonths();

    private Calendar calendar = Calendar.getInstance();
    private TimelineView timelineView;

    private OnDateSelectedListener listener;

    private View selectedView;
    private int selectedPosition = -1;

    public TimelineAdapter(TimelineView timelineView) {
        this.timelineView = timelineView;
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

        holder.bind(month, day, dayOfWeek, position);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedView != null) {
                    selectedView.setBackground(null);
                }
                v.setBackground(timelineView.getResources().getDrawable(R.drawable.background_shape));

                selectedPosition = position;
                selectedView = v;

                if (listener != null) listener.onDateSelected(year, month, day, dayOfWeek);
            }
        });
    }

    private void resetCalendar() {
        calendar.set(timelineView.getYear(), timelineView.getMonth(), timelineView.getDate(),
                1, 0, 0);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
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

        void bind(int month, int day, int dayOfWeek, int position) {
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
        }
    }


}