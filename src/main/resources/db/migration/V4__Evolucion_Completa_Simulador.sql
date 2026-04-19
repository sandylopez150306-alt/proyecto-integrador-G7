ALTER TABLE usuarios MODIFY COLUMN rol ENUM('ADMINISTRADOR', 'ANALISTA', 'DOCENTE') NOT NULL DEFAULT 'ANALISTA';
ALTER TABLE alternativas ADD COLUMN grupo VARCHAR(10) DEFAULT NULL;
ALTER TABLE alternativas MODIFY COLUMN puntaje DOUBLE NOT NULL;
ALTER TABLE sesiones_simulador MODIFY COLUMN puntaje_total DOUBLE DEFAULT 0;

DELETE FROM puntajes;
DELETE FROM alternativas;
DELETE FROM fases WHERE numero_fase IN (2, 3);

UPDATE fases 
SET nom_fase = '1ra FASE: El escenario de crisis',
    desc_fase = 'Yachay-Tech ha detectado que, aunque hay mucho tráfico en su web, las ventas de su tablet "Kallpa" se detienen en el carrito de compras. En este momento usted debe decidir cuál será la ruta de acción para identificar los stoppers que no permiten concluir la compra del producto.'
WHERE numero_fase = 1;

INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 1), 'A', 'Auditoría de Contenido Multimedia y UX', 0.5, 
'¡Excelente elección! Estás aplicando el concepto de sensaciones al entorno digital. Revisar cómo el cliente percibe visualmente la tablet es fundamental para un negocio que opera con un cliente digitalizado.\n\n[RESULTADOS]\n- Falla sensorial: Las fotos en la web son estáticas y no muestran la fluidez del procesador en multitarea, lo que impide que el cliente genere una sensación de velocidad.\n- Contenido "escondido": El contenido educativo de "Yachay-Hub" está en una pestaña secundaria, lo que dificulta que el estudiante perceiba de inmediato cómo la tablet le ayudará a mejorar sus notas.\n\n[CONCLUSION]\nHas identificado que el entorno digital no está estimulando los sentidos del cliente.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'A', 'Test de Producto en Puntos de Venta (Pop-up Stores)', 1.0, 
'¡Muy bien pensado! Al permitir el contacto físico, estás activando el canal sensorial completo del cliente. Esto es vital para romper la creencia de que la tecnología local es de ''baja calidad'', permitiendo que la percepción se base en la experiencia real y no en prejuicios.\n\n[RESULTADOS]\n- Calidad de Materiales: El 85% de los padres se sorprendió positivamente al tocar el aluminio reforzado, ya que esperaban plástico barato debido al precio bajo.\n- Limitación Técnica: Algunos usuarios reportaron que el brillo de la pantalla en exteriores (plazas principales) es insuficiente, lo que afecta la sensación de utilidad en el campo.\n- Comportamiento Social: Se observó que los padres solo se acercan al módulo si ven a otros padres probando el equipo primero.\n\n[CONCLUSION]\nHas descubierto que el contacto físico rompe la creencia negativa de ''mala calidad''.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'A', 'Prueba de Rendimiento Crítico de Yachay-Hub', 2.0, 
'¡Decisión estratégica superior! Has seleccionado una herramienta que analiza profundamente la motivación del cliente. Al demostrar el valor de ''Yachay-Hub'' sin conexión y la durabilidad de la batería en condiciones reales, estás atacando directamente la necesidad psicológica de seguridad que el padre de familia peruano exige para aprobar la compra.\n\n[RESULTADOS]\n- Resistencia física: Al ver la tablet caer desde 1 metro sin sufrir daños, el nivel de estrés de los padres bajó drásticamente y su intención de compra subió un 60%.\n- Valor educativo: El uso de "Yachay-Hub" sin internet generó una sensación de "alivio" y "empoderamiento" en estudiantes de zonas rurales, quienes lo ven como su única oportunidad de competir con alumnos de Lima.\n- Factor seguridad: Se descubrió que el miedo al "robo" (falta de seguridad física en la calle) pesa más que el miedo a que el equipo se malogre.\n\n[CONCLUSION]\nHas llegado a la raíz, la motivación de seguridad no es solo técnica, es también contextual (el entorno peruano). Ahora sabes que, para vender, debes ofrecer tranquilidad total.', 3);

