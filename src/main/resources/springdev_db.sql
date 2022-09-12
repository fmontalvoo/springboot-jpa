-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 11-09-2022 a las 23:54:49
-- Versión del servidor: 8.0.30-0ubuntu0.22.04.1
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `springdev_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities`
--

CREATE TABLE `authorities` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `authority` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `authorities`
--

INSERT INTO `authorities` (`id`, `user_id`, `authority`) VALUES
(1, 1, 'ROLE_USER'),
(3, 2, 'ROLE_ADMIN'),
(2, 2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(256) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `enabled`) VALUES
(1, 'frank', '$2a$10$kki9MQyfLqgcSTXyqc0W4ODW0vQsVgmDeeNzFV.99F2v.Est90mm.', 1),
(2, 'admin', '$2a$10$t2p7nzbYU1hVKHyeZBE.Yef2oBWT832LQrS1MBOxsre337rG5lR4C', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id_authority_unique` (`user_id`,`authority`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
