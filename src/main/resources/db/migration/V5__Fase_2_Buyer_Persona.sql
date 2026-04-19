-- V5: Fase 2 - Identificación del perfil Buyer Persona
-- Limpieza de alternativas previas de la Fase 2 si las hubiera
DELETE FROM puntajes WHERE alternativa_id IN (SELECT id_alternativa FROM alternativas WHERE fase_id = (SELECT id_fase FROM fases WHERE numero_fase = 2));
DELETE FROM alternativas WHERE fase_id = (SELECT id_fase FROM fases WHERE numero_fase = 2);

UPDATE fases 
SET nom_fase = '2da FASE: Identificación del perfil Buyer Persona',
    desc_fase = 'En base a los resultados de la investigación, ahora debes definir los perfiles que mejor representan a tus clientes: el Hijo (impulsor) y el Padre (decisor).'
WHERE numero_fase = 2;

-- Alternativas para Buyer Persona "HIJO"
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven universitario de 19 años (Académico)', 25, 
'Este es un Buyer Persona claro y estratégico. /// Motivación: Busca autorrealización a través del estudio, pero siente presión social por el estatus de las marcas globales. /// Has identificado correctamente que su motor principal es el rendimiento y la utilidad del software educativo Yachay-Hub.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Estudiante de 20 años (Funcional)', 20, 
'Este es un Buyer Persona útil, aunque con matices sociales limitados. /// Motivación: Depende de la validación de pares y de la confianza que le transmita el padre. /// El perfil reconoce la ventaja del precio, pero es vulnerable a dudas sobre la durabilidad.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven de 18 años (Precio-Foco)', 10, 
'Este es un Buyer Persona incompleto. /// Motivación: Únicamente el precio accesible; no logra articular cómo influyen los pares en su decisión. /// Al enfocarse solo en el costo, se pierde la profundidad de las creencias y motivaciones psicológicas.', 3),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven de 18 años (Básico-Clases)', 5, 
'Este es un Buyer Persona poco útil para la estrategia. /// Motivación: Quiere una tablet, pero no sabe cómo ni para qué le será útil específicamente. /// Su aporte es muy limitado para orientar estrategias de posicionamiento de marca diferenciada.', 4);

-- Alternativas para Buyer Persona "PADRE"
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Hombre de 45-55 años (Responsable/Seguridad)', 25, 
'Este es un Buyer Persona completo y de alta calidad. /// Motivación: Su motor central es la SEGURIDAD; quiere garantía física y respaldo técnico. /// Has detectado que teme a la tecnología local por experiencias previas, lo que define una barrera crítica a superar.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 50 años (Preocupado)', 20, 
'Este es un Buyer Persona útil pero requiere más profundidad técnica. /// Motivación: Necesita validación externa (vendedor o experto) para sentirse seguro. /// El perfil es funcional pero omite gran parte de las experiencias previas y creencias culturales del mercado.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 40 años (Básico/Suficiente)', 10, 
'Este es un Buyer Persona incompleto. /// Motivación: Percibe la tablet como "suficiente" por ser barata. /// No explora las barreras emocionales ni cómo superar la desconfianza estructural en marcas peruanas.', 3),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 40-50 años (Pasivo/Emocional)', 5, 
'Este es un Buyer Persona poco detallado. /// Motivación: Quiere que su hijo sea feliz, por lo que cede la decisión casi por completo. /// Este perfil no ayuda a definir argumentos de venta para el decisor final, que suele ser más crítico en este segmento.', 4);
