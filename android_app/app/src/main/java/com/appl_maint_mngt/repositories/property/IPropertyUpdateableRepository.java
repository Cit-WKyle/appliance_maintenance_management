package com.appl_maint_mngt.repositories.property;

import com.appl_maint_mngt.models.property.AProperty;
import com.appl_maint_mngt.models.property.Property;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyUpdateableRepository {

    void addItem(AProperty property);

    void addItems(List<Property> properties);
}
