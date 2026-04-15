ALTER TABLE alternativas ADD COLUMN fase_siguiente_id INT;

ALTER TABLE alternativas
ADD CONSTRAINT fk_alternativas_fase_siguiente
FOREIGN KEY (fase_siguiente_id) REFERENCES fases(id_fase);

INSERT IGNORE INTO fases (numero_fase, nom_fase, desc_fase) VALUES
(2, 'Análisis de Resultados y Segmentación',
 '¡Excelente inicio! Ahora tu objetivo es identificar qué segmento de usuarios presenta la mayor tasa de cancelaciones.');

UPDATE alternativas
SET fase_siguiente_id = (SELECT id_fase FROM fases WHERE numero_fase = 2)
WHERE fase_id = (SELECT id_fase FROM fases WHERE numero_fase = 1);