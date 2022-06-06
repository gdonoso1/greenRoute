DROP TABLE IF EXISTS rutas_pendientes;
DROP TABLE IF EXISTS rutas_hechas;
DROP TABLE IF EXISTS usuarios_roles;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS punto;
DROP TABLE IF EXISTS rol;
DROP TABLE IF EXISTS ruta;
DROP TABLE IF EXISTS usuarios;

INSERT INTO `rol`(`id`, `nombre`) VALUES ('[value-1]','ADMIN')
INSERT INTO `rol`(`id`, `nombre`) VALUES ('[value-1]','USER')