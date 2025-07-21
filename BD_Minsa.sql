DROP DATABASE Sistema_Citas;
CREATE DATABASE Sistema_Citas;
USE Sistema_Citas;

CREATE TABLE Paciente (
    idPaciente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    dni VARCHAR(8),
    fechaNac DATE,
    numeroHijos int,
    direccion VARCHAR(200),
    telefono VARCHAR(9),
    estado VARCHAR(20)
);

CREATE TABLE Obstetra (
    idObstetra INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    dni VARCHAR(8),
    fechaNac DATE,
    direccion VARCHAR(200),
    telefono VARCHAR(9),
    numeroColegiatura varchar(255),
    procedenciaColegio varchar(255),
    fechaColegiatura date,
    estado VARCHAR(20)
);

CREATE TABLE Cuenta (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    idObstetra INT,
    contrasena VARBINARY(128),
    tipoUsuario VARCHAR(20),
    estado VARCHAR(20),
    FOREIGN KEY (idObstetra) REFERENCES Obstetra(idObstetra)
);

CREATE TABLE Programa (
    idPrograma INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion VARCHAR(200),
    año INT,
    metaAnual INT,
    estado VARCHAR(20)
);

CREATE TABLE Atencion (
    idCita INT AUTO_INCREMENT PRIMARY KEY,
    idPaciente INT,
    idObstetra INT,
    fechaRegistro DATETIME,
    fechaCita DATETIME,
    descripcion VARCHAR(200),
    estado VARCHAR(20),
    idPrograma INT,
    FOREIGN KEY (idPaciente) REFERENCES Paciente(idPaciente),
    FOREIGN KEY (idObstetra) REFERENCES Obstetra(idObstetra),
    FOREIGN KEY (idPrograma) REFERENCES Programa(idPrograma)
);


insert Obstetra values (null, 'Alvaro Mitchel','Mundaca Mori','75063133','1999-10-02','Chiclayo','969411184',null,null,null,'ACTIVO');

insert Cuenta values (null,'1',sha2('qaz987-M',256),'SUPER_ADMIN','ACTIVO');

INSERT INTO Obstetra VALUES
(null, 'María Isabel', 'Sánchez Ríos', '71582346', '1980-05-15', 'Av. Las Flores 123', '912345678', 'COP10231', 'Lima', '2004-06-20', 'ACTIVO'),
(null, 'Lucía Fernanda', 'Guzmán León', '72194578', '1979-11-30', 'Jr. Los Andes 456', '913456789', 'COP10232', 'Arequipa', '2003-12-15', 'ACTIVO'),
(null, 'Ana Teresa', 'Campos Vargas', '70831952', '1985-02-20', 'Calle Libertad 789', '914567890', 'COP10233', 'Cusco', '2008-07-10', 'ACTIVO'),
(null, 'Rosa Elena', 'Palacios Cárdenas', '73521890', '1982-08-10', 'Mz B Lt 4, San Juan', '915678901', 'COP10234', 'Trujillo', '2006-01-25', 'ACTIVO'),
(null, 'Claudia Patricia', 'Romero Díaz', '74652173', '1988-03-25', 'Urb. Santa Anita 321', '916789012', 'COP10235', 'Lima', '2011-05-12', 'ACTIVO'),
(null, 'Fiorella Katherine', 'Paredes Huamán', '71823764', '1983-01-12', 'Av. Brasil 456', '917890123', 'COP10236', 'Chiclayo', '2007-09-18', 'ACTIVO'),
(null, 'Cecilia Verónica', 'Loayza Gamarra', '75938201', '1977-09-18', 'Jr. Cuzco 123', '918901234', 'COP10237', 'Huancayo', '2000-04-30', 'ACTIVO'),
(null, 'Ruth Alejandra', 'Ticona Ponce', '70327195', '1981-06-27', 'Urb. El Sol 230', '919012345', 'COP10238', 'Tacna', '2005-08-14', 'ACTIVO'),
(null, 'Verónica Lisseth', 'Castañeda Ramos', '74829501', '1986-12-05', 'Calle Lima 708', '920123456', 'COP10239', 'Iquitos', '2010-03-09', 'ACTIVO'),
(null, 'Paola Jimena', 'Mejía Torres', '75231684', '1984-04-21', 'Jr. Ayacucho 405', '921234567', 'COP10240', 'Piura', '2009-02-17', 'ACTIVO'),
(null, 'Natalia Beatriz', 'Quispe Ñahui', '73256418', '1980-10-02', 'Psje. La Unión 108', '922345678', 'COP10241', 'Lima', '2003-11-22', 'ACTIVO'),
(null, 'Gabriela Teresa', 'Vargas Poma', '76421853', '1978-03-14', 'Av. Grau 900', '923456789', 'COP10242', 'Arequipa', '2002-06-30', 'ACTIVO'),
(null, 'Liliana Roxana', 'Huamán Córdova', '74568931', '1989-08-19', 'Jr. Los Jardines 521', '924567890', 'COP10243', 'Cusco', '2013-04-04', 'ACTIVO'),
(null, 'Sandra Milagros', 'Ríos Alvarado', '73918476', '1983-07-11', 'Mz D Lt 9, Breña', '925678901', 'COP10244', 'Lima', '2007-12-01', 'ACTIVO'),
(null, 'Tatiana Lucía', 'Delgado Cárdenas', '72749162', '1987-02-28', 'Urb. Primavera 301', '926789012', 'COP10245', 'Trujillo', '2012-09-05', 'ACTIVO');


INSERT INTO Cuenta (idObstetra, contrasena, tipoUsuario, estado)
VALUES 
(2, SHA2('1234', 256), 'USER', 'ACTIVO'),
(3, SHA2('1234', 256), 'ADMIN', 'ACTIVO'),
(4, SHA2('1234', 256), 'USER', 'ACTIVO'),
(5, SHA2('1234', 256), 'USER', 'ACTIVO'),
(6, SHA2('1234', 256), 'USER', 'ACTIVO'),
(7, SHA2('1234', 256), 'ADMIN', 'ACTIVO'),
(8, SHA2('1234', 256), 'USER', 'ACTIVO'),
(9, SHA2('1234', 256), 'USER', 'ACTIVO'),
(10, SHA2('1234', 256), 'ADMIN', 'ACTIVO'),
(11, SHA2('1234', 256), 'USER', 'ACTIVO'),
(12, SHA2('1234', 256), 'USER', 'ACTIVO'),
(13, SHA2('1234', 256), 'USER', 'ACTIVO'),
(14, SHA2('1234', 256), 'USER', 'ACTIVO'),
(15, SHA2('1234', 256), 'USER', 'ACTIVO'),
(16, SHA2('1234', 256), 'ADMIN', 'ACTIVO');

