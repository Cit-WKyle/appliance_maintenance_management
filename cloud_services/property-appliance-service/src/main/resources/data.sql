INSERT INTO status_history(id, status_id, logged_time) SELECT 1, 1, '2017-01-01 01:01:01' WHERE NOT EXISTS(SELECT 1 FROM status_history WHERE id=1)

INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 1, '1', 1, 6, 'Kitchen Dishwasher', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=1)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 2, '2', 1, 1, 'PropertyAppliance2', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=2)
INSERT INTO property_appliances(id, appliance_id, property_id, status_id, name, age) SELECT 3, '3', 1, 3, 'Delete Me', 5 WHERE NOT EXISTS(SELECT 1 FROM property_appliances WHERE id=3)

INSERT INTO pappl_stat_hist(pappl_id, stat_hist_id) SELECT 1, 1 WHERE NOT EXISTS(SELECT 1 FROM pappl_stat_hist WHERE pappl_id=1 AND stat_hist_id=1)