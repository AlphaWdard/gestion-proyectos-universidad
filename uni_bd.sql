-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-08-2022 a las 21:15:28
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `uni_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `area`
--

CREATE TABLE `area` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `area`
--

INSERT INTO `area` (`id`, `nombre`) VALUES
(1, 'IA'),
(2, 'Bases de Datos'),
(3, 'Cloud');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facultad`
--

CREATE TABLE `facultad` (
  `id` int(11) NOT NULL,
  `idDecano` int(11) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `idSede` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupoinvestigacion`
--

CREATE TABLE `grupoinvestigacion` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `idArea` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `grupoinvestigacion`
--

INSERT INTO `grupoinvestigacion` (`codigo`, `nombre`, `idArea`) VALUES
(1, 'IA', 1),
(2, 'Mentodos', 1),
(3, 'Dilegecia', 3),
(5, 'DatabaseUD', 2),
(6, 'SoloSQL', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participar`
--

CREATE TABLE `participar` (
  `idProyecto` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `idRol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `participar`
--

INSERT INTO `participar` (`idProyecto`, `idProfesor`, `idRol`) VALUES
(504, 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pertenecer`
--

CREATE TABLE `pertenecer` (
  `idProfesor` int(11) NOT NULL,
  `idGrupoInvestigacion` int(11) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `fechaSalida` date DEFAULT NULL,
  `idRol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pertenecer`
--

INSERT INTO `pertenecer` (`idProfesor`, `idGrupoInvestigacion`, `fechaIngreso`, `fechaSalida`, `idRol`) VALUES
(5, 3, '2022-08-04', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `Titulo` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`id`, `nombre`, `Titulo`) VALUES
(3, 'Enrique', 'Ingeniero'),
(4, 'lucas', 'constructor'),
(5, 'Eber', 'Quimico'),
(6, 'Edward', 'Cientifico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `presupuesto` float DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaTerminacion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`codigo`, `nombre`, `presupuesto`, `fechaInicio`, `fechaTerminacion`) VALUES
(502, 'Analisis Socio Humanistico', 10000000, '2021-05-19', NULL),
(503, 'Experiencias de Desarrollo SQL', 10000000, '2020-04-14', '2021-04-14'),
(504, 'Java y sus App', 15000000, '2019-08-07', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolparticipar`
--

CREATE TABLE `rolparticipar` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rolparticipar`
--

INSERT INTO `rolparticipar` (`id`, `nombre`) VALUES
(1, 'Lider'),
(2, 'Participante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rolpertenecer`
--

CREATE TABLE `rolpertenecer` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rolpertenecer`
--

INSERT INTO `rolpertenecer` (`id`, `nombre`) VALUES
(1, 'Director'),
(2, 'Participante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sede`
--

CREATE TABLE `sede` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sede`
--

INSERT INTO `sede` (`id`, `nombre`) VALUES
(1, 'Ingenieria'),
(2, 'Ciencia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

CREATE TABLE `telefono` (
  `idFacultad` int(11) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(32) NOT NULL,
  `Palabra` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `Palabra`) VALUES
(1, 'admin', '827ccb0eea8a706c4c34a16891f84e7b', 'admin'),
(4, 'luc@correo.com', '827ccb0eea8a706c4c34a16891f84e7b', 'Piata'),
(5, 'correo', '827ccb0eea8a706c4c34a16891f84e7b', 'Perro'),
(6, 'Edward@correo.com', '827ccb0eea8a706c4c34a16891f84e7b', 'palabrotas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `facultad`
--
ALTER TABLE `facultad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idDecano` (`idDecano`),
  ADD KEY `idSede` (`idSede`);

--
-- Indices de la tabla `grupoinvestigacion`
--
ALTER TABLE `grupoinvestigacion`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `idArea` (`idArea`);

--
-- Indices de la tabla `participar`
--
ALTER TABLE `participar`
  ADD PRIMARY KEY (`idProyecto`,`idProfesor`),
  ADD KEY `idProfesor` (`idProfesor`),
  ADD KEY `idRol` (`idRol`);

--
-- Indices de la tabla `pertenecer`
--
ALTER TABLE `pertenecer`
  ADD PRIMARY KEY (`idProfesor`,`idGrupoInvestigacion`,`fechaIngreso`),
  ADD KEY `idGrupoInvestigacion` (`idGrupoInvestigacion`),
  ADD KEY `idProfesor` (`idProfesor`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `rolparticipar`
--
ALTER TABLE `rolparticipar`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `rolpertenecer`
--
ALTER TABLE `rolpertenecer`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sede`
--
ALTER TABLE `sede`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD PRIMARY KEY (`idFacultad`),
  ADD KEY `idFacultad` (`idFacultad`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facultad`
--
ALTER TABLE `facultad`
  ADD CONSTRAINT `facultad_ibfk_1` FOREIGN KEY (`idDecano`) REFERENCES `profesor` (`id`),
  ADD CONSTRAINT `facultad_ibfk_2` FOREIGN KEY (`idSede`) REFERENCES `sede` (`id`);

--
-- Filtros para la tabla `grupoinvestigacion`
--
ALTER TABLE `grupoinvestigacion`
  ADD CONSTRAINT `grupoinvestigacion_ibfk_1` FOREIGN KEY (`idArea`) REFERENCES `area` (`id`);

--
-- Filtros para la tabla `participar`
--
ALTER TABLE `participar`
  ADD CONSTRAINT `participar_ibfk_1` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`codigo`),
  ADD CONSTRAINT `participar_ibfk_2` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`id`),
  ADD CONSTRAINT `participar_ibfk_3` FOREIGN KEY (`idRol`) REFERENCES `rolparticipar` (`id`);

--
-- Filtros para la tabla `pertenecer`
--
ALTER TABLE `pertenecer`
  ADD CONSTRAINT `pertenecer_ibfk_1` FOREIGN KEY (`idGrupoInvestigacion`) REFERENCES `grupoinvestigacion` (`codigo`),
  ADD CONSTRAINT `pertenecer_ibfk_2` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `telefono_ibfk_1` FOREIGN KEY (`idFacultad`) REFERENCES `facultad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