INSERT INTO Paciente VALUES 
(null, 'Luisa Catalina', 'Milla Cornejo', '75063150', '1991-01-18', 2, 'Urb. Las Palmeras 99', '938216784', 'ACTIVO'),
(null, 'María Fernanda', 'Pérez Quispe', '75063101', '1990-03-15', 3, 'Av. Lima 123', '987654321', 'ACTIVO'),
(null, 'Lucía Alejandra', 'García Ramírez', '75063102', '1985-06-20', 1, 'Calle 9 Sur', '945123987', 'ACTIVO'),
(null, 'Carmen Rosa', 'Ramírez Torres', '75063103', '1992-11-01', 4, 'Jr. Tacna 432', '954786321', 'ACTIVO'),
(null, 'Rosa Elvira', 'Lopez Mendoza', '75063104', '1997-04-30', 0, 'Mz A Lt 5, San Borja', '999456123', 'ACTIVO'),
(null, 'Ana Cecilia', 'Fernández Vargas', '75063105', '1980-01-10', 6, 'Urb. La Merced 101', '912345678', 'ACTIVO'),
(null, 'Diana Carolina', 'Gómez Castañeda', '75063106', '1995-08-19', 2, 'Calle F 321, Surco', '956784321', 'ACTIVO'),
(null, 'Patricia Andrea', 'Salazar León', '75063107', '1993-12-25', 3, 'Av. Universitaria 1230', '981234567', 'ACTIVO'),
(null, 'Brenda Sofía', 'Castañeda Rojas', '75063108', '1989-07-11', 5, 'Jr. Ayacucho 200', '945789123', 'ACTIVO'),
(null, 'Sandra Pilar', 'Vargas Huamán', '75063109', '1994-05-08', 1, 'Psje. Lima Norte', '998456321', 'ACTIVO'),
(null, 'Rosa Amelia', 'Quispe Ñahui', '75063110', '1991-02-14', 3, 'Av. La Marina 400', '965432187', 'ACTIVO'),
(null, 'Natalia Ivonne', 'Sánchez Paredes', '75063111', '1988-09-03', 0, 'Calle Bolívar 321', '934567891', 'ACTIVO'),
(null, 'Elena Isabel', 'Huamán Díaz', '75063112', '1996-01-22', 1, 'Jr. Los Olivos 876', '955678902', 'ACTIVO'),
(null, 'Paola Teresa', 'Mendoza Chacón', '75063113', '1990-06-05', 4, 'Av. Industrial 123', '964532187', 'ACTIVO'),
(null, 'Vanessa Milagros', 'León Ruiz', '75063114', '1993-03-13', 2, 'Jr. La Unión 456', '913456789', 'ACTIVO'),
(null, 'Tatiana Rocío', 'Ruiz Cárdenas', '75063115', '1991-07-19', 0, 'Mz D Lt 6, Breña', '998764321', 'ACTIVO'),
(null, 'Miriam Alicia', 'Chávez Torres', '75063116', '1986-10-30', 7, 'Urb. San Miguel 101', '978654321', 'ACTIVO'),
(null, 'Claudia Beatriz', 'Córdova Sánchez', '75063117', '1989-12-11', 3, 'Calle Los Laureles 102', '924567893', 'ACTIVO'),
(null, 'Flor Gabriela', 'Rojas Mendoza', '75063118', '1995-02-02', 1, 'Av. Primavera 230', '916789012', 'ACTIVO'),
(null, 'Yesenia Maribel', 'Alvarado Quispe', '75063119', '1994-11-09', 5, 'Psje. Las Flores 901', '953217890', 'ACTIVO'),
(null, 'Adriana Lucía', 'Silva Castañeda', '75063120', '1992-08-18', 2, 'Jr. San José 508', '961234578', 'ACTIVO'),
(null, 'Milagros Verónica', 'Pacheco Huamán', '75063121', '1990-10-05', 4, 'Av. Brasil 456', '987456321', 'ACTIVO'),
(null, 'Verónica Cecilia', 'Matos Yupanqui', '75063122', '1987-03-27', 2, 'Calle Piura 789', '945321678', 'ACTIVO'),
(null, 'Alejandra Fiorella', 'Delgado Carranza', '75063123', '1993-01-08', 0, 'Urb. San Luis 109', '912765432', 'ACTIVO'),
(null, 'Gabriela Andrea', 'Loarte Villanueva', '75063124', '1995-09-14', 3, 'Jr. Cuzco 902', '974563218', 'ACTIVO'),
(null, 'Juana Roxana', 'Coaqui Quispe', '75063125', '1991-12-19', 6, 'Av. Grau 611', '956741230', 'ACTIVO'),
(null, 'Natalie Romina', 'Ticona Ramos', '75063126', '1989-05-10', 2, 'Mz J Lt 3, Los Olivos', '934521768', 'ACTIVO'),
(null, 'Lizbeth Mariana', 'Salinas Chávez', '75063127', '1994-06-22', 4, 'Calle Los Pinos 234', '998231764', 'ACTIVO'),
(null, 'Camila Antonella', 'Velásquez León', '75063128', '1988-04-02', 1, 'Jr. Callao 503', '964127893', 'ACTIVO'),
(null, 'Daniela Victoria', 'Escobar Ríos', '75063129', '1992-07-17', 0, 'Pasaje Lima Este', '911234578', 'ACTIVO'),
(null, 'Marisol Estefanía', 'Rivera Soto', '75063130', '1996-02-28', 3, 'Urb. Santa Rosa 710', '975612348', 'ACTIVO'),
(null, 'Valeria Mónica', 'Carrillo Espinoza', '75063131', '1990-11-03', 2, 'Av. Perú 334', '984563210', 'ACTIVO'),
(null, 'Karina Susana', 'Yataco Calderón', '75063132', '1995-01-25', 5, 'Jr. Libertad 112', '956789032', 'ACTIVO'),
(null, 'Jazmín Fabiola', 'Guevara Pérez', '75063133', '1993-06-09', 1, 'Psje. Las Rosas 99', '998765432', 'ACTIVO'),
(null, 'Carla Jimena', 'Tapia Guzmán', '75063134', '1987-08-30', 6, 'Calle Las Violetas 403', '918274635', 'ACTIVO'),
(null, 'Liliana Teresa', 'Palomino Chávez', '75063135', '1991-09-12', 2, 'Av. Angamos 890', '963214785', 'ACTIVO'),
(null, 'Brigitte Amanda', 'Suárez Ramos', '75063136', '1986-12-02', 3, 'Urb. Central 506', '986214375', 'ACTIVO'),
(null, 'Silvia Daniela', 'Rosales León', '75063137', '1994-04-15', 0, 'Jr. Huánuco 230', '912387456', 'ACTIVO'),
(null, 'Esther Luciana', 'Aguilar Ríos', '75063138', '1990-05-29', 1, 'Calle El Sol 71', '932187456', 'ACTIVO'),
(null, 'Magaly Romina', 'Zúñiga Chávez', '75063139', '1989-10-06', 4, 'Psje. Zárate 201', '956789421', 'ACTIVO'),
(null, 'Florencia Edith', 'Cáceres Soto', '75063140', '1988-07-18', 7, 'Av. Independencia 209', '918264573', 'ACTIVO'),
(null, 'Tatiana Patricia', 'Mejía Robles', '75063141', '1992-02-04', 3, 'Jr. Ancash 332', '964123890', 'ACTIVO'),
(null, 'Noemí Beatriz', 'Portilla Carranza', '75063142', '1995-12-11', 1, 'Av. Canadá 305', '987321546', 'ACTIVO'),
(null, 'Yolanda Milagros', 'Ochoa Quintana', '75063143', '1991-03-21', 0, 'Mz H Lt 9, San Juan', '954326781', 'ACTIVO'),
(null, 'Angie Maricarmen', 'Bellido Barrera', '75063144', '1989-06-07', 2, 'Psje. Santo Domingo 89', '924356718', 'ACTIVO'),
(null, 'Samantha Ivette', 'Barreto Romero', '75063145', '1986-11-14', 5, 'Urb. El Bosque 444', '975421369', 'ACTIVO'),
(null, 'Beatriz Juliana', 'Espinoza Reátegui', '75063146', '1990-08-25', 1, 'Av. Arequipa 1005', '948265190', 'ACTIVO'),
(null, 'Julissa Andrea', 'Torreblanca León', '75063147', '1987-09-19', 4, 'Jr. Amazonas 554', '912489763', 'ACTIVO'),
(null, 'Inés Alejandrina', 'Caballero Huamán', '75063148', '1993-10-23', 2, 'Calle San Martín 804', '958273641', 'ACTIVO'),
(null, 'Nicole Adriana', 'Valverde Ramos', '75063149', '1996-06-15', 0, 'Pasaje El Milagro 76', '921674538', 'ACTIVO');

