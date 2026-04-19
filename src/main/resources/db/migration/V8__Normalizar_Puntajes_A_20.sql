-- V8: Refactor de Puntajes a formato Decimal para soportar fracciones (0.5, 2.5) y escala total de 20 puntos

-- 1. Modificar tipos de dato en la base de datos para soportar decimales reales
ALTER TABLE alternativas MODIFY COLUMN puntaje DOUBLE NOT NULL;
ALTER TABLE sesiones_simulador MODIFY COLUMN puntaje_total DOUBLE DEFAULT 0;

-- 2. Limpiar textos antiguos si los hubiera
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+3): ', '');
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+2): ', '');
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+1): ', '');
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+0.5): ', '');

-- ==========================================
-- FASE 1: (Suma Máxima = 6 puntos)
-- ==========================================
-- Grupo A
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 1 AND desc_alternativa LIKE '%Auditoría de Contenido%';
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 1 AND desc_alternativa LIKE '%Test de Producto%';
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 1 AND desc_alternativa LIKE '%Prueba de Rendimiento%';

-- Grupo B
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 1 AND desc_alternativa LIKE '%Encuesta de Referidos%';
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 1 AND desc_alternativa LIKE '%Etnografía Digital%';
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 1 AND desc_alternativa LIKE '%Shadowing%';

-- Grupo C
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 1 AND desc_alternativa LIKE '%Encuesta Digital Estándar%';
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 1 AND desc_alternativa LIKE '%Entrevistas a Profundidad%';
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 1 AND desc_alternativa LIKE '%Focus Group%';

-- ==========================================
-- FASE 2: (Suma Máxima = 5 puntos)
-- ==========================================
-- Hijo
UPDATE alternativas SET puntaje = 2.5 WHERE fase_id = 2 AND grupo = 'Hijo' AND desc_alternativa LIKE '%19 años%';
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 2 AND grupo = 'Hijo' AND desc_alternativa LIKE '%20 años%';
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 2 AND grupo = 'Hijo' AND desc_alternativa LIKE '%18 años%' AND desc_alternativa LIKE '%barata para estudiar%';
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 2 AND grupo = 'Hijo' AND desc_alternativa LIKE '%18 años%' AND desc_alternativa LIKE '%buena cámara%';

-- Padre
UPDATE alternativas SET puntaje = 2.5 WHERE fase_id = 2 AND grupo = 'Padre' AND desc_alternativa LIKE '%45-55 años%';
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 2 AND grupo = 'Padre' AND desc_alternativa LIKE '%50 años%';
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 2 AND grupo = 'Padre' AND desc_alternativa LIKE '%40 años%' AND desc_alternativa LIKE '%barata y suficiente%';
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 2 AND grupo = 'Padre' AND desc_alternativa LIKE '%40-50 años%';

-- ==========================================
-- FASE 3: Mantener los que definió el usario (Suma Máxima = 9 puntos)
-- ==========================================
-- Estas descripciones vienen del V6 con (+3), etc., pero ya limpiadas arriba.
-- De todos modos aseguramos actualizarlas por coincidencia exacta usando puntajes antiguos aproximados en int.
UPDATE alternativas SET puntaje = 3   WHERE fase_id = 3 AND (desc_alternativa LIKE '%Opción 1%');
UPDATE alternativas SET puntaje = 2   WHERE fase_id = 3 AND (desc_alternativa LIKE '%Opción 2%');
UPDATE alternativas SET puntaje = 1   WHERE fase_id = 3 AND (desc_alternativa LIKE '%Opción 3%');
UPDATE alternativas SET puntaje = 0.5 WHERE fase_id = 3 AND (desc_alternativa LIKE '%Opción 4%');

-- Limpiar referencias residuales de "(+X)" en Otras Fases en caso de que existan
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+2.5) ', '') WHERE desc_alternativa LIKE '(+2.5)%';
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+2) ', '') WHERE desc_alternativa LIKE '(+2)%';
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+1) ', '') WHERE desc_alternativa LIKE '(+1)%';
UPDATE alternativas SET desc_alternativa = REPLACE(desc_alternativa, '(+0.5) ', '') WHERE desc_alternativa LIKE '(+0.5)%';

-- Reprocesar todos los puntajes totales retroactivamente
UPDATE sesiones_simulador s
SET puntaje_total = (
    SELECT COALESCE(SUM(a.puntaje), 0)
    FROM puntajes p
    JOIN alternativas a ON p.alternativa_id = a.id_alternativa
    WHERE p.sesion_id = s.id_sesion
);
