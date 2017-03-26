package com.appl_maint_mngt.forms.pending_repair_report;

import java.math.BigDecimal;

/**
 * Created by Kyle on 25/03/2017.
 */

public class PendingRepairReportForm {
    private int duration;
    private BigDecimal cost;
    private boolean onSite;
    private String description;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isOnSite() {
        return onSite;
    }

    public void setOnSite(boolean onSite) {
        this.onSite = onSite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