-- Grupo B
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 1), 'B', 'Encuesta de Referidos y canales de información', 0.5, 
'¡Buen trabajo! Has elegido una herramienta eficiente para mapear las influencias externas cuantitativamente. Identificar si el cliente llega por una red social o por recomendación de un amigo es el primer paso para entender el entorno del consumidor.\n\n[RESULTADOS]\n- Impacto de Redes vs. Confianza: El 65% de los clientes llegó por un video de TikTok, pero el 80% solo compró tras consultar a un "amigo que sabe de tecnología".\n- Barrera del Precio: El 40% de los padres mencionó que "el precio le hacía dudar" hasta que alguien de su confianza le dio el visto bueno.\n- Falta de Validación: Se ha identificado la falta de una figura de autoridad técnica local que valide la marca masivamente.\n\n[CONCLUSION]\nLa información es útil para detectar tendencias. Te da el qué (la recomendación es clave), pero no el cómo.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'B', 'Etnografía Digital en Comunidades Universitarias', 1.0, 
'¡Excelente enfoque! Al observar el comportamiento en su entorno natural, estás analizando los factores sociales y los grupos de referencia de la Generación Z. Este instrumento te permite ver la influencia de los pares en tiempo real, algo crucial para productos tecnológicos.\n\n[RESULTADOS]\n- Percepción de Marca: En los grupos de WhatsApp de estudiantes, se descubrió que Yachay-Tech es vista como "la tablet para estudiar", pero se recomienda una marca global para "lucirse en la facultad".\n- Líderes de Opinión: Existe un líder de opinión (el delegado o el alumno más aplicado) que tiene el poder de validar o destruir la reputación de la tablet con un solo comentario.\n- Factor Estatus: El factor social del estatus y la presión de grupo asocia "lo peruano" con algo funcional pero no aspiracional.\n\n[CONCLUSION]\nAl observar sin intervenir, has captado la ''jerga'' y el sentimiento real de la Generación Z. Posees datos cualitativos potentes sobre el factor social.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'B', 'Shadowing y Entrevista en el "Punto de Decisión" (Tienda Física)', 2.0, 
'¡Decisión estratégica sobresaliente! Has seleccionado el instrumento más profundo para este camino. Al observar la dinámica real entre el decisor y el influyente (padre-hijo), puedes identificar exactamente en qué etapa de la toma de decisiones surge el conflicto.\n\n[RESULTADOS]\n- Dinámica Padre-Hijo: Se observó que el hijo convence al padre en el pasillo usando argumentos de "tareas y software", pero al momento de sacar la tarjeta, el padre se detiene, mira las marcas globales vecinas y le pregunta al vendedor: "¿Cuál me recomienda usted?".\n- El Rol del Vendedor: Si el vendedor duda o no conoce Yachay-Tech, el padre aborta la compra inmediatamente por falta de seguridad.\n- El Quiebre: El vendedor actúa como influyente crítico y la inseguridad del padre aparece en la etapa final del proceso.\n\n[CONCLUSION]\nLa información es de altísima calidad y estratégica. Has presenciado el momento exacto del quiebre (la ''hora de la verdad''). Tienes evidencia directa de cómo interactúan las influencias internas (deseo del hijo) con las externas (vendedor y entorno).', 3);

