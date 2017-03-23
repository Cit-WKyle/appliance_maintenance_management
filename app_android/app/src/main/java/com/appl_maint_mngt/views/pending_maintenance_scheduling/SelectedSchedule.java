package com.appl_maint_mngt.views.pending_maintenance_scheduling;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;

import org.joda.time.DateTime;

/**
 * Created by Kyle on 22/03/2017.
 */

public class SelectedSchedule {

    private DateTime start;
    private DateTime end;

    public SelectedSchedule(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(MainActivity.getInstance().getString(R.string.schedule_start_time));
        builder.append(start.toString());
        builder.append('\t');
        builder.append(MainActivity.getInstance().getString(R.string.schedule_end_time));
        builder.append(end.toString());
        return builder.toString();
    }
}
