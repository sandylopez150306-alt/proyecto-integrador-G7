INSERT INTO fases (numero_fase, nom_fase, desc_fase) VALUES
(1, 'Identificación del Problema',
 'Yachay-Pro, una empresa líder en servicios tecnológicos educativos, ha experimentado un incremento alarmante del 35% en las cancelaciones de sus suscripciones durante el último trimestre. Este fenómeno ha generado una pérdida estimada de $2.4 millones en ingresos recurrentes y amenaza directamente la sostenibilidad del modelo de negocio.\n\nComo Analista de Datos asignado a este caso crítico, tu primera tarea es definir el método de investigación más adecuado para comprender las causas raíz de estas cancelaciones. La dirección ejecutiva necesita respuestas fundamentadas en datos antes de tomar decisiones estratégicas.\n\nDebes considerar que la empresa cuenta con una base de datos de más de 50,000 usuarios activos, registros de interacción con la plataforma, encuestas de satisfacción previas y datos del equipo de soporte técnico. El tiempo disponible para la fase de investigación es de 2 semanas.');

INSERT INTO alternativas (fase_id, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 1),
 'Análisis Cuantitativo de Datos Históricos: Realizar un estudio estadístico profundo de los patrones de cancelación utilizando los registros de la base de datos, segmentando por tipo de usuario, antigüedad, frecuencia de uso y plan contratado.',
 100,
 '¡Excelente elección! El análisis cuantitativo de datos históricos es la metodología más robusta para este escenario. Permite identificar patrones objetivos, segmentar comportamientos y generar hipótesis verificables con evidencia estadística sólida. Esta aproximación data-driven es fundamental para decisiones de negocio de alto impacto.',
 1),

((SELECT id_fase FROM fases WHERE numero_fase = 1),
 'Encuestas Cualitativas a Usuarios: Diseñar y enviar un cuestionario detallado a los usuarios que cancelaron, preguntándoles directamente sobre sus razones y experiencia con la plataforma.',
 70,
 'Buena opción. Las encuestas cualitativas aportan contexto y profundidad emocional que los datos cuantitativos no capturan. Sin embargo, dependen de la tasa de respuesta (generalmente baja en usuarios que ya cancelaron) y del sesgo de auto-reporte. Es un complemento valioso, pero no debería ser el método principal.',
 2),

((SELECT id_fase FROM fases WHERE numero_fase = 1),
 'Benchmarking Competitivo: Investigar las estrategias de retención de los principales competidores del mercado para identificar prácticas que Yachay-Pro podría adoptar.',
 40,
 'Esta opción tiene valor estratégico, pero presenta una limitación crítica: el benchmarking externo no aborda las causas internas específicas de las cancelaciones de Yachay-Pro. Antes de mirar hacia afuera, es esencial comprender primero qué está sucediendo dentro de la propia organización con datos propios.',
 3),

((SELECT id_fase FROM fases WHERE numero_fase = 1),
 'Reunión de Brainstorming con el Equipo: Convocar una sesión creativa con líderes de área para generar hipótesis sobre las posibles causas de cancelación basándose en su experiencia.',
 20,
 'Aunque la colaboración interna es importante, basar la investigación inicial únicamente en opiniones y experiencias subjetivas del equipo introduce sesgos significativos. Las decisiones de negocio de esta magnitud requieren evidencia objetiva como punto de partida, no suposiciones.',
 4);
