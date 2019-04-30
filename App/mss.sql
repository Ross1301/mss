-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2019 at 06:14 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mss`
--

-- --------------------------------------------------------

--
-- Table structure for table `clase`
--

CREATE TABLE `clase` (
  `Dia` tinyint(4) NOT NULL,
  `Hora` tinyint(4) NOT NULL,
  `Minuto` tinyint(4) NOT NULL,
  `Tipo` tinyint(4) NOT NULL,
  `Cupo` tinyint(4) NOT NULL,
  `Cantidad` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clase`
--

INSERT INTO `clase` (`Dia`, `Hora`, `Minuto`, `Tipo`, `Cupo`, `Cantidad`) VALUES
(0, 8, 0, 1, 15, 0),
(0, 8, 30, 1, 15, 0),
(0, 14, 0, 1, 15, 0),
(0, 15, 0, 1, 15, 0),
(0, 16, 0, 1, 15, 0),
(0, 17, 0, 1, 15, 0),
(0, 20, 30, 1, 15, 0),
(0, 23, 0, 1, 15, 0),
(1, 0, 0, 1, 15, 0),
(1, 7, 0, 1, 15, 0),
(1, 8, 0, 1, 15, 0),
(1, 20, 0, 1, 15, 0),
(1, 21, 0, 1, 15, 0),
(1, 22, 0, 1, 15, 0),
(1, 23, 30, 1, 15, 0),
(2, 0, 0, 1, 15, 1),
(2, 5, 0, 1, 15, 0),
(2, 6, 0, 1, 15, 0),
(2, 7, 0, 1, 15, 0),
(2, 9, 0, 1, 15, 0),
(6, 0, 0, 1, 15, 0),
(6, 9, 0, 1, 15, 0),
(6, 10, 0, 2, 15, 0),
(6, 11, 0, 1, 15, 1),
(6, 13, 0, 1, 15, 1),
(6, 14, 0, 2, 15, 0),
(6, 15, 0, 1, 15, 0);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `Usuario` varchar(25) NOT NULL,
  `Pass` varchar(100) DEFAULT NULL,
  `Tipo` tinyint(4) NOT NULL,
  `Maximo` tinyint(4) NOT NULL,
  `Lleva` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`Usuario`, `Pass`, `Tipo`, `Maximo`, `Lleva`) VALUES
('Adriana.Sanchez', '', 1, 3, 0),
('Adrianne.Pacheco', '', 1, 3, 0),
('Alejandro.Jiron', '', 1, 5, 0),
('Alejandro.Salas', '', 1, 3, 0),
('Alejandro.Sanchez', '', 1, 3, 0),
('Alex.Solis', '', 1, 2, 0),
('Andre.Canales', '', 1, 3, 0),
('Andres.Soto', '', 1, 3, 0),
('Andrey.Salazar', '', 1, 3, 0),
('Arturo.Ramirez', '', 2, 3, 0),
('Carlos.Nunez', '', 1, 3, 0),
('Carlos.Ramirez', '', 2, 2, 0),
('Cesar.Rosales', '', 1, 3, 0),
('Cesar.Salazar', '', 1, 4, 0),
('Daniel.Cambronero', '', 1, 3, 0),
('Dennis.Sancho', '', 1, 2, 0),
('Derek.Garita', '', 1, 2, 0),
('Diego.Camacho', '', 1, 3, 0),
('Diego.Salazar', '', 1, 4, 0),
('Douglas.Reynolds', '', 2, 3, 0),
('Eduardo.Gonzales', '', 1, 1, 0),
('Fabian.Quesada', '', 1, 3, 0),
('Fabiola.Madrigal', '', 1, 3, 0),
('Fernanda.Munoz', '', 1, 5, 0),
('Fernando.Munoz', '', 1, 3, 0),
('Fiorella.Lizano', '', 1, 3, 0),
('Franklin.Padilla', '', 2, 3, 0),
('Javier.Matamoros', '', 1, 3, 0),
('Jina.Gonzalez', '', 1, 3, 0),
('Jorge.Badilla', '', 2, 3, 0),
('Jose.Daniel', '', 1, 3, 0),
('Jose.Gonzalez', '', 1, 3, 0),
('Jose.Mora', '', 1, 3, 0),
('Jose.Quesada', '', 1, 3, 0),
('Jose.Sanchez', '', 1, 3, 0),
('Joselyn.Valverde', '', 1, 3, 0),
('Juan.Sanchez', '', 1, 3, 0),
('Karen.Ramirez', '', 1, 3, 0),
('Karla.Leon', '', 1, 3, 0),
('Kimberly.Reynolds', '', 2, 4, 0),
('Luis.Gamboa', '', 1, 4, 0),
('Maria.Cubero', '', 1, 3, 0),
('Mariano.Padilla', '', 1, 3, 0),
('Maribel.Villareal', '', 1, 1, 0),
('Mariela.Vargas', '', 1, 4, 0),
('Milena.Rodriguez', '', 1, 3, 0),
('Natalia.Alvarez', '', 1, 2, 0),
('Nelson.Alcantara', '', 1, 4, 0),
('Pablo.Ramirez', '', 2, 3, 0),
('Pamela.Leon', '', 1, 1, 0),
('Prueba.1', '', 1, 5, 3),
('Prueba.2', '', 1, 3, 0),
('Prueba.3', '', 1, 5, 0),
('Prueba.4', '', 1, 6, 0),
('Rafael.Jimenez', '', 2, 3, 0),
('Ricardo.Corrales', '', 1, 3, 0),
('Ricardo.Melendez', '', 1, 3, 0),
('Rolando.Sancho', '', 1, 1, 0),
('Ruben.Caballero', '', 1, 3, 0),
('Sebastian.Blanco', '', 1, 2, 0),
('Silvana.Rodriguez', '', 1, 3, 0),
('Sofia.Arce', '', 1, 3, 0),
('Stephanie.Venegas', '', 1, 3, 0),
('Valeshka.Solis', '', 2, 3, 0),
('Veronica.Jimenez', '', 1, 4, 0),
('Vilma.Perez', '', 2, 2, 0),
('Ximena.Vargas', '', 1, 4, 0),
('Yehudy.Chaves', '', 1, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `entrena`
--

CREATE TABLE `entrena` (
  `Usuario` varchar(25) NOT NULL,
  `Dia` tinyint(4) NOT NULL,
  `Hora` tinyint(4) NOT NULL,
  `Minuto` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entrena`
--

INSERT INTO `entrena` (`Usuario`, `Dia`, `Hora`, `Minuto`) VALUES
('prueba.1', 2, 0, 0),
('Prueba.1', 6, 11, 0),
('Prueba.1', 6, 13, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`Dia`,`Hora`,`Minuto`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`Usuario`);

--
-- Indexes for table `entrena`
--
ALTER TABLE `entrena`
  ADD PRIMARY KEY (`Usuario`,`Dia`,`Hora`,`Minuto`),
  ADD KEY `Dia` (`Dia`,`Hora`,`Minuto`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `entrena`
--
ALTER TABLE `entrena`
  ADD CONSTRAINT `entrena_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `cliente` (`Usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entrena_ibfk_2` FOREIGN KEY (`Dia`,`Hora`,`Minuto`) REFERENCES `clase` (`Dia`, `Hora`, `Minuto`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