-- Grupo C
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 1), 'C', 'Encuesta Digital Estándar (Post-Abandono)', 0.5, 
'¡Buena idea! Has elegido una herramienta que te permite obtener datos cuantitativos rápidamente y a bajo costo. Es un paso valioso para identificar tendencias generales en el comportamiento del cliente digitalizado.\n\n[RESULTADOS]\n- Desconfianza técnica: El 55% de los encuestados marcó la opción "Desconfianza en la durabilidad del hardware".\n- Paradoja del precio: El 80% considera que el precio de S/ 850.00 es "muy bueno", pero el 40% teme que lo barato signifique mala calidad.\n- Desconocimiento de valor: El 70% de los usuarios digitales no sabía que la tablet incluye contenido del MINEDU que funciona sin internet.\n\n[CONCLUSION]\nHas obtenido datos fríos. Sabes qué está pasando (miedo a la durabilidad), pero no sabes el porqué.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'C', 'Entrevistas a Profundidad con el "Comprador Dual"', 1.0, 
'¡Excelente enfoque! Al incluir a ambos actores, estás reconociendo las influencias en la toma de decisiones. Estás logrando captar matices emocionales y motivaciones de seguridad que una encuesta ignoraría.\n\n[RESULTADOS]\n- El Hijo (Gen Z): Tiene una actitud positiva hacia el software "Yachay-Hub", pero siente que la tablet no tiene "onda" o estatus frente a sus compañeros.\n- El Padre (Gen X): Es el principal stopper. Su creencia está basada en experiencias pasadas negativas con marcas ensambladas en Perú. Solo compraría si hubiera una garantía física cerca de su casa.\n- Conflicto de intereses: La etapa de "Evaluación de alternativas" se bloquea porque el padre prioriza la motivación de seguridad (durabilidad) mientras el hijo busca autorrealización (estudio).\n\n[CONCLUSION]\nHas identificado un choque generacional de necesidades que detiene la compra.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 1), 'C', 'Focus Group de "Asociación Implícita y Proyectiva"', 2.0, 
'¡Impresionante decisión estratégica! Has seleccionado la herramienta cualitativa más potente para este escenario. Este instrumento te permitirá ''hackear'' las creencias arraigadas del consumidor peruano y entender el componente cognitivo real de su actitud negativa.\n\n[RESULTADOS]\n- Metáfora de Usuario: Los participantes describieron a Yachay-Tech como "un estudiante esforzado pero que usa ropa genérica", mientras que a la competencia global la ven como "el estudiante exitoso que todos admiran".\n- Prueba Ciega: En una prueba "ciega" (sin marcas), el 90% prefirió la fluidez de la pantalla de la tablet Kallpa. Sin embargo, al ponerle el logo de Yachay-Tech, la percepción de calidad bajó automáticamente.\n- Prejuicio Cognitivo: El cliente peruano asocia la tecnología nacional con "lo básico", ignorando que las especificaciones son de alta gama.\n\n[CONCLUSION]\nEl problema no es el producto, es el prejuicio. Has descubierto que la marca sufre de una "actitud de rechazo por origen".', 3);


INSERT INTO fases (numero_fase, nom_fase, desc_fase) VALUES
(2, '2da FASE: Identificación del perfil Buyer Persona', 'En base a los resultados de la investigación, ahora debes definir los perfiles que mejor representan a tus clientes: el Hijo (impulsor) y el Padre (decisor).');

-- Hijo
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven universitario de 19 años (Académico)', 2.5, 
'Este es un Buyer Persona claro y estratégico. /// Motivación: Busca autorrealización a través del estudio, pero siente presión social por el estatus de las marcas globales. /// Has identificado correctamente que su motor principal es el rendimiento y la utilidad del software educativo Yachay-Hub.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Estudiante de 20 años (Funcional)', 2.0, 
'Este es un Buyer Persona útil, aunque con matices sociales limitados. /// Motivación: Depende de la validación de pares y de la confianza que le transmita el padre. /// El perfil reconoce la ventaja del precio, pero es vulnerable a dudas sobre la durabilidad.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven de 18 años (Precio-Foco)', 1.0, 
'Este es un Buyer Persona incompleto. /// Motivación: Únicamente el precio accesible; no logra articular cómo influyen los pares en su decisión. /// Al enfocarse solo en el costo, se pierde la profundidad de las creencias y motivaciones psicológicas.', 3),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Hijo', 'Joven de 18 años (Básico-Clases)', 0.5, 
'Este es un Buyer Persona poco útil para la estrategia. /// Motivación: Quiere una tablet, pero no sabe cómo ni para qué le será útil específicamente. /// Su aporte es muy limitado para orientar estrategias de posicionamiento de marca diferenciada.', 4);

-- Padre
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Hombre de 45-55 años (Responsable/Seguridad)', 2.5, 
'Este es un Buyer Persona completo y de alta calidad. /// Motivación: Su motor central es la SEGURIDAD; quiere garantía física y respaldo técnico. /// Has detectado que teme a la tecnología local por experiencias previas, lo que define una barrera crítica a superar.', 1),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 50 años (Preocupado)', 2.0, 
'Este es un Buyer Persona útil pero requiere más profundidad técnica. /// Motivación: Necesita validación externa (vendedor o experto) para sentirse seguro. /// El perfil es funcional pero omite gran parte de las experiencias previas y creencias culturales del mercado.', 2),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 40 años (Básico/Suficiente)', 1.0, 
'Este es un Buyer Persona incompleto. /// Motivación: Percibe la tablet como "suficiente" por ser barata. /// No explora las barreras emocionales ni cómo superar la desconfianza estructural en marcas peruanas.', 3),

