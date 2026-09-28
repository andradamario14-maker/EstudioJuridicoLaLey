INSERT INTO Personas (nombre, apellido, dni, direccion, telefono, email)
VALUES ('Marisa', 'Andrade', '12345678', 'Avenida Real 456', '3834395741', 'juan@mail.com');

INSERT INTO Personas (nombre, apellido, dni, direccion, telefono, email)
VALUES ('Lucia', 'Belobog', '87654321', 'Avenida Peron 742', '3834147852', 'maria@mail.com');

INSERT INTO Personas (nombre, apellido, dni, direccion, telefono, email)
VALUES ('Carlos', 'López', '11223344', 'San Martín 100', '3834222222', 'carlos@mail.com');

INSERT INTO Abogados (idMatricula, idPersona)
VALUES (1234567890, 3);

INSERT INTO Causas (nExpediente, tipoCausa, estado, fechaInicio, idDemandante, idDemandado, idAbogado)
VALUES ('EXP-2026-001', 'Civil', TRUE, '2026-09-28', 1, 2, 1234567890);

INSERT INTO Documentos (idCausa, tipoDocumento, rutaArchivo, fechaPresentacion)
VALUES (1, 'Demanda', '/files/EXP-2026-001.pdf', '2026-09-28');