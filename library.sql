-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.31 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para library
CREATE DATABASE IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library`;

-- Volcando estructura para tabla library.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_authorities_unique` (`user_id`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla library.authorities: ~3 rows (aproximadamente)
REPLACE INTO `authorities` (`id`, `user_id`, `authority`) VALUES
	(1, 1, 'ROLE_USER'),
	(3, 2, 'ROLE_ADMIN'),
	(2, 2, 'ROLE_USER');

-- Volcando estructura para tabla library.autor
CREATE TABLE IF NOT EXISTS `autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla library.autor: ~9 rows (aproximadamente)
REPLACE INTO `autor` (`id`, `nombre`, `nacionalidad`) VALUES
	(1, 'Plasencia, Juan Luis', 'Perú'),
	(2, 'Llorens Antonia', 'España'),
	(3, 'Sanabria, Carmelo', 'España'),
	(4, 'Richter, Helmut', 'Escocia'),
	(7, 'Llorens, Antonia', 'España'),
	(8, 'Dulac, George', 'EEUU'),
	(9, 'Davolio, Nancy', 'Croacia'),
	(10, 'Bazilian, Eric', 'Brasil'),
	(11, 'Bertomeu, Andrés', 'España');

-- Volcando estructura para tabla library.libro
CREATE TABLE IF NOT EXISTS `libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `editorial` varchar(45) DEFAULT NULL,
  `ISBN` varchar(45) DEFAULT NULL,
  `disponible_fisico` tinyint(1) DEFAULT NULL COMMENT '0 = No, 1 = Si',
  `autor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_libro-autor_idx` (`autor_id`),
  CONSTRAINT `fk_libro-autor` FOREIGN KEY (`autor_id`) REFERENCES `autor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla library.libro: ~8 rows (aproximadamente)
REPLACE INTO `libro` (`id`, `titulo`, `editorial`, `ISBN`, `disponible_fisico`, `autor_id`) VALUES
	(1, 'El tránsito terreno', 'Larrosa Mas, S.L', '84-121-2310-2', 1, 4),
	(2, 'Poemas intrínsecos', 'Deloria Editores', '84-305-0473-7', 1, 2),
	(3, 'La mente y el sentir', 'Larrosa Mas, S.L', '84-226-2128-2', 1, 1),
	(4, 'Avances en Arquitectura', 'TechniBooks', '84-473-0120-6', 0, 4),
	(5, 'Canto de esperanza', 'McCoy Hill', '84-444-0027-3', 0, 9),
	(36, 'Edicion', 'edicion', 'edicion', 0, 3),
	(37, 'Procesadores cuánticos', 'Grisham Publishing', '84-212-2121-2', 1, 10),
	(38, 'Libro edit', 'TechniBooks', '3393-339393', 0, 7);

-- Volcando estructura para tabla library.prestamo
CREATE TABLE IF NOT EXISTS `prestamo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_prestamo` date DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `devuelto` tinyint(1) DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  `libro_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prestamo-libro_idx` (`libro_id`),
  KEY `fk_prestamo-usuario_idx` (`usuario_id`),
  CONSTRAINT `fk_prestamo-libro` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_prestamo-usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla library.prestamo: ~4 rows (aproximadamente)
REPLACE INTO `prestamo` (`id`, `fecha_prestamo`, `fecha_devolucion`, `devuelto`, `usuario_id`, `libro_id`) VALUES
	(11, '2022-12-06', '2022-12-21', 0, 2, 3),
	(12, '2022-12-06', '2022-12-16', 0, 2, 3),
	(33, '2022-12-13', '2022-12-24', 1, 9, 36),
	(34, '2022-12-13', '2022-12-21', 1, 11, 2);

-- Volcando estructura para tabla library.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla library.users: ~2 rows (aproximadamente)
REPLACE INTO `users` (`id`, `username`, `password`, `enabled`) VALUES
	(1, 'andresp', '$2a$10$Ok5BIHXFJpJFoFUPp.SFYun9O0fS1sh.5yuhuOn/t5wW6yvaFh94y', 1),
	(2, 'admin', '$2a$10$52xQraLQm0wG0rMnAz.IPuNm/tFRLsVWtM.675eaUQY9aGym0DyMa', 1);

-- Volcando estructura para tabla library.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla library.usuario: ~7 rows (aproximadamente)
REPLACE INTO `usuario` (`id`, `nombre`, `correo`, `direccion`) VALUES
	(1, 'Andrés Pardo', 'andrespardo@gmail.com', 'St. Illionis 23'),
	(2, 'Juana Marquez', 'juanamarquez@gmail.com', 'St. LorkForward #1'),
	(7, 'Maura Garcia', 'mau@mail.com', '1234 Strret'),
	(8, 'Delfina Torres', 'pepi@mail.com', 'Calle 32'),
	(9, 'Nubia Jara', 'nubi@mail.com', 'Calle 23'),
	(11, 'Jhon Doe', 'jdoe@gmail.com', 'ster # 23'),
	(12, 'Carmelo Valencia', 'pipe572010@hotmail.com', 'que main sr 5');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
