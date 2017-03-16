INSERT INTO auth_users(id, email, password, user_type) SELECT 1, 'manager1@test.ie', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'PROPERTY_MANAGER' WHERE NOT EXISTS(SELECT 1 FROM auth_users WHERE id=1)

INSERT INTO auth_users(id, email, password, user_type) SELECT 2, 'tenant2@test.ie', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'PROPERTY_TENANT' WHERE NOT EXISTS(SELECT 1 FROM auth_users WHERE id=2)
INSERT INTO auth_users(id, email, password, user_type) SELECT 3, 'tenant3@test.ie', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'PROPERTY_TENANT' WHERE NOT EXISTS(SELECT 1 FROM auth_users WHERE id=3)
INSERT INTO auth_users(id, email, password, user_type) SELECT 4, 'tenant4@test.ie', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'PROPERTY_TENANT' WHERE NOT EXISTS(SELECT 1 FROM auth_users WHERE id=4)

INSERT INTO auth_users(id, email, password, user_type) SELECT 5, 'engineer@test.ie', '$2a$10$bnC26zz//2cavYoSCrlHdecWF8tkGfPodlHcYwlACBBwJvcEf0p2G', 'MAINTENANCE_ENGINEER' WHERE NOT EXISTS(SELECT 1 FROM auth_users WHERE id=5)

INSERT INTO user_roles(user_auth_id, role) SELECT 1, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE user_auth_id=1)
INSERT INTO user_roles(user_auth_id, role) SELECT 2, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE user_auth_id=2)
INSERT INTO user_roles(user_auth_id, role) SELECT 3, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE user_auth_id=3)
INSERT INTO user_roles(user_auth_id, role) SELECT 4, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE user_auth_id=4)
INSERT INTO user_roles(user_auth_id, role) SELECT 5, 'MEMBER' WHERE NOT EXISTS(SELECT 1 FROM user_roles WHERE user_auth_id=5)