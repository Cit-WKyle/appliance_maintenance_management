INSERT INTO property_managers(account_id) SELECT 1 WHERE NOT EXISTS(SELECT 1 FROM property_managers WHERE account_id=1)

INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 1 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=1)
INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 3 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=3)
INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 4 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=4)
INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 5 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=5)
INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 6 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=6)
INSERT INTO current_property_ids(manager_id, property_id) SELECT 1, 7 WHERE NOT EXISTS(SELECT 1 FROM current_property_ids WHERE property_id=7)

INSERT INTO previous_property_ids(manager_id, property_id) SELECT 1, 2 WHERE NOT EXISTS(SELECT 1 FROM previous_property_ids WHERE property_id=2)