insert programa values 
(null,'PAP','Prueba papanicolao', 2025, 300, 'EN PROCESO'),
(null,'IVA','Inspección visual con ácido acético ', 2025, 150, 'EN PROCESO'),
(null,'VPH','Prueba del virus del papiloma humano', 2025, 500, 'EN PROCESO'),
(null,'Consejeria','Asesoramiento en general', 2025, 300, 'EN PROCESO'),
(null,'MAMAS','Estudio Mamas', 2025, 300, 'EN PROCESO');

INSERT INTO Atencion (idPaciente, idObstetra, fechaRegistro, fechaCita, descripcion, estado, idPrograma) VALUES
(29, 2, '2025-06-28 07:00:00', '2025-07-01 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(17, 2, '2025-06-28 06:00:00', '2025-07-01 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(46, 2, '2025-06-30 06:00:00', '2025-07-01 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(6, 2, '2025-06-29 10:00:00', '2025-07-01 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(16, 2, '2025-06-30 08:00:00', '2025-07-01 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(2, 2, '2025-06-28 11:00:00', '2025-07-01 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(21, 2, '2025-06-30 11:00:00', '2025-07-01 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(30, 2, '2025-06-29 16:00:00', '2025-07-01 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(33, 2, '2025-06-29 03:00:00', '2025-07-02 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(15, 2, '2025-07-01 08:00:00', '2025-07-02 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(6, 2, '2025-07-01 09:00:00', '2025-07-02 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(10, 2, '2025-06-30 09:00:00', '2025-07-02 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(18, 2, '2025-06-30 11:00:00', '2025-07-02 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(9, 2, '2025-06-29 13:00:00', '2025-07-02 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(11, 2, '2025-07-01 13:00:00', '2025-07-02 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(7, 2, '2025-07-01 16:00:00', '2025-07-02 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(42, 2, '2025-06-30 06:00:00', '2025-07-03 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(22, 2, '2025-06-30 09:00:00', '2025-07-03 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(29, 2, '2025-07-02 09:00:00', '2025-07-03 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(42, 2, '2025-07-02 10:00:00', '2025-07-03 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(46, 2, '2025-06-30 13:00:00', '2025-07-03 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(13, 2, '2025-06-30 11:00:00', '2025-07-03 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(32, 2, '2025-07-01 10:00:00', '2025-07-03 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(23, 2, '2025-07-01 16:00:00', '2025-07-03 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(3, 2, '2025-07-03 04:00:00', '2025-07-04 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(19, 2, '2025-07-02 04:00:00', '2025-07-04 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(13, 2, '2025-07-03 10:00:00', '2025-07-04 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(44, 2, '2025-07-03 07:00:00', '2025-07-04 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(18, 2, '2025-07-03 13:00:00', '2025-07-04 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(4, 2, '2025-07-01 10:00:00', '2025-07-04 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(9, 2, '2025-07-03 14:00:00', '2025-07-04 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(13, 2, '2025-07-01 15:00:00', '2025-07-04 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(24, 2, '2025-07-06 06:00:00', '2025-07-07 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(22, 2, '2025-07-04 06:00:00', '2025-07-07 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(43, 2, '2025-07-05 06:00:00', '2025-07-07 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(5, 2, '2025-07-05 07:00:00', '2025-07-07 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(19, 2, '2025-07-05 08:00:00', '2025-07-07 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(8, 2, '2025-07-04 14:00:00', '2025-07-07 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(15, 2, '2025-07-04 14:00:00', '2025-07-07 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(43, 2, '2025-07-05 12:00:00', '2025-07-07 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(47, 2, '2025-07-06 04:00:00', '2025-07-08 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(6, 2, '2025-07-05 07:00:00', '2025-07-08 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(32, 2, '2025-07-07 05:00:00', '2025-07-08 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(19, 2, '2025-07-07 08:00:00', '2025-07-08 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(12, 2, '2025-07-05 12:00:00', '2025-07-08 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(21, 2, '2025-07-05 10:00:00', '2025-07-08 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(5, 2, '2025-07-06 10:00:00', '2025-07-08 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(21, 2, '2025-07-06 16:00:00', '2025-07-08 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(47, 2, '2025-07-08 04:00:00', '2025-07-09 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(5, 2, '2025-07-07 08:00:00', '2025-07-09 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(48, 2, '2025-07-07 09:00:00', '2025-07-09 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(26, 2, '2025-07-07 08:00:00', '2025-07-09 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(7, 2, '2025-07-08 10:00:00', '2025-07-09 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(5, 2, '2025-07-07 10:00:00', '2025-07-09 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(31, 2, '2025-07-06 12:00:00', '2025-07-09 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(48, 2, '2025-07-07 11:00:00', '2025-07-09 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(5, 2, '2025-07-09 04:00:00', '2025-07-10 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(20, 2, '2025-07-09 04:00:00', '2025-07-10 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(13, 2, '2025-07-09 05:00:00', '2025-07-10 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(8, 2, '2025-07-09 06:00:00', '2025-07-10 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(13, 2, '2025-07-07 13:00:00', '2025-07-10 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(45, 2, '2025-07-08 13:00:00', '2025-07-10 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(35, 2, '2025-07-07 13:00:00', '2025-07-10 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(42, 2, '2025-07-08 12:00:00', '2025-07-10 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(24, 2, '2025-07-08 04:00:00', '2025-07-11 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(47, 2, '2025-07-09 05:00:00', '2025-07-11 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(50, 2, '2025-07-10 08:00:00', '2025-07-11 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(21, 2, '2025-07-10 11:00:00', '2025-07-11 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(33, 2, '2025-07-10 08:00:00', '2025-07-11 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(35, 2, '2025-07-10 13:00:00', '2025-07-11 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(7, 2, '2025-07-09 10:00:00', '2025-07-11 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(26, 2, '2025-07-10 12:00:00', '2025-07-11 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(10, 2, '2025-07-12 03:00:00', '2025-07-14 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(21, 2, '2025-07-13 04:00:00', '2025-07-14 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(22, 2, '2025-07-12 08:00:00', '2025-07-14 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(22, 2, '2025-07-13 10:00:00', '2025-07-14 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(27, 2, '2025-07-13 12:00:00', '2025-07-14 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(1, 2, '2025-07-12 10:00:00', '2025-07-14 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(46, 2, '2025-07-12 14:00:00', '2025-07-14 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(18, 2, '2025-07-13 16:00:00', '2025-07-14 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(40, 2, '2025-07-13 03:00:00', '2025-07-15 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(26, 2, '2025-07-14 05:00:00', '2025-07-15 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(8, 2, '2025-07-14 05:00:00', '2025-07-15 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(6, 2, '2025-07-14 09:00:00', '2025-07-15 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(45, 2, '2025-07-12 11:00:00', '2025-07-15 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(10, 2, '2025-07-14 14:00:00', '2025-07-15 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(22, 2, '2025-07-14 10:00:00', '2025-07-15 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(40, 2, '2025-07-13 12:00:00', '2025-07-15 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(20, 2, '2025-07-13 03:00:00', '2025-07-16 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(43, 2, '2025-07-14 06:00:00', '2025-07-16 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(22, 2, '2025-07-13 08:00:00', '2025-07-16 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(32, 2, '2025-07-13 11:00:00', '2025-07-16 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(14, 2, '2025-07-15 13:00:00', '2025-07-16 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(5, 2, '2025-07-14 09:00:00', '2025-07-16 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(6, 2, '2025-07-15 12:00:00', '2025-07-16 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(17, 2, '2025-07-15 16:00:00', '2025-07-16 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(36, 2, '2025-07-16 07:00:00', '2025-07-17 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(49, 2, '2025-07-14 04:00:00', '2025-07-17 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(31, 2, '2025-07-14 09:00:00', '2025-07-17 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(22, 2, '2025-07-15 11:00:00', '2025-07-17 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4);
INSERT INTO Atencion (idPaciente, idObstetra, fechaRegistro, fechaCita, descripcion, estado, idPrograma) VALUES
(23, 3, '2025-07-02 05:00:00', '2025-07-05 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(39, 3, '2025-07-04 04:00:00', '2025-07-05 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(48, 3, '2025-07-03 10:00:00', '2025-07-05 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(40, 3, '2025-07-03 08:00:00', '2025-07-05 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(50, 3, '2025-07-04 11:00:00', '2025-07-05 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(44, 3, '2025-07-04 11:00:00', '2025-07-05 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(1, 3, '2025-07-03 10:00:00', '2025-07-05 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(10, 3, '2025-07-03 13:00:00', '2025-07-05 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(26, 3, '2025-07-03 05:00:00', '2025-07-06 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(16, 3, '2025-07-05 07:00:00', '2025-07-06 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(16, 3, '2025-07-05 09:00:00', '2025-07-06 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(25, 3, '2025-07-05 06:00:00', '2025-07-06 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(22, 3, '2025-07-05 11:00:00', '2025-07-06 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(49, 3, '2025-07-05 14:00:00', '2025-07-06 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(3, 3, '2025-07-04 10:00:00', '2025-07-06 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(28, 3, '2025-07-03 13:00:00', '2025-07-06 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(14, 3, '2025-07-11 04:00:00', '2025-07-12 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(36, 3, '2025-07-11 04:00:00', '2025-07-12 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(13, 3, '2025-07-09 05:00:00', '2025-07-12 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(30, 3, '2025-07-10 11:00:00', '2025-07-12 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(45, 3, '2025-07-09 08:00:00', '2025-07-12 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(3, 3, '2025-07-11 11:00:00', '2025-07-12 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(29, 3, '2025-07-11 13:00:00', '2025-07-12 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(17, 3, '2025-07-11 16:00:00', '2025-07-12 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(5, 3, '2025-07-10 05:00:00', '2025-07-13 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(36, 3, '2025-07-10 04:00:00', '2025-07-13 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(9, 3, '2025-07-10 05:00:00', '2025-07-13 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(20, 3, '2025-07-11 06:00:00', '2025-07-13 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(23, 3, '2025-07-11 11:00:00', '2025-07-13 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(5, 3, '2025-07-12 09:00:00', '2025-07-13 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(41, 3, '2025-07-11 15:00:00', '2025-07-13 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(21, 3, '2025-07-12 12:00:00', '2025-07-13 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(25, 3, '2025-07-15 04:00:00', '2025-07-18 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(19, 3, '2025-07-16 05:00:00', '2025-07-18 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(23, 3, '2025-07-15 07:00:00', '2025-07-18 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(13, 3, '2025-07-17 06:00:00', '2025-07-18 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(32, 3, '2025-07-17 13:00:00', '2025-07-18 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(23, 3, '2025-07-16 09:00:00', '2025-07-18 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(8, 3, '2025-07-16 12:00:00', '2025-07-18 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(2, 3, '2025-07-15 13:00:00', '2025-07-18 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(19, 3, '2025-07-17 06:00:00', '2025-07-19 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(9, 3, '2025-07-18 05:00:00', '2025-07-19 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(11, 3, '2025-07-18 05:00:00', '2025-07-19 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(25, 3, '2025-07-17 08:00:00', '2025-07-19 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(33, 3, '2025-07-17 11:00:00', '2025-07-19 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(24, 3, '2025-07-16 14:00:00', '2025-07-19 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(44, 3, '2025-07-16 12:00:00', '2025-07-19 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(16, 3, '2025-07-16 16:00:00', '2025-07-19 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(44, 3, '2025-07-19 04:00:00', '2025-07-20 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(7, 3, '2025-07-17 06:00:00', '2025-07-20 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(23, 3, '2025-07-19 10:00:00', '2025-07-20 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(34, 3, '2025-07-19 08:00:00', '2025-07-20 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(44, 3, '2025-07-18 09:00:00', '2025-07-20 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(4, 3, '2025-07-19 09:00:00', '2025-07-20 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(1, 3, '2025-07-18 11:00:00', '2025-07-20 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(15, 3, '2025-07-19 11:00:00', '2025-07-20 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(48, 3, '2025-07-19 08:00:00', '2025-07-21 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(13, 3, '2025-07-20 05:00:00', '2025-07-21 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(40, 3, '2025-07-18 10:00:00', '2025-07-21 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(25, 3, '2025-07-19 09:00:00', '2025-07-21 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(3, 3, '2025-07-18 10:00:00', '2025-07-21 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(19, 3, '2025-07-19 09:00:00', '2025-07-21 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(19, 3, '2025-07-18 13:00:00', '2025-07-21 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(13, 3, '2025-07-20 13:00:00', '2025-07-21 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(17, 3, '2025-07-19 08:00:00', '2025-07-22 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(20, 3, '2025-07-20 05:00:00', '2025-07-22 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(28, 3, '2025-07-21 09:00:00', '2025-07-22 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(3, 3, '2025-07-21 06:00:00', '2025-07-22 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(40, 3, '2025-07-20 10:00:00', '2025-07-22 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(18, 3, '2025-07-20 10:00:00', '2025-07-22 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(16, 3, '2025-07-21 11:00:00', '2025-07-22 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(25, 3, '2025-07-19 13:00:00', '2025-07-22 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(31, 3, '2025-07-20 08:00:00', '2025-07-23 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(46, 3, '2025-07-22 07:00:00', '2025-07-23 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(18, 3, '2025-07-20 10:00:00', '2025-07-23 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(24, 3, '2025-07-22 06:00:00', '2025-07-23 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(41, 3, '2025-07-22 12:00:00', '2025-07-23 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(32, 3, '2025-07-22 12:00:00', '2025-07-23 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(17, 3, '2025-07-20 15:00:00', '2025-07-23 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(13, 3, '2025-07-22 14:00:00', '2025-07-23 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(43, 3, '2025-07-22 06:00:00', '2025-07-24 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(2, 3, '2025-07-21 04:00:00', '2025-07-24 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(19, 3, '2025-07-23 06:00:00', '2025-07-24 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(8, 3, '2025-07-21 07:00:00', '2025-07-24 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(15, 3, '2025-07-23 08:00:00', '2025-07-24 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(33, 3, '2025-07-22 09:00:00', '2025-07-24 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(39, 3, '2025-07-21 11:00:00', '2025-07-24 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(15, 3, '2025-07-22 11:00:00', '2025-07-24 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(44, 3, '2025-07-24 03:00:00', '2025-07-25 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(23, 3, '2025-07-23 04:00:00', '2025-07-25 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(43, 3, '2025-07-23 09:00:00', '2025-07-25 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4),
(12, 3, '2025-07-24 08:00:00', '2025-07-25 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(15, 3, '2025-07-24 09:00:00', '2025-07-25 13:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(2, 3, '2025-07-24 09:00:00', '2025-07-25 14:00:00', 'Cita programada automáticamente', 'PENDIENTE', 5),
(13, 3, '2025-07-24 15:00:00', '2025-07-25 15:00:00', 'Cita programada automáticamente', 'PENDIENTE', 3),
(20, 3, '2025-07-24 14:00:00', '2025-07-25 16:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(46, 3, '2025-07-23 08:00:00', '2025-07-26 08:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(7, 3, '2025-07-23 04:00:00', '2025-07-26 09:00:00', 'Cita programada automáticamente', 'PENDIENTE', 2),
(23, 3, '2025-07-23 06:00:00', '2025-07-26 10:00:00', 'Cita programada automáticamente', 'PENDIENTE', 1),
(15, 3, '2025-07-23 10:00:00', '2025-07-26 11:00:00', 'Cita programada automáticamente', 'PENDIENTE', 4);

 INSERT INTO Atencion (idPaciente, idObstetra, fechaRegistro, fechaCita, descripcion, estado, idPrograma) VALUES
 (28, 4, '2025-07-31 11:00:00', '2025-08-01 08:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (11, 4, '2025-07-31 18:15:00', '2025-08-01 09:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (43, 4, '2025-07-30 15:30:00', '2025-08-01 10:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (3, 4, '2025-07-29 18:45:00', '2025-08-01 11:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (43, 4, '2025-07-31 12:30:00', '2025-08-01 14:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (3, 4, '2025-07-31 13:45:00', '2025-08-01 15:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (47, 4, '2025-07-29 18:30:00', '2025-08-01 16:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (45, 4, '2025-07-31 12:00:00', '2025-08-01 17:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (18, 4, '2025-08-02 10:00:00', '2025-08-04 08:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (35, 4, '2025-08-01 18:45:00', '2025-08-04 09:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (42, 4, '2025-08-03 15:30:00', '2025-08-04 10:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (21, 4, '2025-08-02 09:30:00', '2025-08-04 11:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (21, 4, '2025-08-01 12:00:00', '2025-08-04 14:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (42, 4, '2025-08-03 15:00:00', '2025-08-04 15:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (43, 4, '2025-08-01 18:15:00', '2025-08-04 16:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (32, 4, '2025-08-03 10:00:00', '2025-08-04 17:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (32, 4, '2025-08-04 10:45:00', '2025-08-05 08:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (49, 4, '2025-08-04 15:00:00', '2025-08-05 09:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (30, 4, '2025-08-03 09:30:00', '2025-08-05 10:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (3, 4, '2025-08-04 17:15:00', '2025-08-05 11:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (16, 4, '2025-08-03 08:15:00', '2025-08-05 14:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (23, 4, '2025-08-04 16:15:00', '2025-08-05 15:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (29, 4, '2025-08-03 18:00:00', '2025-08-05 16:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (7, 4, '2025-08-03 14:45:00', '2025-08-05 17:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (10, 4, '2025-08-04 15:15:00', '2025-08-06 08:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (3, 4, '2025-08-03 16:30:00', '2025-08-06 09:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (40, 4, '2025-08-04 14:30:00', '2025-08-06 10:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (10, 4, '2025-08-04 17:15:00', '2025-08-06 11:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (10, 4, '2025-08-05 15:15:00', '2025-08-06 14:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (38, 4, '2025-08-03 16:45:00', '2025-08-06 15:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (12, 4, '2025-08-05 08:00:00', '2025-08-06 16:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (44, 4, '2025-08-04 16:00:00', '2025-08-06 17:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (30, 4, '2025-08-04 11:45:00', '2025-08-07 08:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (43, 4, '2025-08-05 10:30:00', '2025-08-07 09:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (39, 4, '2025-08-06 13:30:00', '2025-08-07 10:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (40, 4, '2025-08-06 12:15:00', '2025-08-07 11:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (14, 4, '2025-08-04 18:30:00', '2025-08-07 14:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (19, 4, '2025-08-04 12:30:00', '2025-08-07 15:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (32, 4, '2025-08-05 10:30:00', '2025-08-07 16:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (8, 4, '2025-08-06 11:30:00', '2025-08-07 17:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (37, 4, '2025-08-05 10:00:00', '2025-08-08 08:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (39, 4, '2025-08-05 14:15:00', '2025-08-08 09:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (2, 4, '2025-08-05 09:45:00', '2025-08-08 10:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (24, 4, '2025-08-05 11:30:00', '2025-08-08 11:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (8, 4, '2025-08-06 18:00:00', '2025-08-08 14:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (25, 4, '2025-08-05 15:45:00', '2025-08-08 15:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (12, 4, '2025-08-07 18:00:00', '2025-08-08 16:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (29, 4, '2025-08-06 14:30:00', '2025-08-08 17:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (45, 4, '2025-08-08 15:00:00', '2025-08-11 08:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (3, 4, '2025-08-10 18:15:00', '2025-08-11 09:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (9, 4, '2025-08-09 16:15:00', '2025-08-11 10:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (5, 4, '2025-08-08 17:15:00', '2025-08-11 11:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (1, 4, '2025-08-09 09:15:00', '2025-08-11 14:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (9, 4, '2025-08-08 08:45:00', '2025-08-11 15:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (9, 4, '2025-08-09 18:45:00', '2025-08-11 16:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (10, 4, '2025-08-08 18:30:00', '2025-08-11 17:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (33, 4, '2025-08-09 09:15:00', '2025-08-12 08:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (50, 4, '2025-08-09 14:30:00', '2025-08-12 09:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (37, 4, '2025-08-10 18:30:00', '2025-08-12 10:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (31, 4, '2025-08-09 16:45:00', '2025-08-12 11:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (1, 4, '2025-08-11 16:00:00', '2025-08-12 14:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (26, 4, '2025-08-11 16:00:00', '2025-08-12 15:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (27, 4, '2025-08-09 08:30:00', '2025-08-12 16:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (12, 4, '2025-08-11 17:00:00', '2025-08-12 17:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (8, 4, '2025-08-11 09:45:00', '2025-08-13 08:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (39, 4, '2025-08-11 12:30:00', '2025-08-13 09:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (30, 4, '2025-08-11 12:45:00', '2025-08-13 10:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (32, 4, '2025-08-11 16:45:00', '2025-08-13 11:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (10, 4, '2025-08-10 08:15:00', '2025-08-13 14:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (33, 4, '2025-08-12 16:45:00', '2025-08-13 15:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (28, 4, '2025-08-12 11:15:00', '2025-08-13 16:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (44, 4, '2025-08-11 15:15:00', '2025-08-13 17:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (48, 4, '2025-08-11 09:45:00', '2025-08-14 08:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (20, 4, '2025-08-13 17:45:00', '2025-08-14 09:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (4, 4, '2025-08-12 12:30:00', '2025-08-14 10:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (50, 4, '2025-08-12 13:00:00', '2025-08-14 11:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (40, 4, '2025-08-13 11:00:00', '2025-08-14 14:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (7, 4, '2025-08-13 11:15:00', '2025-08-14 15:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (10, 4, '2025-08-11 09:00:00', '2025-08-14 16:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (38, 4, '2025-08-13 13:00:00', '2025-08-14 17:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (34, 4, '2025-08-13 17:45:00', '2025-08-15 08:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (14, 4, '2025-08-12 17:15:00', '2025-08-15 09:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (35, 4, '2025-08-14 18:00:00', '2025-08-15 10:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (23, 4, '2025-08-12 14:30:00', '2025-08-15 11:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (3, 4, '2025-08-14 08:30:00', '2025-08-15 14:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (11, 4, '2025-08-12 14:30:00', '2025-08-15 15:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (34, 4, '2025-08-13 18:00:00', '2025-08-15 16:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (38, 4, '2025-08-14 15:45:00', '2025-08-15 17:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (50, 4, '2025-08-17 13:15:00', '2025-08-18 08:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (17, 4, '2025-08-16 09:00:00', '2025-08-18 09:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (47, 4, '2025-08-16 08:00:00', '2025-08-18 10:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (4, 4, '2025-08-15 17:00:00', '2025-08-18 11:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (47, 4, '2025-08-16 12:00:00', '2025-08-18 14:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (28, 4, '2025-08-16 08:00:00', '2025-08-18 15:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (45, 4, '2025-08-15 18:45:00', '2025-08-18 16:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5),
 (33, 4, '2025-08-15 12:45:00', '2025-08-18 17:00:00', 'Consulta programada para atención de programa 1', 'PROGRAMADA', 1),
 (24, 4, '2025-08-16 09:30:00', '2025-08-19 08:00:00', 'Consulta programada para atención de programa 2', 'PROGRAMADA', 2),
 (11, 4, '2025-08-18 13:45:00', '2025-08-19 09:00:00', 'Consulta programada para atención de programa 3', 'PROGRAMADA', 3),
 (48, 4, '2025-08-16 10:15:00', '2025-08-19 10:00:00', 'Consulta programada para atención de programa 4', 'PROGRAMADA', 4),
 (43, 4, '2025-08-16 11:00:00', '2025-08-19 11:00:00', 'Consulta programada para atención de programa 5', 'PROGRAMADA', 5);
 
 
 update atencion set estado = 'PENDIENTE' where  estado = 'PROGRAMADA';
 
UPDATE Atencion
SET estado = 'COMPLETADA'
WHERE estado = 'PENDIENTE'
  AND fechaCita < NOW();



CREATE VIEW usuario AS 
(
    SELECT CONCAT(nombres, ' ', apellidos) nombre, dni, contrasena, tipoUsuario, c.estado
    FROM cuenta c 
    inner JOIN obstetra o on c.idObstetra = o.idObstetra
);

delimiter //
create procedure PA_ValidarCredenciales
	(
		in p_dni char(8),
		in p_contrasena varchar(32)
	)
	
	begin
	if(exists (select * from Usuario where dni = p_dni and contrasena = sha2(p_contrasena,256))) then		
		select 1 as resultado, nombre, tipoUsuario from usuario where dni = p_dni;          
	else 
		select 0 as resultado;
	end if;

	end;
	//
delimiter ;


/* PA Obstetra */

delimiter //
create procedure PA_RegistrarObstetra
(
	in p_nombres varchar(255),
    in p_apellidos varchar(255),
    in p_dni varchar(255),
    in p_fechaNac varchar(255),
    in p_direccion varchar(255), 
    in p_telefono varchar(255),
    in p_nroColegiatura varchar(255),
    in p_colegio varchar(255),
    in p_fechaColegiatura varchar(255)
)
begin
	insert Obstetra values (null, p_nombres, p_apellidos, p_dni, p_fechaNac, p_direccion, p_telefono, p_nroColegiatura, p_colegio, p_fechaColegiatura,'ACTIVO');
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ListarObstetras()
begin
	select o.idObstetra id, concat(nombres,' ',apellidos)nombre, dni, fechaNac, direccion, telefono, numeroColegiatura, procedenciaColegio, fechaColegiatura, o.estado
	from Obstetra o
	left join Cuenta c on o.idObstetra = c.idObstetra
	where c.tipoUsuario <> 'SUPER_ADMIN' or c.tipoUsuario is null
	order by apellidos;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_BuscarObstetras(in texto varchar(255))
begin
	select o.idObstetra id, concat(nombres,' ',apellidos)nombre, dni, fechaNac, direccion, telefono, numeroColegiatura, procedenciaColegio, fechaColegiatura, o.estado
	from Obstetra o
	join Cuenta c on o.idObstetra = c.idObstetra
    where dni like(concat(texto,'%')) or
		  nombres like(concat(texto,'%')) or
          apellidos like (concat(texto,'%'));
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ActualizarObstetra
(
	in p_id varchar(255),
	in p_nombres varchar(255),
    in p_apellidos varchar(255),
    in p_dni varchar(255),
    in p_fechaNac varchar(255),
    in p_direccion varchar(255), 
    in p_telefono varchar(255),
    in p_nroColegiatura varchar(255),
    in p_colegio varchar(255),
    in p_fechaColegiatura varchar(255),
    in p_estado varchar(255)
)
begin
	update Obstetra
	set nombres = p_nombres,
        apellidos = p_apellidos,
        dni = p_dni,
        fechaNac = p_fechaNac,
        direccion = p_direccion,
        telefono = p_telefono,
        numeroColegiatura = p_nroColegiatura,
        procedenciaColegio = p_colegio,
        fechaColegiatura = p_fechaColegiatura,
        estado = p_estado
    where idObstetra = p_id;
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_EliminarObstetra(in id varchar(255))
begin
	delete from Obstetra where idObstetra = id;
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ObtenerDatosObstetra(in id varchar(255))
begin
	select * from Obstetra where idObstetra = id;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ObtenerObstetras()
begin
	select concat(nombres,' ', apellidos) as nombre
	from Obstetra o
	join Cuenta c on o.idObstetra = c.idObstetra
	where c.tipoUsuario <> 'SUPER_ADMIN'
	order by apellidos;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_CantidadObstetras(OUT CANTIDAD INT)
begin
	SELECT COUNT(*) INTO CANTIDAD
	FROM Obstetra o
	WHERE o.idObstetra NOT IN (
		SELECT c.idObstetra
		FROM Cuenta c
		WHERE c.tipoUsuario = 'SUPER_ADMIN'
);
end//
delimiter ;
SET @cantidad := 0;
CAL pa_PA_CantidadObstetras(@cantidad)
SELECT @cantidad
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

DELIMITER //
CREATE PROCEDURE PA_Top10Obstetras()
BEGIN
	SELECT 
		CONCAT(o.nombres, ' ', o.apellidos) AS obstetra,
		COUNT(a.idCita) AS totalAtenciones
	FROM Obstetra o
	JOIN Atencion a ON o.idObstetra = a.idObstetra
	GROUP BY o.idObstetra, o.nombres, o.apellidos
	ORDER BY totalAtenciones DESC
	LIMIT 10;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

/* PA Paciente */

delimiter //
create procedure PA_RegistrarPaciente
(
	in p_nombres varchar(255),
    in p_apellidos varchar(255),
    in p_dni varchar(255),
    in p_fechaNac varchar(255),
    in p_nroHijos int,
    in p_direccion varchar(255), 
    in p_telefono varchar(255)
)
begin
	insert paciente values (null, p_nombres, p_apellidos, p_dni, p_fechaNac, p_nroHijos, p_direccion, p_telefono, 'ACTIVO');
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ListarPacientes()
begin
	select idPaciente id, concat(nombres,' ',apellidos)nombre, dni, fechaNac, numeroHijos, direccion, telefono, estado
	from paciente;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_BuscarPacientes(in texto varchar(255))
begin
	select idPaciente id, concat(nombres,' ',apellidos)nombre, dni, fechaNac, numeroHijos, direccion, telefono, estado
	from paciente 
    where dni like(concat(texto,'%')) or
		  nombres like(concat(texto,'%')) or
          apellidos like (concat(texto,'%'));
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ActualizarPaciente
(
	in p_id varchar(255),
	in p_nombres varchar(255),
    in p_apellidos varchar(255),
    in p_dni varchar(255),
    in p_fechaNac varchar(255),
    in p_nroHijos int,
    in p_direccion varchar(255), 
    in p_telefono varchar(255),
    in p_estado varchar(255)
)
begin
	update Paciente
	set nombres = p_nombres,
        apellidos = p_apellidos,
        dni = p_dni,
        fechaNac = p_fechaNac,
        numeroHijos = p_nroHijos,
        direccion = p_direccion,
        telefono = p_telefono,
        estado = p_estado
    where idPaciente = p_id;
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_EliminarPaciente(in id varchar(255))
begin
	delete from Paciente where idPaciente = id;
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ObtenerDatosPaciente(in id varchar(255))
begin
	select * from Paciente where idPaciente = id;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ObtenerPacientes()
begin
	select concat(nombres,' ',apellidos) as pacientes 
    from Paciente
    order by apellidos;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
delimiter //
create procedure PA_ListarProgramas()
begin
	select * from Programa
end //
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

delimiter //
create procedure PA_BuscarPrograma(in texto varchar(255))
begin
	select * from Programa where nombre like(concat(texto,'%')) or
								 año like(concat(texto,'%'));
end //
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

delimiter //
create procedure PA_RegistrarPrograma
(
in p_nombre varchar(255),
in p_descripcion varchar(255),
in p_año int,
in p_metaAnual int
)
begin
	insert Programa values (null, p_nombre, p_descripcion, p_año, p_metaAnual, 'EN PROCESO');
    select row_count() as resultado;
end //
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

delimiter //
create procedure PA_ActualizarPrograma
(
in p_id int,
in p_nombre varchar(255),
in p_descripcion varchar(255),
in p_año int,
in p_metaAnual int,
in p_estado varchar(255)
)
begin
	update programa
    set nombre = p_nombre,
		descripcion = p_descripcion,
        año = p_año,
        metaAnual = p_metaAnual,
        estado = p_estado
	where idPrograma = p_id;
    select row_count() resultado;
end //
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

DELIMITER //
CREATE PROCEDURE PA_EliminarPrograma(IN id INT)
BEGIN
	UPDATE Programa
	SET estado = 'INACTIVO'
	WHERE idPrograma = id;

	SELECT ROW_COUNT() AS resultado;
END //
DELIMITER ;


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ObtenerDatosPrograma(in id int)
begin
    select * from Programa where idPrograma = id;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ObtenerProgramas()
begin
    select nombre as programas from Programa;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_MetaProgramas()
begin
    SELECT idPrograma, nombre, metaAnual FROM Programa;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_MetaProgramasPorId(in nombrePrograma varchar(255))
begin
    SELECT nombre, metaAnual FROM Programa where nombre = nombrePrograma;
end//
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

delimiter //
create procedure PA_ProgresoMetas()
begin
	SELECT 
		p.nombre AS programa,
		p.metaAnual,
		COUNT(a.idCita) AS progreso,
		ROUND(COUNT(a.idCita) * 100.0 / p.metaAnual, 2) AS porcentaje
	FROM Programa p
	LEFT JOIN Atencion a ON p.idPrograma = a.idPrograma
	GROUP BY p.idPrograma, p.nombre, p.metaAnual;

end //
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

DELIMITER //
CREATE PROCEDURE PA_ProgresoMetasCitasCompletas()
BEGIN
    SELECT 
        p.nombre AS programa,
        p.metaAnual,
        COUNT(a.idCita) AS progreso,
        ROUND(COUNT(a.idCita) * 100.0 / p.metaAnual, 2) AS porcentaje
    FROM Programa p
    LEFT JOIN Atencion a 
        ON p.idPrograma = a.idPrograma 
        AND a.estado = 'COMPLETADA'
    WHERE p.estado != 'INACTIVO'
    GROUP BY p.idPrograma, p.nombre, p.metaAnual;
END //
DELIMITER ;


-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
/* PA Citas */

create view V_TotalAtenciones as
	select idCita id, concat(pac.nombres,' ',pac.apellidos) paciente , 
	   concat(o.nombres,' ',o.apellidos) obstetra, fechaRegistro, 
       fechaCita, a.descripcion, a.estado, p.nombre programa 
	from atencion a
	inner join programa p on p.idPrograma = a.idPrograma
	inner join paciente pac on pac.idPaciente = a.idPaciente
	inner join obstetra o on o.idObstetra = a.idObstetra;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DELIMITER //
create PROCEDURE PA_RegistrarAtencion
(
	IN paciente VARCHAR(255),
	IN obstetra VARCHAR(255),
	IN fecha VARCHAR(255),
	IN descripcion VARCHAR(255),
	IN programa VARCHAR(255)
)
BEGIN
	DECLARE estadoFinal VARCHAR(20);

	-- Determinar si la cita ya pasó
	IF STR_TO_DATE(fecha, '%Y-%m-%d %H:%i:%s') < NOW() THEN
		SET estadoFinal = 'COMPLETADA';
	ELSE
		SET estadoFinal = 'PENDIENTE';
	END IF;

	INSERT INTO Atencion
	VALUES (
		NULL,
		(SELECT idPaciente FROM Paciente WHERE CONCAT(nombres, ' ', apellidos) = paciente),
		(SELECT idObstetra FROM Obstetra WHERE CONCAT(nombres, ' ', apellidos) = obstetra),
		NOW(),
		fecha,
		descripcion,
		estadoFinal,
		(SELECT idPrograma FROM Programa WHERE nombre = programa)
	);

	SELECT ROW_COUNT() AS resultado;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ListarAtenciones()
begin
	select * from V_TotalAtenciones;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ActualizarAtencion
(
	in id int,
	in paciente varchar(255),
    in obstetra varchar(255),
    in fecha varchar(255),
    in p_descripcion varchar(255),
    in p_estado varchar(255),
    in programa varchar(255)
    
)
begin
	update atencion set
        idPaciente = (select idPaciente from paciente where concat(nombres,' ',apellidos) = paciente), 
		idObstetra = (select idObstetra from obstetra where concat(nombres,' ',apellidos) = obstetra),
        fechaCita =  fecha,
        descripcion = p_descripcion,
        estado = p_estado,
        idPrograma = (select idPrograma from programa where nombre = programa)
        where idCita = id;
    select row_count() as resultado;
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_CancelarAtencion(in id int)
begin
	update Atencion set estado = 'CANCELADO' where idCita = id;
    select row_count() resultado;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_BuscarAtencion(in texto varchar(255))
begin
	select * from V_TotalAtenciones where id = texto or fechaCita = texto;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ObtenerDatosAtencion(in p_id int)
begin
	select * from V_TotalAtenciones where id = p_id;
end//
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DELIMITER //
CREATE PROCEDURE PA_ResumenCitasPorDia()
BEGIN
    SELECT 
        DATE(fechaCita) AS fecha,
        p.nombre AS programa,
        COUNT(*) AS total
    FROM Atencion a
    JOIN Programa p ON a.idPrograma = p.idPrograma
    GROUP BY fecha, programa
    ORDER BY fecha;
END;
//
DELIMITER ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
DELIMITER //
CREATE PROCEDURE PA_ResumenCitasPorDiaPorObstetra(IN nombreCompleto VARCHAR(100))
BEGIN
    SELECT 
        DATE(a.fechaCita) AS fecha,
        p.nombre AS programa,
        COUNT(*) AS total
    FROM Atencion a
    JOIN Programa p ON a.idPrograma = p.idPrograma
    JOIN Obstetra o ON a.idObstetra = o.idObstetra
    WHERE CONCAT(o.nombres, ' ', o.apellidos) = nombreCompleto
    GROUP BY fecha, programa
    ORDER BY fecha;
END;
//
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_CitasCalendario(in fecha varchar(255))
begin
	select id, DATE_FORMAT(fechaCita, '%H:%i') as hora, obstetra, paciente, programa, estado 
	from v_totalatenciones
	where fechaCita like (concat(fecha,'%'));
end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_CitasCalendarioPorObstetra(in fecha varchar(255), in p_obstetra varchar(255))
begin
	select id, DATE_FORMAT(fechaCita, '%H:%i') as hora, paciente, programa, estado 
	from v_totalatenciones
	where fechaCita like (concat(fecha,'%')) and obstetra = p_obstetra;

end //
delimiter ;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
delimiter //
create procedure PA_ObtenerHorasCitasPorObstetra(in obstetra varchar(255), in fecha varchar(255))
begin
	SELECT HOUR(fechaCita) AS hora
	FROM Atencion
	WHERE idObstetra =(select idObstetra  from obstetra WHERE concat(nombres,' ',apellidos) = obstetra )
	AND DATE(fechaCita) = fecha;
end//
delimiter ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 

DELIMITER //
CREATE PROCEDURE PA_DetalleCitaPorFecha(IN inicio VARCHAR(255),IN fin VARCHAR(255))
BEGIN
	SELECT * 
	FROM v_totalatenciones 
	WHERE fechaCita BETWEEN inicio AND fin;
END //
DELIMITER ;


