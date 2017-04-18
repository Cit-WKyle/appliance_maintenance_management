INSERT INTO diagnostic_reports(id, prop_appl_id, issued_time, description) SELECT 1, 1, '2017-01-01 01:01:01', 'DESCRIPTION' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_reports WHERE id=1)
INSERT INTO diagnostic_reports(id, prop_appl_id, issued_time, description) SELECT 2, 3, '2017-01-01 01:01:01', 'DESCRIPTION' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_reports WHERE id=2)