((SELECT id_fase FROM fases WHERE numero_fase = 2), 'Padre', 'Padre de 40-50 años (Pasivo/Emocional)', 0.5, 
'Este es un Buyer Persona poco detallado. /// Motivación: Quiere que su hijo sea feliz, por lo que cede la decisión casi por completo. /// Este perfil no ayuda a definir argumentos de venta para el decisor final, que suele ser más crítico en este segmento.', 4);

INSERT INTO fases (numero_fase, nom_fase, desc_fase) VALUES
(3, '3ra FASE: Determinación del posicionamiento', 'Como consultor de marketing, debes proponer la estrategia de posicionamiento para la Tablet "Kallpa" en tres ejes fundamentales.');

-- Eje 1
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 1: "Calidad accesible con respaldo local"', 3.0, 'Análisis: ¡Excelente! Propones un enfoque que rompe el prejuicio de "lo barato es malo" y convierte el precio accesible en un atributo aspiracional. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 2: "La opción inteligente para estudiar"', 2.0, 'Análisis: ¡Interesante! Genera confianza moderada y atrae a estudiantes que buscan funcionalidad, aunque aún depende de validación externa. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 3: "La tablet económica para estudiantes"', 1.0, 'Análisis: Con esto te aseguras de atraer compradores sensibles al precio, pero refuerza el prejuicio de baja calidad y limita la percepción de valor. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PC', 'Opción 4: "La alternativa local"', 0.5, 'Análisis: No logra diferenciarse ni superar las creencias negativas; probablemente tengas ventas estancadas y percepción débil sobre la calidad de la Tablet. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrecioVSCalidad+0.5.JPG', 4);

-- Eje 2
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 1: "Innovación peruana con diseño global"', 3.0, 'Análisis: ¡Excelente! Este enfoque rompe el prejuicio de "producto local básico" y convierte la innovación en un símbolo de estatus accesible. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 2: "Diseño funcional para estudiantes exigentes"', 2.0, 'Análisis: ¡Interesante! Buscas generar confianza y diferenciación moderada, aunque no alcanza un nivel aspiracional pleno. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 3: "La tablet práctica y económica"', 1.0, 'Análisis: Estás buscando atraer compradores pragmáticos, pero refuerza la percepción de producto limitado y poco innovador. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_ID', 'Opción 4: "La alternativa local sin pretensiones"', 0.5, 'Análisis: Esta idea no logra diferenciar ni superar prejuicios; es probable que las ventas se vean estancadas y haya una percepción débil de innovación. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjeInnovacionVSDiseno+0.5.JPG', 4);

-- Eje 3
INSERT INTO alternativas (fase_id, grupo, desc_alternativa, puntaje, retroalimentacion, orden) VALUES
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 1: "La experiencia educativa que trasciende"', 3.0, 'Análisis: ¡Excelente! Te enfocas en la experiencia de usuario y en el valor real de la educación, elevando a Yachay-Tech más allá de un simple dispositivo electrónico. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+3.JPG', 1),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 2: "Prestigio basado en resultados académicos"', 2.0, 'Análisis: ¡Interesante! Buscas generar confianza a través de logros concretos, lo cual resuena bien con los padres, aunque puede percibirse como un enfoque muy tradicional. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+2.JPG', 2),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 3: "Una experiencia práctica para el día a día"', 1.0, 'Análisis: Atrae a usuarios que buscan una herramienta funcional, pero no logra construir una imagen de marca fuerte o prestigiosa a largo plazo. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+1.JPG', 3),
((SELECT id_fase FROM fases WHERE numero_fase = 3), 'Axis_PE', 'Opción 4: "La opción confiable del mercado local"', 0.5, 'Análisis: Este enfoque es muy genérico y no logra destacar frente a competidores globales; la percepción de prestigio sigue siendo baja para el consumidor. /// Acción: Matriz 2x2 con el comparativo con las marcas competidoras. /// IMAGEN:EjePrestigioVSExperiencia+0.5.JPG', 4);
