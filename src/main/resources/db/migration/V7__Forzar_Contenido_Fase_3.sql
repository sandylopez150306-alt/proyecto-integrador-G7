-- V7: Forzar contenido correcto en Fase 3 y limpieza
-- Esta migración asegura que la Fase 3 tenga los datos de Posicionamiento correctos
UPDATE fases 
SET nom_fase = '3ra FASE: Determinación del posicionamiento', 
    desc_fase = 'Como consultor de marketing, debes proponer la estrategia de posicionamiento para la Tablet "Kallpa" en tres ejes fundamentales.'
WHERE numero_fase = 3;

-- Asegurar que las alternativas tengan los grupos cortos (Axis_...)
UPDATE alternativas SET grupo = 'Axis_PC' WHERE grupo = 'Eje_Precio_Calidad';
UPDATE alternativas SET grupo = 'Axis_ID' WHERE grupo = 'Eje_Innovacion_Diseno';
UPDATE alternativas SET grupo = 'Axis_PE' WHERE grupo = 'Eje_Prestigio_Experiencia';
