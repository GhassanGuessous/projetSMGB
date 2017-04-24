-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 24 Avril 2017 à 22:15
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `projetsmgb_bd`
--

-- --------------------------------------------------------

--
-- Structure de la table `action`
--

CREATE TABLE IF NOT EXISTS `action` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `GOAL_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACTION_GOAL_ID` (`GOAL_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- Contenu de la table `action`
--

INSERT INTO `action` (`ID`, `NOM`, `GOAL_ID`) VALUES
(6, 'a', 6),
(7, 'aa', 7),
(8, 'sanata', 8),
(9, 'cvngh', 9),
(10, 'gqshCX', 10),
(11, 'fhcgj', 11),
(12, 'sbshv', 12),
(13, 'gdf', 13),
(14, 'sfgt', 14),
(15, 'sfgt8', 15),
(16, 'z', 16),
(17, 's', 17),
(18, 'nkjdf', 18),
(19, '8', 19),
(20, 'ghj', 20),
(21, '125', 21),
(22, 'xc', 22),
(23, 'p', 23),
(24, 'f', 24),
(25, 'fd', 25),
(26, 'vfr', 26),
(27, 'vfr4', 27),
(28, 'vfr45', 28),
(29, 'nhj', 29),
(30, 'ph', 30),
(31, 'vyh', 31),
(32, 'bjl', 32),
(33, 'vbnuyt', 33),
(34, 'v,ggg', 34),
(35, 'cdeer', 35),
(36, 'dfrc', 36),
(37, 'bjjjli', 37),
(38, 'vyyeee', 38),
(39, 'klouyt', 39),
(40, 'bfggfggf', 40),
(41, 'gdr', 41),
(42, 'hfur', 42),
(43, 'shu', 43),
(44, 'bn', 44),
(45, 'bn', 45),
(46, 'bv', 46),
(47, 'fbvvvv', 47),
(48, 'sddd', 48),
(49, 'sddd78', 49),
(50, 'wvvb', 50),
(51, 'fgdfdr', 51),
(52, 'bbbbbbb', 52),
(53, 'lmpoiuty', 53),
(54, 'rfv', 54),
(55, 'rfv10', 55),
(56, 'sbgg', 56),
(57, 'vdfdfdf', 57),
(58, 'wsqa', 58),
(59, 'xder', 59),
(60, 'jjjjl', 60),
(61, 'xcbc', 61),
(62, 'mpoiu', 62),
(63, 'vrrrrrrr', 63),
(64, 'zess', 64),
(65, 'vcs', 65),
(66, 'frrrrr', 66),
(67, 'dhcbbv', 67),
(68, 'fggg', 68),
(69, 'fghfc', 69),
(70, 'lkh', 70),
(71, 'kpoze', 71),
(72, 'nifle', 72),
(73, 'nke', 73),
(74, 'ggv', 74);

-- --------------------------------------------------------

--
-- Structure de la table `composant`
--

CREATE TABLE IF NOT EXISTS `composant` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CHEMIN` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `DOMAINEASSOCIE_ID` bigint(20) DEFAULT NULL,
  `PROVIDEINTERFACE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMPOSANT_DOMAINEASSOCIE_ID` (`DOMAINEASSOCIE_ID`),
  KEY `FK_COMPOSANT_PROVIDEINTERFACE_ID` (`PROVIDEINTERFACE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `composant`
--

INSERT INTO `composant` (`ID`, `CHEMIN`, `NOM`, `DOMAINEASSOCIE_ID`, `PROVIDEINTERFACE_ID`) VALUES
(1, 'hjbj', 'hhy', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `contexte`
--

CREATE TABLE IF NOT EXISTS `contexte` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `contexteelement`
--

CREATE TABLE IF NOT EXISTS `contexteelement` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `CONTEXTE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTEXTEELEMENT_CONTEXTE_ID` (`CONTEXTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `contrainte`
--

CREATE TABLE IF NOT EXISTS `contrainte` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) NOT NULL,
  `ACTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTRAINTE_ACTION_ID` (`ACTION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Contenu de la table `contrainte`
--

INSERT INTO `contrainte` (`ID`, `NOM`, `ACTION_ID`) VALUES
(2, 'hjlkj', 10),
(3, 'gchhhh', 11),
(4, 'djscn,;:/', 13),
(5, 'ferhd', 15),
(6, 'e', 16),
(7, 'g', 17),
(8, 'CCHGC', 18),
(9, 'n', 19),
(10, 'bh', 20),
(11, 'bhhy', 21),
(12, 'fdrs', 22),
(13, 'gy', 23),
(14, 'z', 24),
(15, 'vbh', 26),
(16, 'vgf', 29),
(17, 'fr', 30),
(18, 'bn,', 31),
(19, 'frt', 31),
(20, 'vjg', 32),
(21, 'cdert', 33),
(22, 'vfrrr', 35),
(23, 'vffrt', 36),
(24, 'tyree', 37),
(25, 'ffrrr', 38),
(26, 'vffrrtthhhh', 39),
(27, 'fretdg', 40),
(28, 'vvvvvvvvvv', 41),
(29, 'ndjhfb', 43),
(30, 'vfr', 44),
(31, 'vbbbbhv', 45),
(32, ' bbb', 46),
(33, 'vbbbbbbb', 47),
(34, 'dsssss', 48),
(35, 'ccxcc', 50),
(36, 'frdedf', 51),
(37, 'gtttt', 52),
(38, 'gytrrrd', 53),
(39, 'xdrt', 54),
(40, 'frgfgv', 56),
(41, 'vdfer', 57),
(42, 'dsert', 58),
(43, 'nbj', 59),
(44, 'ccccgh', 60),
(45, 'bbbbj', 61),
(46, 'vvy', 62),
(47, 'dxdsz', 64),
(48, 'xssd', 65),
(49, 'wxx', 66),
(50, 'vxxc', 67),
(51, 'ghbn', 68),
(52, 'freii', 69),
(53, 'tyyuhb', 70),
(54, 'mugiu', 71),
(55, 'ugm', 72),
(56, 'lk', 73),
(57, 'nbvv', 74);

-- --------------------------------------------------------

--
-- Structure de la table `contrainteitem`
--

CREATE TABLE IF NOT EXISTS `contrainteitem` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ATTRIBUT` varchar(255) DEFAULT NULL,
  `CRITERE` varchar(255) DEFAULT NULL,
  `CONTRAINTE_ID` bigint(20) DEFAULT NULL,
  `STEP_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CONTRAINTEITEM_STEP_ID` (`STEP_ID`),
  KEY `FK_CONTRAINTEITEM_CONTRAINTE_ID` (`CONTRAINTE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Contenu de la table `contrainteitem`
--

INSERT INTO `contrainteitem` (`ID`, `ATTRIBUT`, `CRITERE`, `CONTRAINTE_ID`, `STEP_ID`) VALUES
(1, NULL, NULL, 26, 30),
(2, NULL, NULL, 26, 30),
(3, NULL, NULL, 26, 30),
(4, NULL, NULL, 26, 30),
(5, NULL, NULL, 27, 34),
(6, NULL, NULL, 28, 35),
(7, NULL, NULL, 29, 36),
(8, 'ddr', 'inferieur', 39, 46),
(9, '', '', 40, 47),
(10, '', '', 41, 48),
(11, '', '', 42, 49),
(12, '', '', 43, 50),
(13, '', '', 44, 51),
(14, NULL, NULL, 45, 52),
(15, NULL, NULL, 46, 53),
(16, '', '', 47, 54),
(17, NULL, NULL, 49, 58),
(18, NULL, NULL, 50, 59),
(19, NULL, NULL, 51, 60),
(20, NULL, NULL, 52, 61),
(21, 'komh', 'inferieur', 53, 62),
(22, 'dsqd', 'egal', 53, 62),
(23, 'jimosd', 'inferieur', 54, 63),
(24, 'jomoze', 'superieur', 54, 63),
(25, 'nmkj', 'egal', 56, 67),
(26, 'n:kjdf', 'inferieur', 56, 67),
(27, 'bnn', 'inferieur', 57, 68);

-- --------------------------------------------------------

--
-- Structure de la table `domaine`
--

CREATE TABLE IF NOT EXISTS `domaine` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `domaine`
--

INSERT INTO `domaine` (`ID`, `NOM`) VALUES
(1, 'tourisme');

-- --------------------------------------------------------

--
-- Structure de la table `domaineassocie`
--

CREATE TABLE IF NOT EXISTS `domaineassocie` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `DOMAINE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOMAINEASSOCIE_DOMAINE_ID` (`DOMAINE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `domaineassocie`
--

INSERT INTO `domaineassocie` (`ID`, `NOM`, `TYPE`, `DOMAINE_ID`) VALUES
(1, 'ds1', 2, 1),
(2, 'ghj', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `goal`
--

CREATE TABLE IF NOT EXISTS `goal` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `DOMAINE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_GOAL_DOMAINE_ID` (`DOMAINE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- Contenu de la table `goal`
--

INSERT INTO `goal` (`ID`, `NOM`, `DOMAINE_ID`) VALUES
(6, 'g', 1),
(7, 'gg', 1),
(8, 'lah', 1),
(9, 'vhg,n', 1),
(10, 'dhgsv', 1),
(11, 'fgj', 1),
(12, 'bshdb', 1),
(13, 'fghdj', 1),
(14, 'mls', 1),
(15, 'mls2', 1),
(16, 'x', 1),
(17, 'm', 1),
(18, 'hgiu', 1),
(19, 'j', 1),
(20, 'g5', 1),
(21, 'lm', 1),
(22, 'frgg', 1),
(23, 'h', 1),
(24, 'b', 1),
(25, 'bn', 1),
(26, 'hpo', 1),
(27, 'hpo2', 1),
(28, 'hpo21', 1),
(29, 'hy', 1),
(30, 'l', 1),
(31, 'hu', 1),
(32, 'jk', 1),
(33, 'uyt', 1),
(34, 'drtjk', 1),
(35, 'frt', 1),
(36, 'dddddd', 1),
(37, 'vvvvvvvvgh', 1),
(38, 'bujiiiiiiii', 1),
(39, 'vgtre', 1),
(40, 'oiuouiui', 1),
(41, 'ttttttt', 1),
(42, 'hgu', 1),
(43, 'fnhue', 1),
(44, 'vgh', 1),
(45, 'bbh', 1),
(46, 'pm', 1),
(47, 'hhhf', 1),
(48, 'dfcds', 1),
(49, 'dfcds15', 1),
(50, 'wbh', 1),
(51, 'frdegffgc', 1),
(52, 'bhhhhhh', 1),
(53, 'grtttt', 1),
(54, 'vfr', 1),
(55, 'vfr8', 1),
(56, 'adefs', 1),
(57, 'cxfdr', 1),
(58, 'bgfd', 1),
(59, 'bvcf', 1),
(60, 'bnk', 1),
(61, 'cvgf', 1),
(62, 'vggggu', 1),
(63, 'dddddddd', 1),
(64, 'esze', 1),
(65, 'njk', 1),
(66, 'vgf', 1),
(67, 'vbchf', 1),
(68, 'dffg', 1),
(69, 'cgh', 1),
(70, 'kj', 1),
(71, 'ùàisq', 1),
(72, 'jrmeih', 1),
(73, 'nmfdhn', 1),
(74, 'hbj', 1);

-- --------------------------------------------------------

--
-- Structure de la table `input`
--

CREATE TABLE IF NOT EXISTS `input` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `PROVIDEINTERFACEITEM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INPUT_PROVIDEINTERFACEITEM_ID` (`PROVIDEINTERFACEITEM_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `input`
--

INSERT INTO `input` (`ID`, `NOM`, `TYPE`, `PROVIDEINTERFACEITEM_ID`) VALUES
(1, 'nkjn', 'vghbhj', 1);

-- --------------------------------------------------------

--
-- Structure de la table `output`
--

CREATE TABLE IF NOT EXISTS `output` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `PROVIDEINTERFACEITEM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_OUTPUT_PROVIDEINTERFACEITEM_ID` (`PROVIDEINTERFACEITEM_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `output`
--

INSERT INTO `output` (`ID`, `NOM`, `TYPE`, `PROVIDEINTERFACEITEM_ID`) VALUES
(1, 'o', 't', 1);

-- --------------------------------------------------------

--
-- Structure de la table `processus`
--

CREATE TABLE IF NOT EXISTS `processus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `PROCESSUSENUM` int(11) DEFAULT NULL,
  `ACTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROCESSUS_ACTION_ID` (`ACTION_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=68 ;

--
-- Contenu de la table `processus`
--

INSERT INTO `processus` (`ID`, `NOM`, `PROCESSUSENUM`, `ACTION_ID`) VALUES
(6, 'p', NULL, 6),
(7, 'pp', NULL, 7),
(8, 'sanogo', NULL, 8),
(9, 'dtrf', NULL, 9),
(10, 'gqschwx', NULL, 10),
(11, 'gvhj', NULL, 11),
(12, 'ndsjqlk', NULL, 13),
(13, 'dn,sx nw,', NULL, 13),
(14, '', NULL, 15),
(15, 'dgfj', NULL, 15),
(16, 'y', NULL, 16),
(17, 'i', NULL, 17),
(18, 'P', NULL, 18),
(19, 'il', NULL, 19),
(20, 'ghk', NULL, 20),
(21, '1587', NULL, 21),
(22, 'xvg', NULL, 22),
(23, 'hl', NULL, 23),
(24, 'd', NULL, 24),
(25, 'ert', NULL, 26),
(26, 'gtt', NULL, 29),
(27, 'ze', NULL, 30),
(28, 'nu', NULL, 31),
(29, 'nj', NULL, 32),
(30, 'vfdd', NULL, 33),
(31, 'vvgtt', NULL, 35),
(32, 'bgyjjj', NULL, 36),
(33, 'buuomlk', NULL, 37),
(34, 'bgtt', NULL, 38),
(35, 'vvvff', NULL, 39),
(36, 'bghtyy', NULL, 40),
(37, 'fgdddddd', NULL, 41),
(38, 'ndbgrt', NULL, 43),
(39, ' bbb', NULL, 44),
(40, 'bbbbby', NULL, 45),
(41, 'bh', NULL, 46),
(42, 'cbn', NULL, 47),
(43, 'dcds', NULL, 48),
(44, 'vwvcf', NULL, 50),
(45, 'frtdgfr', NULL, 51),
(46, 'bbbbbb', NULL, 52),
(47, 'ertuyty', NULL, 53),
(48, 'fvr', NULL, 54),
(49, 'cdes', NULL, 56),
(50, 'vdfer', NULL, 57),
(51, 'vfres', NULL, 58),
(52, 'klop', NULL, 59),
(53, 'cdrt', NULL, 60),
(54, 'vbghg', NULL, 61),
(55, 'fflmpoi', NULL, 62),
(56, 'ddree', NULL, 64),
(57, 'xwq', NULL, 65),
(58, 'fr', NULL, 66),
(59, 'bcvcvx', NULL, 67),
(60, 'nhjk', NULL, 68),
(61, 'fhhy', NULL, 69),
(62, 'ihb', NULL, 70),
(63, 'joids', NULL, 71),
(64, 'opez', NULL, 72),
(65, 'fsa', NULL, 72),
(66, 'nier', NULL, 73),
(67, 'bgyg', NULL, 74);

-- --------------------------------------------------------

--
-- Structure de la table `provideinterface`
--

CREATE TABLE IF NOT EXISTS `provideinterface` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `provideinterface`
--

INSERT INTO `provideinterface` (`ID`, `NOM`) VALUES
(1, 'hhy');

-- --------------------------------------------------------

--
-- Structure de la table `provideinterfaceitem`
--

CREATE TABLE IF NOT EXISTS `provideinterfaceitem` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `PROVIDEINTERFACE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROVIDEINTERFACEITEM_PROVIDEINTERFACE_ID` (`PROVIDEINTERFACE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `provideinterfaceitem`
--

INSERT INTO `provideinterfaceitem` (`ID`, `NOM`, `PROVIDEINTERFACE_ID`) VALUES
(1, 'pii', 1);

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

CREATE TABLE IF NOT EXISTS `resultat` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RESULTAT_ACTION_ID` (`ACTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `resultatitem`
--

CREATE TABLE IF NOT EXISTS `resultatitem` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VALEUR` varchar(255) DEFAULT NULL,
  `RESULTAT_ID` bigint(20) DEFAULT NULL,
  `STEP_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RESULTATITEM_RESULTAT_ID` (`RESULTAT_ID`),
  KEY `FK_RESULTATITEM_STEP_ID` (`STEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sensibleparam`
--

CREATE TABLE IF NOT EXISTS `sensibleparam` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `CONTEXTEELEMENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SENSIBLEPARAM_CONTEXTEELEMENT_ID` (`CONTEXTEELEMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `sensiblevaleur`
--

CREATE TABLE IF NOT EXISTS `sensiblevaleur` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `SENSIBLEPARAM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SENSIBLEVALEUR_SENSIBLEPARAM_ID` (`SENSIBLEPARAM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `step`
--

CREATE TABLE IF NOT EXISTS `step` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `STEPENUM` int(11) DEFAULT NULL,
  `PROCESSUS_ID` bigint(20) DEFAULT NULL,
  `PROVIDEINTERFACEITEM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_STEP_PROCESSUS_ID` (`PROCESSUS_ID`),
  KEY `FK_STEP_PROVIDEINTERFACEITEM_ID` (`PROVIDEINTERFACEITEM_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Contenu de la table `step`
--

INSERT INTO `step` (`ID`, `NOM`, `STEPENUM`, `PROCESSUS_ID`, `PROVIDEINTERFACEITEM_ID`) VALUES
(7, 'sss', NULL, 7, 1),
(8, 'jdklkjk', NULL, 8, 1),
(9, 'dtrhg', NULL, 9, 1),
(10, 'gqsswbn', NULL, 10, 1),
(11, 'dxbv', NULL, 11, 1),
(12, 'jdbssn', NULL, 12, 1),
(13, 'dnjj', NULL, 15, 1),
(14, 'r', NULL, 16, 1),
(15, 'g', NULL, 17, 1),
(16, 'vhgv', NULL, 18, 1),
(17, 'gh', NULL, 19, 1),
(18, 'bhg', NULL, 20, 1),
(19, 'gdtrv', NULL, 21, 1),
(20, 'bhbdv', NULL, 22, 1),
(21, 'io', NULL, 23, 1),
(22, 'q', NULL, 24, 1),
(23, 'hjik', NULL, 25, 1),
(24, 'bgg', NULL, 26, 1),
(25, 'drt', NULL, 27, 1),
(26, 'vbhj', NULL, 28, 1),
(27, 'leee', NULL, 29, 1),
(28, 'xseer', NULL, 30, 1),
(29, 'xdee', NULL, 31, 1),
(30, 'dvvvdfg', NULL, 32, 1),
(31, 'hvbjl', NULL, 33, 1),
(32, 'cfrrrr', NULL, 34, 1),
(33, 'cfrrrr', NULL, 35, 1),
(34, 'hguuyu', NULL, 36, 1),
(35, 'fdddddddd', NULL, 37, 1),
(36, 'heru', NULL, 38, 1),
(37, 'fghk', NULL, 39, 1),
(38, 'cgf', NULL, 40, 1),
(39, 'bvv', NULL, 41, 1),
(40, 'vbcnn', NULL, 42, 1),
(41, 'sssssss', NULL, 43, 1),
(42, 'wlkj', NULL, 44, 1),
(43, 'gdggdst', NULL, 45, 1),
(44, 'jjjjjjjjjj', NULL, 46, 1),
(45, 'vbgtre', NULL, 47, 1),
(46, 'frv', NULL, 48, 1),
(47, 'sdewc', NULL, 49, 1),
(48, 'vdfr', NULL, 50, 1),
(49, 'cvfre', NULL, 51, 1),
(50, 'vbgg', NULL, 52, 1),
(51, 'gggg', NULL, 53, 1),
(52, 'ghk', NULL, 54, 1),
(53, 'hhhhkb', NULL, 55, 1),
(54, 'seezzd', NULL, 56, 1),
(55, 'sdessz', NULL, 56, 1),
(56, 'deess', NULL, 56, 1),
(57, 'gtre', NULL, 57, 1),
(58, 'cvs', NULL, 58, 1),
(59, 'vxfsffd', NULL, 59, 1),
(60, 'vghgn', NULL, 60, 1),
(61, 'vvvjv', NULL, 61, 1),
(62, 'hgvk', NULL, 62, 1),
(63, 'blued', NULL, 63, 1),
(64, 'blcdjb', NULL, 64, 1),
(65, 'gkdsu', NULL, 64, 1),
(66, 'fz', NULL, 65, 1),
(67, 'blhby', NULL, 66, 1),
(68, 'gygh', NULL, 67, 1);

-- --------------------------------------------------------

--
-- Structure de la table `valeurcritique`
--

CREATE TABLE IF NOT EXISTS `valeurcritique` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(255) DEFAULT NULL,
  `VALEUR` varchar(255) DEFAULT NULL,
  `CONTRAINTEITEM_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VALEURCRITIQUE_CONTRAINTEITEM_ID` (`CONTRAINTEITEM_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Contenu de la table `valeurcritique`
--

INSERT INTO `valeurcritique` (`ID`, `TYPE`, `VALEUR`, `CONTRAINTEITEM_ID`) VALUES
(1, 'sbvx wVN?', 'gdhj', NULL),
(2, 'dfb', 'fhhhhh', NULL),
(3, 'gsqgvx', 'hddj', NULL),
(4, 'vcbng', 'ddrtf', NULL),
(5, 'dvhqsj', 'nsd,', NULL),
(6, 'dvfg', 'dhv', NULL),
(7, 'f', 'e', NULL),
(8, 'p', 'l', NULL),
(9, 'bjhv', 'g,hfvj', NULL),
(10, 'hbn', 'hj', NULL),
(11, 'vvh', 'bj ghg', NULL),
(12, 'xde', 'hfgt', NULL),
(13, 'iduy', 'cde', NULL),
(14, 'kl', '4', NULL),
(15, 'c', '7', NULL),
(16, 'hyt', 'de', NULL),
(17, 'kjh', 'vf', NULL),
(18, 'gy', 'ct', NULL),
(19, 'bgyt', 'bgt', NULL),
(20, 'vj', 'vgj', NULL),
(21, 'gfdd', 'cfr', NULL),
(22, 'bhhg', 'xseet', NULL),
(23, 'bbggtt', 'cdddff', NULL),
(24, 'kuyhbv', 'vfrrrrrrrrrrrrrr', NULL),
(25, 'hhyyy', 'vfrrr', NULL),
(26, 'liiuuy', 'vfrrrrri', NULL),
(27, 'sjhdgte', 'xcdfrz', NULL),
(28, 'fdddddd', 'ttrrrrrr', NULL),
(29, 'qgt', 'qbh', NULL),
(30, 'vvccd', 'bgf', NULL),
(31, 'bn', 'bj', NULL),
(32, 'cxw', 'bvvv', NULL),
(33, 'vb,  bbb', 'nnnnnnj', NULL),
(34, 'xxs', 'sdd', NULL),
(35, 'sggyd', 'cdss', NULL),
(36, 'dcxcdd', 'cdfdr', NULL),
(37, 'kkkkkk', 'hhhhh', NULL),
(38, 'bbbf', 'vfrrr', NULL),
(39, 'xdd', 'ser', NULL),
(40, 'dccdc', 'gbbb', NULL),
(41, 'dget', 'vcfdf', NULL),
(42, 'sssrh', 'cvfde', NULL),
(43, 'bbb', 'hhh', NULL),
(44, 'cxwszs', 'vvffffx', NULL),
(45, 'vvhvg', 'jjjhgg', NULL),
(46, 'bjbug', 'njjinb', NULL),
(47, 'sanogo', 'lah', NULL),
(48, 'dxw', 'ssw', NULL),
(49, 'cxdwffw', 'xcfwhxbxv', NULL),
(50, 'dfgb', 'cbfg', NULL),
(51, 'poiuyt', 'gtrrf', NULL),
(52, 'iugliug', 'huhbhj', 21),
(53, 'FKUYF', 'hbukg', 21),
(54, 'ne', 'jiomez', 23),
(55, 'nvfdk:', 'nludfi', 23),
(56, ';lmd', 'korto', 25),
(57, 'nkdflj', 'nlkf', 25),
(58, 'bn', 'vbn', 27);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `action`
--
ALTER TABLE `action`
  ADD CONSTRAINT `FK_ACTION_GOAL_ID` FOREIGN KEY (`GOAL_ID`) REFERENCES `goal` (`ID`);

--
-- Contraintes pour la table `composant`
--
ALTER TABLE `composant`
  ADD CONSTRAINT `FK_COMPOSANT_DOMAINEASSOCIE_ID` FOREIGN KEY (`DOMAINEASSOCIE_ID`) REFERENCES `domaineassocie` (`ID`),
  ADD CONSTRAINT `FK_COMPOSANT_PROVIDEINTERFACE_ID` FOREIGN KEY (`PROVIDEINTERFACE_ID`) REFERENCES `provideinterface` (`ID`);

--
-- Contraintes pour la table `contexteelement`
--
ALTER TABLE `contexteelement`
  ADD CONSTRAINT `FK_CONTEXTEELEMENT_CONTEXTE_ID` FOREIGN KEY (`CONTEXTE_ID`) REFERENCES `contexte` (`ID`);

--
-- Contraintes pour la table `contrainte`
--
ALTER TABLE `contrainte`
  ADD CONSTRAINT `FK_CONTRAINTE_ACTION_ID` FOREIGN KEY (`ACTION_ID`) REFERENCES `action` (`ID`);

--
-- Contraintes pour la table `contrainteitem`
--
ALTER TABLE `contrainteitem`
  ADD CONSTRAINT `FK_CONTRAINTEITEM_CONTRAINTE_ID` FOREIGN KEY (`CONTRAINTE_ID`) REFERENCES `contrainte` (`ID`),
  ADD CONSTRAINT `FK_CONTRAINTEITEM_STEP_ID` FOREIGN KEY (`STEP_ID`) REFERENCES `step` (`ID`);

--
-- Contraintes pour la table `domaineassocie`
--
ALTER TABLE `domaineassocie`
  ADD CONSTRAINT `FK_DOMAINEASSOCIE_DOMAINE_ID` FOREIGN KEY (`DOMAINE_ID`) REFERENCES `domaine` (`ID`);

--
-- Contraintes pour la table `goal`
--
ALTER TABLE `goal`
  ADD CONSTRAINT `FK_GOAL_DOMAINE_ID` FOREIGN KEY (`DOMAINE_ID`) REFERENCES `domaine` (`ID`);

--
-- Contraintes pour la table `input`
--
ALTER TABLE `input`
  ADD CONSTRAINT `FK_INPUT_PROVIDEINTERFACEITEM_ID` FOREIGN KEY (`PROVIDEINTERFACEITEM_ID`) REFERENCES `provideinterfaceitem` (`ID`);

--
-- Contraintes pour la table `output`
--
ALTER TABLE `output`
  ADD CONSTRAINT `FK_OUTPUT_PROVIDEINTERFACEITEM_ID` FOREIGN KEY (`PROVIDEINTERFACEITEM_ID`) REFERENCES `provideinterfaceitem` (`ID`);

--
-- Contraintes pour la table `processus`
--
ALTER TABLE `processus`
  ADD CONSTRAINT `FK_PROCESSUS_ACTION_ID` FOREIGN KEY (`ACTION_ID`) REFERENCES `action` (`ID`);

--
-- Contraintes pour la table `provideinterfaceitem`
--
ALTER TABLE `provideinterfaceitem`
  ADD CONSTRAINT `FK_PROVIDEINTERFACEITEM_PROVIDEINTERFACE_ID` FOREIGN KEY (`PROVIDEINTERFACE_ID`) REFERENCES `provideinterface` (`ID`);

--
-- Contraintes pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `FK_RESULTAT_ACTION_ID` FOREIGN KEY (`ACTION_ID`) REFERENCES `action` (`ID`);

--
-- Contraintes pour la table `resultatitem`
--
ALTER TABLE `resultatitem`
  ADD CONSTRAINT `FK_RESULTATITEM_RESULTAT_ID` FOREIGN KEY (`RESULTAT_ID`) REFERENCES `resultat` (`ID`),
  ADD CONSTRAINT `FK_RESULTATITEM_STEP_ID` FOREIGN KEY (`STEP_ID`) REFERENCES `step` (`ID`);

--
-- Contraintes pour la table `sensibleparam`
--
ALTER TABLE `sensibleparam`
  ADD CONSTRAINT `FK_SENSIBLEPARAM_CONTEXTEELEMENT_ID` FOREIGN KEY (`CONTEXTEELEMENT_ID`) REFERENCES `contexteelement` (`ID`);

--
-- Contraintes pour la table `sensiblevaleur`
--
ALTER TABLE `sensiblevaleur`
  ADD CONSTRAINT `FK_SENSIBLEVALEUR_SENSIBLEPARAM_ID` FOREIGN KEY (`SENSIBLEPARAM_ID`) REFERENCES `sensibleparam` (`ID`);

--
-- Contraintes pour la table `step`
--
ALTER TABLE `step`
  ADD CONSTRAINT `FK_STEP_PROCESSUS_ID` FOREIGN KEY (`PROCESSUS_ID`) REFERENCES `processus` (`ID`),
  ADD CONSTRAINT `FK_STEP_PROVIDEINTERFACEITEM_ID` FOREIGN KEY (`PROVIDEINTERFACEITEM_ID`) REFERENCES `provideinterfaceitem` (`ID`);

--
-- Contraintes pour la table `valeurcritique`
--
ALTER TABLE `valeurcritique`
  ADD CONSTRAINT `FK_VALEURCRITIQUE_CONTRAINTEITEM_ID` FOREIGN KEY (`CONTRAINTEITEM_ID`) REFERENCES `contrainteitem` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
