package com.appl_maint_mngt.models.property_appliance;

import com.appl_maint_mngt.models.status_history.IStatusHistoryReadable;
import com.appl_maint_mngt.models.status_history.StatusHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public class PropertyAppliance extends APropertyAppliance {

    private Long id;

    private String applianceId;

    private Long propertyId;

    private Long statusId;

    private String name;

    private int age;

    private List<StatusHistory> statusHistory;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getApplianceId() {
        return applianceId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    @Override
    public Long getPropertyId() {
        return propertyId;
    }

    @Override
    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public Long getStatusId() {
        return statusId;
    }

    @Override
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public List<IStatusHistoryReadable> getStatusHistory() {
        List<IStatusHistoryReadable> list = new ArrayList<>();
        for(StatusHistory sHist: this.statusHistory) {
            list.add(sHist);
        }
        return list;
    }

    @Override
    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }
}
