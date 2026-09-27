CREATE TABLE IF NOT EXISTS `Personas` (
	`idPersona` int AUTO_INCREMENT NOT NULL UNIQUE,
	`nombre` varchar(100) NOT NULL,
	`apellido` varchar(100) NOT NULL,
	`dni` varchar(20) NOT NULL UNIQUE,
	`direccion` varchar(150) NOT NULL,
	`telefono` varchar(50) NOT NULL,
	`email` varchar(100) NOT NULL,
	PRIMARY KEY (`idPersona`)
);
CREATE TABLE IF NOT EXISTS `Causas` (
	`idCausa` int AUTO_INCREMENT NOT NULL UNIQUE,
	`nExpediente` varchar(50) NOT NULL UNIQUE,
	`tipoCausa` varchar(50) NOT NULL,
	`estado` boolean NOT NULL,
	`fechaInicio` date NOT NULL,
	`idDemandante` int NOT NULL,
	`idDemandado` int NOT NULL,
	`idAbogado` int NOT NULL,
	PRIMARY KEY (`idCausa`)
);
CREATE TABLE IF NOT EXISTS `Documentos` (
	`idDocumento` int AUTO_INCREMENT NOT NULL UNIQUE,
	`idCausa` int NOT NULL,
	`tipoDocumento` varchar(50) NOT NULL,
	`rutaArchivo` varchar(200) NOT NULL,
	`fechaPresentacion` date NOT NULL,
	PRIMARY KEY (`idDocumento`)
);
CREATE TABLE IF NOT EXISTS `Abogados` (
	`idMatricula` int AUTO_INCREMENT NOT NULL UNIQUE,
	`idPersona` int NOT NULL,
	PRIMARY KEY (`idMatricula`)
);
ALTER TABLE `Causas` ADD CONSTRAINT `Causas_fk5` FOREIGN KEY (`idDemandante`) REFERENCES `Personas`(`idPersona`);
ALTER TABLE `Causas` ADD CONSTRAINT `Causas_fk6` FOREIGN KEY (`idDemandado`) REFERENCES `Personas`(`idPersona`);
ALTER TABLE `Causas` ADD CONSTRAINT `Causas_fk7` FOREIGN KEY (`idAbogado`) REFERENCES `Abogados`(`idMatricula`);
ALTER TABLE `Documentos` ADD CONSTRAINT `Documentos_fk1` FOREIGN KEY (`idCausa`) REFERENCES `Causas`(`idCausa`);
ALTER TABLE `Abogados` ADD CONSTRAINT `Abogados_fk1` FOREIGN KEY (`idPersona`) REFERENCES `Personas`(`idPersona`);