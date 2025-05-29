-- Tabla de tecnologías
CREATE TABLE IF NOT EXISTS technologies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de capacidades
CREATE TABLE IF NOT EXISTS capabilities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de relación muchos a muchos entre capacidades y tecnologías
CREATE TABLE IF NOT EXISTS capability_technologies (
    capability_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (capability_id, technology_id),

    CONSTRAINT fk_capability
        FOREIGN KEY (capability_id)
        REFERENCES capabilities(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_technology
        FOREIGN KEY (technology_id)
        REFERENCES technologies(id)
        ON DELETE CASCADE
);
