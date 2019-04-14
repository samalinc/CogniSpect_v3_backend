  INSERT INTO roles (id, role_name)
 SELECT 'd8c9ed00-41ec-4ebd-aed2-3446ed2c4b6f','ADMIN'
  WHERE NOT EXISTS (SELECT * FROM roles WHERE role_name = 'ADMIN');
  INSERT INTO roles (id, role_name)
 SELECT '22fdca31-b67a-49f1-b457-c34c669d3b18','STUDENT'
  WHERE NOT EXISTS (SELECT * FROM roles WHERE role_name = 'STUDENT');
  INSERT INTO roles (id, role_name)
 SELECT '3c037ad1-5dcf-4d88-87d5-7b0f8a3d90b3','TEACHER'
  WHERE NOT EXISTS (SELECT * FROM roles WHERE role_name = 'TEACHER');

   INSERT INTO question_types (id, type)
 SELECT 'cfde4438-fe7f-4338-9607-059bf34fc10f','CHOOSE'
  WHERE NOT EXISTS (SELECT * FROM question_types WHERE type = 'CHOOSE');
  INSERT INTO question_types (id, type)
 SELECT '6ca18a13-f5dd-487d-8445-50ddf8083d36','SORT'
  WHERE NOT EXISTS (SELECT * FROM question_types WHERE type = 'SORT');
  INSERT INTO question_types (id, type)
 SELECT '4cddb318-c47f-4561-909a-bdd638a982c8','MATCH'
  WHERE NOT EXISTS (SELECT * FROM question_types WHERE type = 'MATCH');
  INSERT INTO question_types (id, type)
 SELECT '9d703d2e-6b70-4081-99e9-a4aab0aef741','SUBSTITUTION'
  WHERE NOT EXISTS (SELECT * FROM question_types WHERE type = 'SUBSTITUTION');