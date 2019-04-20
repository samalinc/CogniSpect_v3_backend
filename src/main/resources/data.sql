  INSERT INTO role (id, role_name)
 SELECT 'd8c9ed00-41ec-4ebd-aed2-3446ed2c4b6f','ADMIN'
  WHERE NOT EXISTS (SELECT * FROM role WHERE role_name = 'ADMIN');
  INSERT INTO role (id, role_name)
 SELECT '22fdca31-b67a-49f1-b457-c34c669d3b18','STUDENT'
  WHERE NOT EXISTS (SELECT * FROM role WHERE role_name = 'STUDENT');
  INSERT INTO role (id, role_name)
 SELECT '3c037ad1-5dcf-4d88-87d5-7b0f8a3d90b3','TEACHER'
  WHERE NOT EXISTS (SELECT * FROM role WHERE role_name = 'TEACHER');