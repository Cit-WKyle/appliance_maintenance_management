INSERT INTO property_tenants(account_id, current_property_id, residence_status) SELECT 3, 1, 'OCCUPANT' WHERE NOT EXISTS(SELECT 1 FROM property_tenants WHERE account_id=3)
INSERT INTO property_tenants(account_id, current_property_id, residence_status) SELECT 4, 1, 'PENDING' WHERE NOT EXISTS(SELECT 1 FROM property_tenants WHERE account_id=4)
INSERT INTO property_tenants(account_id, current_property_id, residence_status) SELECT 5, 1, 'OCCUPANT' WHERE NOT EXISTS(SELECT 1 FROM property_tenants WHERE account_id=5)