INSERT INTO diagnostic_requests(id, diagnotsic_report_id, maintenance_organisation_id, response_status) SELECT 1, 1, 1, 'RESPONDED' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=1)
INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 2, 1, 2, 'CANCELLED' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=2)
INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 3, 1, 3, 'CANCELLED' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=3)

INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 4, 2, 1, 'PENDING' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=4)
INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 5, 2, 2, 'PENDING' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=5)
INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 6, 2, 3, 'DECLINED' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=6)
INSERT INTO diagnostic_requests(id, diagnostic_report_id, maintenance_organisation_id, response_status) SELECT 7, 2, 3, 'CANCELLED' WHERE NOT EXISTS(SELECT 1 FROM diagnostic_requests WHERE id=7)
