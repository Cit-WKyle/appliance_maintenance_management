package com.appl_maint_mngt.views.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.appl_maint_mngt.R;

import org.joda.time.DateTime;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Kyle on 22/03/2017.
 */
public class DateTimeDialogBuilder {

    private Activity context;
    private DialogInterface.OnClickListener okListener;

    private AlertDialog dialog;

    private int selectedYear;
    private int selectedMonthOfYear;
    private int selectedDayOfMonth;

    private int selectedHourOfDay;
    private int selectedMinute;

    public DateTimeDialogBuilder(Activity context) {
        this.context = context;
    }

    public void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.dialog_add_date_time, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.add_time_action);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);

        DatePicker datePicker = (DatePicker) convertView.findViewById(R.id.dialog_date_time_datepicker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedYear = year;
                selectedMonthOfYear = monthOfYear;
                selectedDayOfMonth = dayOfMonth;
            }
        });


        TimePicker timePicker = (TimePicker) convertView.findViewById(R.id.dialog_date_time_timepicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedHourOfDay = hourOfDay;
                selectedMinute = minute;
            }
        });
        dialog = alertDialog.create();
    }

    public void show() {
        dialog.show();
    }

    public void close() {
        dialog.cancel();
    }

    public DateTime getSelectedDateTime() {
        Calendar calend = Calendar.getInstance();
        calend.set(selectedYear, selectedMonthOfYear, selectedDayOfMonth, selectedHourOfDay, selectedMinute);
        return new DateTime(calend);
    }
}
