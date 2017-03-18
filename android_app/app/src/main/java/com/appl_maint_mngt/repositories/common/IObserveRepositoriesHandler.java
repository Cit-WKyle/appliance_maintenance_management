package com.appl_maint_mngt.repositories.common;

import java.util.Observer;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IObserveRepositoriesHandler {

    void observeAccountRepository(Observer obs);

    void observePropertyManagerRepository(Observer obs);

    void observePropertyRepository(Observer obs);

    void observePropertyApplianceRepository(Observer obs);

    void observeApplianceStatusRepository(Observer obs);

    void observeApplianceRepository(Observer obs);

    void observeDiagnosticReportRepository(Observer obs);

}
