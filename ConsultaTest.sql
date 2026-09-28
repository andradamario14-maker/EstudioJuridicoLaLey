SELECT c.nExpediente,
	p1.nombre AS Demandante,
	p2.nombre AS Demandado,
	p3.nombre AS Abogado,
	a.idMatricula,
	d.tipoDocumento,
	d.rutaArchivo,
	d.fechaPresentacion
FROM Causas c
JOIN Personas p1 ON c.idDemandante = p1.idPersona
JOIN Personas p2 ON c.idDemandado = p2.idPersona
JOIN Abogados a ON c.idAbogado = a.idMatricula
JOIN Personas p3 ON a.idPersona = p3.idPersona
JOIN Documentos d ON c.idCausa = d.idCausa;