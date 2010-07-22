-- phpMyAdmin SQL Dump
-- version 2.11.8.1deb5+lenny4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 22, 2010 at 02:14 PM
-- Server version: 5.0.51
-- PHP Version: 5.3.2-0.dotdeb.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `skripsi_cfws`
--

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE IF NOT EXISTS `document` (
  `id` bigint(20) NOT NULL auto_increment,
  `fileName` varchar(255) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `document`
--


-- --------------------------------------------------------

--
-- Table structure for table `node`
--

CREATE TABLE IF NOT EXISTS `node` (
  `id` bigint(20) NOT NULL auto_increment,
  `words` text NOT NULL,
  `wordLength` int(11) NOT NULL,
  `documentCount` int(11) NOT NULL,
  `parentId` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `parentId` (`parentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `node`
--


-- --------------------------------------------------------

--
-- Table structure for table `node_has_document`
--

CREATE TABLE IF NOT EXISTS `node_has_document` (
  `nodeId` bigint(20) NOT NULL,
  `documentId` bigint(20) NOT NULL,
  KEY `nodeId` (`nodeId`,`documentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `node_has_document`
--

