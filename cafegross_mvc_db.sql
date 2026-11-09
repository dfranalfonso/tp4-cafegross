-- Base de datos y persistencia para Café Gross (AP4)
CREATE DATABASE IF NOT EXISTS cafegross_db;
USE cafegross_db;

-- Tabla paramétrica de orígenes
CREATE TABLE IF NOT EXISTS origen_cafe (
    id_origen INT AUTO_INCREMENT PRIMARY KEY,
    pais VARCHAR(50) NOT NULL,
    variedad VARCHAR(50) NOT NULL,
    perfil_taza VARCHAR(100) NOT NULL
);

-- Tabla de tolvas de acopio
CREATE TABLE IF NOT EXISTS tolva (
    id_tolva INT AUTO_INCREMENT PRIMARY KEY,
    numero_tolva INT NOT NULL UNIQUE,
    id_origen INT NOT NULL,
    pesomax DECIMAL(8,2) NOT NULL,
    kilos_actuales DECIMAL(8,2) DEFAULT 0.00,
    tipo_tolva ENUM('ESTANDAR', 'HERMETICA') NOT NULL,
    presion_atmosferica DECIMAL(5,2) DEFAULT 1.00,
    estado_operativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_tolva_origen FOREIGN KEY (id_origen) REFERENCES origen_cafe(id_origen)
);

-- Tabla de persistencia de transacciones de pesaje
CREATE TABLE IF NOT EXISTS registro_carga (
    id_carga INT AUTO_INCREMENT PRIMARY KEY,
    id_tolva INT NOT NULL,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    kilos_ingresados DECIMAL(6,2) NOT NULL,
    operador VARCHAR(60) NOT NULL,
    CONSTRAINT fk_carga_tolva FOREIGN KEY (id_tolva) REFERENCES tolva(id_tolva),
    CONSTRAINT chk_rango_carga CHECK (kilos_ingresados >= 10.00 AND kilos_ingresados <= 70.00)
);

-- Datos iniciales de auditoría
INSERT INTO origen_cafe (pais, variedad, perfil_taza) VALUES
('Colombia', 'Castillo / Caturra', 'Acidez cítrica brillante y notas de caramelo'),
('Etiopía', 'Heirloom Yirgacheffe', 'Notas florales a jazmín y frutos silvestres'),
('Bolivia', 'Típica de Altura (Caranavi)', 'Cuerpo denso, notas a chocolate amargo y nuez');

INSERT INTO tolva (numero_tolva, id_origen, pesomax, kilos_actuales, tipo_tolva, presion_atmosferica, estado_operativo) VALUES
(101, 1, 1500.00, 450.00, 'ESTANDAR', 1.00, TRUE),
(102, 2, 800.00, 210.00, 'HERMETICA', 1.05, TRUE),
(103, 3, 1200.00, 0.00, 'ESTANDAR', 1.00, TRUE);
