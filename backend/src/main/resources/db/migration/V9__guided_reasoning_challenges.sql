-- Preserve historical activity IDs, attempts and evidence. Only the new catalog is guided content.
ALTER TABLE learning_activities ADD COLUMN archived boolean NOT NULL DEFAULT false;
UPDATE learning_activities SET archived=true WHERE id IN ('SEQUENCES','VARIABLES','CONDITIONALS','LOOPS');
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('SEQ-01','El primer sendero','SEQUENCES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('SEQ-02','Entrega en el campamento','SEQUENCES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('SEQ-03','Cruza el puente','SEQUENCES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('SEQ-04','La ruta eficiente','SEQUENCES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('VAR-01','Guarda el código','VARIABLES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('VAR-02','Contador de objetos','VARIABLES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('VAR-03','Energía del explorador','VARIABLES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('VAR-04','Inventario de llaves','VARIABLES',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('COND-01','Dos caminos','CONDITIONALS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('COND-02','Camino bloqueado','CONDITIONALS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('COND-03','La puerta y la llave','CONDITIONALS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('COND-04','Energía suficiente','CONDITIONALS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('COND-05','El laberinto de decisiones','CONDITIONALS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('LOOP-01','Pasos repetidos','LOOPS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('LOOP-02','El corredor largo','LOOPS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('LOOP-03','Patrulla el sendero','LOOPS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('LOOP-04','Hasta encontrar la salida','LOOPS',false,false);
INSERT INTO learning_activities(id,title,concept_id,reinforcement,archived) VALUES ('LOOP-05','El desafío del explorador','LOOPS',false,false);
-- Existing students retain mastery and history; new challenges start uncompleted.
INSERT INTO student_activity_progress(id,student_id,activity_id,unlocked_at) SELECT gen_random_uuid(),s.id,a.id,CASE WHEN a.id='SEQ-01' OR (a.id IN ('VAR-01','COND-01','LOOP-01') AND EXISTS (SELECT 1 FROM student_activity_progress p JOIN learning_activities old ON old.id=p.activity_id WHERE p.student_id=s.id AND old.concept_id=a.concept_id AND p.unlocked_at IS NOT NULL)) THEN CURRENT_TIMESTAMP ELSE NULL END FROM students s CROSS JOIN learning_activities a WHERE NOT a.archived AND NOT a.reinforcement;

-- Generalize the existing pattern explanation to the configured variable values. IDs/detectors are unchanged.
UPDATE error_patterns SET pedagogical_meaning='El cambio observado no cumple los valores inicial y final requeridos por el reto.' WHERE id='INCORRECT_UPDATE';
