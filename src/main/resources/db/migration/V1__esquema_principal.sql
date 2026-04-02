CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    nombres VARCHAR(100) NOT NULL,
    apepa VARCHAR(100) NOT NULL,
    apema VARCHAR(100) NOT NULL,
    rol ENUM('ADMINISTRADOR', 'ANALISTA') NOT NULL DEFAULT 'ANALISTA',
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fases (
    id_fase INT AUTO_INCREMENT PRIMARY KEY,
    numero_fase INT NOT NULL UNIQUE,
    nom_fase VARCHAR(100) NOT NULL,
    desc_fase TEXT
);

CREATE TABLE alternativas (
    id_alternativa INT AUTO_INCREMENT PRIMARY KEY,
    fase_id INT NOT NULL,
    desc_alternativa TEXT NOT NULL,
    puntaje INT NOT NULL,
    retroalimentacion TEXT,
    orden INT,
    CONSTRAINT fk_alternativa_fase
        FOREIGN KEY (fase_id) REFERENCES fases(id_fase) ON DELETE CASCADE
);

CREATE TABLE sesiones_simulador (
    id_sesion INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    fch_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
    completado BOOLEAN DEFAULT FALSE,
    puntaje_total INT DEFAULT 0,
    CONSTRAINT fk_sesion_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE puntajes (
    id_puntaje INT AUTO_INCREMENT PRIMARY KEY,
    sesion_id INT NOT NULL,
    alternativa_id INT NOT NULL,
    fecha_decision DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_puntaje_sesion
        FOREIGN KEY (sesion_id) REFERENCES sesiones_simulador(id_sesion) ON DELETE CASCADE,
    CONSTRAINT fk_puntaje_alternativa
        FOREIGN KEY (alternativa_id) REFERENCES alternativas(id_alternativa) ON DELETE CASCADE,
    CONSTRAINT uk_sesion_fase_decision UNIQUE (sesion_id, alternativa_id)
);