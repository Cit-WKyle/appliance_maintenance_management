INSERT INTO status_history(id, status_id, logged_time) SELECT 1, 1, '2017-01-01 01:01:01' WHERE NOT EXISTS(SELECT 1 FROM status_history WHERE id=1)

INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 1, '58f539c6faccfd35d35166bb', 1, 2, 'Kitchen Dishwasher', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=1)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 2, '58f539c6faccfd35d35166bc', 1, 1, 'PropertyAppliance2', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=2)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 3, '58f539c6faccfd35d35166bd', 1, 2, 'PropertyAppliance3', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=3)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 4, '58f539c6faccfd35d35166bb', 1, 5, 'PropertyAppliance4', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=4)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 5, '58f539c6faccfd35d35166bc', 2, 1, 'PropertyAppliance5', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=5)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 6, '58f539c6faccfd35d35166bd', 2, 2, 'PropertyAppliance6', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=6)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 7, '58f539c6faccfd35d35166bb', 3, 2, 'PropertyAppliance7', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=7)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 8, '58f539c6faccfd35d35166bc', 3, 4, 'PropertyAppliance8', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=8)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 9, '58f539c6faccfd35d35166bd', 3, 3, 'PropertyAppliance9', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=9)

INSERT INTO pappl_stat_hist(pappl_id, stat_hist_id) SELECT 1, 1 WHERE NOT EXISTS(SELECT 1 FROM pappl_stat_hist WHERE pappl_id=1 AND stat_hist_id=1)
