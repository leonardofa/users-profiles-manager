INSERT IGNORE INTO profile (id, created_date, created_by, last_modified_date, last_modified_by, name, description, type)
    VALUES (UUID(), utc_timestamp, 'flyway:migration', utc_timestamp, 'flyway:migration', 'ADMINISTRATOR', 'ADMINISTRATOR profile - must not delete', 'ADMINISTRATOR');

INSERT IGNORE INTO user (id, created_date, created_by, last_modified_date, last_modified_by, identification, name, secret, profile_id)
    VALUES (UUID(), utc_timestamp, 'flyway:migration', utc_timestamp, 'flyway:migration', 'admin', 'ADMINISTRATOR user - must not delete', 'gs3', (SELECT id FROM profile where type like 'ADMINISTRATOR'));

INSERT IGNORE INTO user_master (id, user_id)
    VALUES (UUID(), (SELECT id FROM user where profile_id = ((SELECT id FROM profile where type like 'ADMINISTRATOR'))));