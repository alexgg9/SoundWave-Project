-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-10-2023 a las 14:33:49
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `soundwave`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista`
--

CREATE TABLE `artista` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `nacionalidad` varchar(256) NOT NULL,
  `foto` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artista_disco`
--

CREATE TABLE `artista_disco` (
  `dni_artista` varchar(9) NOT NULL,
  `id_disco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `cancion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `duracion` time NOT NULL,
  `genero` varchar(256) NOT NULL,
  `url` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion_lista`
--

CREATE TABLE `cancion_lista` (
  `id_cancion` int(11) NOT NULL,
  `id_lista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `contenido` varchar(256) NOT NULL,
  `fecha` int(11) NOT NULL,
  `dni_usuario` varchar(9) NOT NULL,
  `id_lista` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disco`
--

CREATE TABLE `disco` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `fecha_publicacion` date NOT NULL,
  `foto` longblob NOT NULL,
  `reproducciones` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disco_cancion`
--

CREATE TABLE `disco_cancion` (
  `id_disco` int(11) NOT NULL,
  `id_cancion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lista`
--

CREATE TABLE `lista` (
  `id` int(11) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `descripcion` varchar(256) NOT NULL,
  `dni_usuario` varchar(9) NOT NULL,
  `suscripciones` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripcion`
--

CREATE TABLE `suscripcion` (
  `dni_usuario` varchar(9) NOT NULL,
  `id_lista` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(256) NOT NULL,
  `correo` varchar(256) NOT NULL,
  `contraseña` varchar(256) NOT NULL,
  `foto` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `artista`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `artista_disco`
--
ALTER TABLE `artista_disco`
  ADD PRIMARY KEY (`dni_artista`,`id_disco`) USING BTREE,
  ADD KEY `id_disco` (`id_disco`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `cancion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cancion_lista`
--
ALTER TABLE `cancion_lista`
  ADD PRIMARY KEY (`id_cancion`,`id_lista`) USING BTREE,
  ADD KEY `id_lista` (`id_lista`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dni_usuario` (`dni_usuario`,`id_lista`),
  ADD KEY `id_lista` (`id_lista`);

--
-- Indices de la tabla `disco`
--
ALTER TABLE `disco`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `disco_cancion`
--
ALTER TABLE `disco_cancion`
  ADD PRIMARY KEY (`id_disco`,`id_cancion`) USING BTREE,
  ADD KEY `id_cancion` (`id_cancion`);

--
-- Indices de la tabla `lista`
--
ALTER TABLE `lista`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dni_usuario` (`dni_usuario`);

--
-- Indices de la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD KEY `dni_usuario` (`dni_usuario`,`id_lista`),
  ADD KEY `id_lista` (`id_lista`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cancion`
--
ALTER TABLE `cancion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `disco`
--
ALTER TABLE `disco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `lista`
--
ALTER TABLE `lista`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artista_disco`
--
ALTER TABLE `artista_disco`
  ADD CONSTRAINT `artista_disco_ibfk_1` FOREIGN KEY (`dni_artista`) REFERENCES `artista` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `artista_disco_ibfk_2` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cancion_lista`
--
ALTER TABLE `cancion_lista`
  ADD CONSTRAINT `cancion_lista_ibfk_1` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cancion_lista_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `disco_cancion`
--
ALTER TABLE `disco_cancion`
  ADD CONSTRAINT `disco_cancion_ibfk_1` FOREIGN KEY (`id_disco`) REFERENCES `disco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `disco_cancion_ibfk_2` FOREIGN KEY (`id_cancion`) REFERENCES `cancion` (`id`);

--
-- Filtros para la tabla `lista`
--
ALTER TABLE `lista`
  ADD CONSTRAINT `lista_ibfk_1` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `suscripcion`
--
ALTER TABLE `suscripcion`
  ADD CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `lista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
