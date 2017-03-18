package com.appl_maint_mngt.repositories.property;

import com.appl_maint_mngt.models.property.IPropertyReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyReadableRepository {

    List<IPropertyReadable> getAll();

    IPropertyReadable getForId(Long id);
}
