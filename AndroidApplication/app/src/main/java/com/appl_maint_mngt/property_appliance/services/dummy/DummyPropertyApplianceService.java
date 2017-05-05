package com.appl_maint_mngt.property_appliance.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance.models.PropertyAppliance;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.status_history.models.StatusHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class DummyPropertyApplianceService implements IPropertyApplianceService {
    @Override
    public void get(Long id, IErrorCallback errorCallback) {
//        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
//
//        PropertyAppliance propertyAppliance1 = new PropertyAppliance();
//        propertyAppliance1.setId(id);
//        propertyAppliance1.setApplianceId("1");
//        propertyAppliance1.setStatusId((long) 1);
//        propertyAppliance1.setPropertyId((long) 1);
//        propertyAppliance1.setAge(5);
//        propertyAppliance1.setName("Name");
//        propertyAppliance1.setStatusHistory(new ArrayList<StatusHistory>());
//
//        repository.addItem(propertyAppliance1);
        findByPropertyId((long) 0, errorCallback);
    }

    @Override
    public void findByPropertyId(Long propertyId, IErrorCallback callback) {
        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
        List<PropertyAppliance> propertyApplianceList = new ArrayList<>();

        PropertyAppliance propertyAppliance1 = new PropertyAppliance();
        propertyAppliance1.setId((long) 1);
        propertyAppliance1.setApplianceId("1");
        propertyAppliance1.setStatusId((long) 1);
        propertyAppliance1.setPropertyId((long) 1);
        propertyAppliance1.setAge(5);
        propertyAppliance1.setName("Name");
        propertyAppliance1.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance2 = new PropertyAppliance();
        propertyAppliance2.setId((long) 2);
        propertyAppliance2.setApplianceId("1");
        propertyAppliance2.setStatusId((long) 2);
        propertyAppliance2.setPropertyId((long) 1);
        propertyAppliance2.setAge(5);
        propertyAppliance2.setName("Name2");
        propertyAppliance2.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance3 = new PropertyAppliance();
        propertyAppliance3.setId((long) 3);
        propertyAppliance3.setApplianceId("1");
        propertyAppliance3.setStatusId((long) 2);
        propertyAppliance3.setPropertyId((long) 1);
        propertyAppliance3.setAge(5);
        propertyAppliance3.setName("Name3");
        propertyAppliance3.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance4 = new PropertyAppliance();
        propertyAppliance4.setId((long) 4);
        propertyAppliance4.setApplianceId("1");
        propertyAppliance4.setStatusId((long) 2);
        propertyAppliance4.setPropertyId((long) 1);
        propertyAppliance4.setAge(5);
        propertyAppliance4.setName("Name4");
        propertyAppliance4.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance5 = new PropertyAppliance();
        propertyAppliance5.setId((long) 5);
        propertyAppliance5.setApplianceId("1");
        propertyAppliance5.setStatusId((long) 2);
        propertyAppliance5.setPropertyId((long) 1);
        propertyAppliance5.setAge(5);
        propertyAppliance5.setName("Name5");
        propertyAppliance5.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance6 = new PropertyAppliance();
        propertyAppliance6.setId((long) 6);
        propertyAppliance6.setApplianceId("1");
        propertyAppliance6.setStatusId((long) 2);
        propertyAppliance6.setPropertyId((long) 1);
        propertyAppliance6.setAge(5);
        propertyAppliance6.setName("Name6");
        propertyAppliance6.setStatusHistory(new ArrayList<StatusHistory>());

        PropertyAppliance propertyAppliance7 = new PropertyAppliance();
        propertyAppliance7.setId((long) 7);
        propertyAppliance7.setApplianceId("1");
        propertyAppliance7.setStatusId((long) 3);
        propertyAppliance7.setPropertyId((long) 1);
        propertyAppliance7.setAge(5);
        propertyAppliance7.setName("Name6");
        propertyAppliance7.setStatusHistory(new ArrayList<StatusHistory>());

        propertyApplianceList.add(propertyAppliance1);
        propertyApplianceList.add(propertyAppliance2);
        propertyApplianceList.add(propertyAppliance3);
        propertyApplianceList.add(propertyAppliance4);
        propertyApplianceList.add(propertyAppliance5);
        propertyApplianceList.add(propertyAppliance6);
        propertyApplianceList.add(propertyAppliance7);
        repository.addItems(propertyApplianceList);
    }

    @Override
    public void findByPropertyIds(List<Long> propertyIds, IErrorCallback callback) {
//        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
//        List<PropertyAppliance> propertyApplianceList = new ArrayList<>();
//        long counter = 1;
//        for(Long id : propertyIds) {
//            PropertyAppliance propertyAppliance = new PropertyAppliance();
//            propertyAppliance.setId(counter);
//            propertyAppliance.setPropertyId(id);
//            propertyAppliance.setApplianceId("1");
//            propertyAppliance.setStatusId((long) 1);
//            propertyAppliance.setAge(5);
//            propertyAppliance.setName("Name");
//            propertyAppliance.setStatusHistory(new ArrayList<StatusHistory>());
//            propertyApplianceList.add(propertyAppliance);
//            counter++;
//        }
//        repository.addItems(propertyApplianceList);
        findByPropertyId((long) 0, callback);
    }
}
