-- V6: Fase 3 - Determinación del posicionamiento
-- Limpieza de seguridad si existiera una fase 3 previa
DELETE FROM puntajes WHERE alternativa_id IN (SELECT id_alternativa FROM alternativas WHERE fase_id = (SELECT id_fase FROM fases WHERE numero_fase = 3));
DELETE FROM alternativas WHERE fase_id = (SELECT id_fase FROM fases WHERE numero_fase = 3);
DELETE FROM fases WHERE numero_fase = 3;

-- Inserción de la Fase 3
INSERT INTO fases (numero_fase, nom_fase, desc_fase) VALUES
(3, '3ra FASE: Determinación del posicionamiento', 'Como consultor de marketing, debes proponer la estrategia de posicionamiento para la Tablet "Kallpa" en tres ejes fundamentales.');

-- EJE 1: PRECIO VS CALIDAD
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 1 (+3): "Calidad accesible con respaldo local"', 3, 'Análisis: ¡Excelente! Propones un enfoque que rompe el prejuicio de "lo barato es malo" y convierte el precio accesible en un atributo aspiracional. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 2 (+2): "La opción inteligente para estudiar"', 2, 'Análisis: ¡Interesante! Genera confianza moderada y atrae a estudiantes que buscan funcionalidad, aunque aún depende de validación externa. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 3 (+1): "La tablet económica para estudiantes"', 1, 'Análisis: Con esto te aseguras de atraer compradores sensibles al precio, pero refuerza el prejuicio de baja calidad y limita la percepción de valor. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 4 (+0.5): "La alternativa local"', 0.5, 'Análisis: No logra diferenciarse ni superar las creencias negativas; probablemente tengas ventas estancadas y percepción débil sobre la calidad de la Tablet. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+0.5.JPG', 4);

-- EJE 2: INNOVACIÓN VS DISEÑO
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 1 (+3): "Innovación peruana con diseño global"', 3, 'Análisis: ¡Excelente! Este enfoque rompe el prejuicio de "producto local básico" y convierte la innovación en un símbolo de estatus accesible. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 2 (+2): "Diseño funcional para estudiantes exigentes"', 2, 'Análisis: ¡Interesante! Buscas generar confianza y diferenciación moderada, aunque no alcanza un nivel aspiracional pleno. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 3 (+1): "La tablet práctica y económica"', 1, 'Análisis: Estás buscando atraer compradores pragmáticos, pero refuerza la percepción de producto limitado y poco innovador. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 4 (+0.5): "La alternativa local sin pretensiones"', 0.5, 'Análisis: Esta idea no logra diferenciar ni superar prejuicios; es probable que las ventas se vean estancadas y haya una percepción débil de innovación. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+0.5.JPG', 4);

-- EJE 3: PRESTIGIO VS EXPERIENCIA
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 1 (+3): "La experiencia educativa que trasciende"', 3, 'Análisis: ¡Excelente! Te enfocas en la experiencia de usuario y en el valor real de la educación, elevando a Yachay-Tech más allá de un simple dispositivo electrónico. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 2 (+2): "Prestigio basado en resultados académicos"', 2, 'Análisis: ¡Interesante! Buscas generar confianza a través de logros concretos, lo cual resuena bien con los padres, aunque puede percibirse como un enfoque muy tradicional. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 3 (+1): "Una experiencia práctica para el día a día"', 1, 'Análisis: Atrae a usuarios que buscan una herramienta funcional, pero no logra construir una imagen de marca fuerte o prestigiosa a largo plazo. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 4 (+0.5): "La opción confiable del mercado local"', 0.5, 'Análisis: Este enfoque es muy genérico y no logra destacar frente a competidores globales; la percepción de prestigio sigue siendo baja para el consumidor. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+0.5.JPG', 4);
