-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 27, 2015 at 11:16 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `studentskasluzbaispiti`
--
CREATE DATABASE IF NOT EXISTS `studentskasluzbaispiti` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `studentskasluzbaispiti`;

-- --------------------------------------------------------

--
-- Table structure for table `ispitnirok`
--

CREATE TABLE IF NOT EXISTS `ispitnirok` (
  `IspitniRokID` int(11) NOT NULL,
  `Naziv` text NOT NULL,
  PRIMARY KEY (`IspitniRokID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ispitnirok`
--

INSERT INTO `ispitnirok` (`IspitniRokID`, `Naziv`) VALUES
(1, 'Januar'),
(2, 'Februar'),
(3, 'Jun'),
(4, 'Septembar'),
(5, 'Oktobar'),
(6, 'Oktobar2');

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE IF NOT EXISTS `mesto` (
  `Ptt` int(11) NOT NULL,
  `Naziv` text NOT NULL,
  PRIMARY KEY (`Ptt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`Ptt`, `Naziv`) VALUES
(11000, 'Beograd'),
(18000, 'Nis'),
(21000, 'Novi Sad'),
(25000, 'Sombor');

-- --------------------------------------------------------

--
-- Table structure for table `predaje`
--

CREATE TABLE IF NOT EXISTS `predaje` (
  `PredajeID` int(11) NOT NULL,
  `GodinaStudija` int(11) NOT NULL,
  `Predmet` int(11) NOT NULL,
  `Profesor` int(11) DEFAULT NULL,
  `Smer` int(11) NOT NULL,
  PRIMARY KEY (`PredajeID`),
  KEY `Predmet` (`Predmet`,`Profesor`,`Smer`),
  KEY `Profesor` (`Profesor`),
  KEY `Smer` (`Smer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predaje`
--

INSERT INTO `predaje` (`PredajeID`, `GodinaStudija`, `Predmet`, `Profesor`, `Smer`) VALUES
(1, 1, 1, NULL, 3),
(2, 1, 2, NULL, 3),
(3, 2, 4, NULL, 3),
(4, 3, 6, NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `predmet`
--

CREATE TABLE IF NOT EXISTS `predmet` (
  `PredmetID` int(11) NOT NULL,
  `Naziv` text NOT NULL,
  `Sluzbenik` varchar(255) NOT NULL,
  `ESPB` int(11) NOT NULL,
  `promenljiva` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PredmetID`),
  KEY `Sluzbenik` (`Sluzbenik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `predmet`
--

INSERT INTO `predmet` (`PredmetID`, `Naziv`, `Sluzbenik`, `ESPB`, `promenljiva`) VALUES
(1, '1', 'a', 1, 1),
(2, '2', 'a', 2, 1),
(3, '3', 'a', 3, NULL),
(4, '4', 'a', 4, NULL),
(5, '5', 'a', 5, NULL),
(6, '66', 'a', 6, NULL),
(7, '9', 'a', 6, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prijava`
--

CREATE TABLE IF NOT EXISTS `prijava` (
  `PrijavaID` int(11) NOT NULL,
  `Ocena` int(11) NOT NULL,
  `Status` int(11) NOT NULL,
  `IspitniRok` int(11) NOT NULL,
  `Predmet` int(11) NOT NULL,
  `Student` int(11) NOT NULL,
  `Sluzbenik` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PrijavaID`),
  KEY `PrijavaID` (`PrijavaID`,`Status`,`IspitniRok`,`Predmet`,`Student`,`Sluzbenik`),
  KEY `Status` (`Status`),
  KEY `IspitniRok` (`IspitniRok`),
  KEY `Predmet` (`Predmet`),
  KEY `Student` (`Student`),
  KEY `Sluzbenik` (`Sluzbenik`),
  KEY `Status_2` (`Status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijava`
--

INSERT INTO `prijava` (`PrijavaID`, `Ocena`, `Status`, `IspitniRok`, `Predmet`, `Student`, `Sluzbenik`) VALUES
(4, 0, 5, 4, 1, 2, NULL),
(5, 0, 5, 4, 2, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `JMBG` int(11) NOT NULL,
  `Ime` text NOT NULL,
  `Prezime` text NOT NULL,
  PRIMARY KEY (`JMBG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sluzbenik`
--

CREATE TABLE IF NOT EXISTS `sluzbenik` (
  `KorisnickoIme` varchar(255) NOT NULL,
  `KorisnickaSifra` text NOT NULL,
  `Ime` text NOT NULL,
  `Prezime` text NOT NULL,
  PRIMARY KEY (`KorisnickoIme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sluzbenik`
--

INSERT INTO `sluzbenik` (`KorisnickoIme`, `KorisnickaSifra`, `Ime`, `Prezime`) VALUES
('a', 'a', 'a', 'a'),
('b', 'b', 'b', 'b');

-- --------------------------------------------------------

--
-- Table structure for table `smer`
--

CREATE TABLE IF NOT EXISTS `smer` (
  `SmerID` int(11) NOT NULL,
  `Naziv` text NOT NULL,
  PRIMARY KEY (`SmerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `smer`
--

INSERT INTO `smer` (`SmerID`, `Naziv`) VALUES
(1, 'ISIT'),
(2, 'Menadzment'),
(3, 'kvalitet');

-- --------------------------------------------------------

--
-- Table structure for table `statusprijave`
--

CREATE TABLE IF NOT EXISTS `statusprijave` (
  `StatusID` int(11) NOT NULL,
  `Naziv` text NOT NULL,
  PRIMARY KEY (`StatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statusprijave`
--

INSERT INTO `statusprijave` (`StatusID`, `Naziv`) VALUES
(1, 'Polozio'),
(2, 'Pao'),
(3, 'N.I'),
(4, 'Udaljen'),
(5, 'Prijavljen');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `JMBG` int(11) NOT NULL,
  `Ime` text NOT NULL,
  `Prezime` text NOT NULL,
  `GodinaStudija` int(11) NOT NULL,
  `BrojIndeksa` text NOT NULL,
  `Sluzbenik` varchar(255) NOT NULL,
  `Ulica` text NOT NULL,
  `Broj` text NOT NULL,
  `Mesto` int(11) NOT NULL,
  `Smer` int(11) NOT NULL,
  `Sifra` text NOT NULL,
  PRIMARY KEY (`JMBG`),
  KEY `Sluzbenik` (`Sluzbenik`,`Mesto`,`Smer`),
  KEY `Mesto` (`Mesto`),
  KEY `Smer` (`Smer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`JMBG`, `Ime`, `Prezime`, `GodinaStudija`, `BrojIndeksa`, `Sluzbenik`, `Ulica`, `Broj`, `Mesto`, `Smer`, `Sifra`) VALUES
(1, '1', '1', 1, '1', 'a', '1', '1', 21000, 1, '1'),
(2, '2', '2', 2, '2', 'a', '2', '22', 25000, 1, '2'),
(3, '3', '3', 3, '3', 'a', '3', '3', 11000, 1, '3');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `predaje`
--
ALTER TABLE `predaje`
  ADD CONSTRAINT `predaje_ibfk_1` FOREIGN KEY (`Predmet`) REFERENCES `predmet` (`PredmetID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predaje_ibfk_2` FOREIGN KEY (`Profesor`) REFERENCES `profesor` (`JMBG`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `predaje_ibfk_3` FOREIGN KEY (`Smer`) REFERENCES `smer` (`SmerID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `predmet`
--
ALTER TABLE `predmet`
  ADD CONSTRAINT `predmet_ibfk_1` FOREIGN KEY (`Sluzbenik`) REFERENCES `sluzbenik` (`KorisnickoIme`);

--
-- Constraints for table `prijava`
--
ALTER TABLE `prijava`
  ADD CONSTRAINT `prijava_ibfk_1` FOREIGN KEY (`Status`) REFERENCES `statusprijave` (`StatusID`),
  ADD CONSTRAINT `prijava_ibfk_2` FOREIGN KEY (`IspitniRok`) REFERENCES `ispitnirok` (`IspitniRokID`),
  ADD CONSTRAINT `prijava_ibfk_3` FOREIGN KEY (`Predmet`) REFERENCES `predmet` (`PredmetID`),
  ADD CONSTRAINT `prijava_ibfk_4` FOREIGN KEY (`Student`) REFERENCES `student` (`JMBG`),
  ADD CONSTRAINT `prijava_ibfk_5` FOREIGN KEY (`Sluzbenik`) REFERENCES `sluzbenik` (`KorisnickoIme`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Sluzbenik`) REFERENCES `sluzbenik` (`KorisnickoIme`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`Mesto`) REFERENCES `mesto` (`Ptt`),
  ADD CONSTRAINT `student_ibfk_3` FOREIGN KEY (`Smer`) REFERENCES `smer` (`SmerID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
