-- phpMyAdmin SQL Dump
-- version 2.11.11.3
-- http://www.phpmyadmin.net
--
-- Host: omega.uta.edu
-- Generation Time: Apr 16, 2016 at 08:12 PM
-- Server version: 5.0.95
-- PHP Version: 5.1.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sxr5833`
--

-- --------------------------------------------------------

--
-- Table structure for table `Cart`
--

CREATE TABLE `Cart` (
  `restaurant_id` int(5) NOT NULL,
  `order_id` int(11) NOT NULL auto_increment,
  `food_item_id` int(11) NOT NULL,
  `customer_id` int(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY  (`order_id`,`food_item_id`,`customer_id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1002 ;

--
-- Dumping data for table `Cart`
--

INSERT INTO `Cart` (`restaurant_id`, `order_id`, `food_item_id`, `customer_id`, `quantity`) VALUES(10011, 1000, 1, 123, 2);
INSERT INTO `Cart` (`restaurant_id`, `order_id`, `food_item_id`, `customer_id`, `quantity`) VALUES(10011, 1000, 2, 123, 3);
INSERT INTO `Cart` (`restaurant_id`, `order_id`, `food_item_id`, `customer_id`, `quantity`) VALUES(10011, 1001, 1, 125, 1);
INSERT INTO `Cart` (`restaurant_id`, `order_id`, `food_item_id`, `customer_id`, `quantity`) VALUES(10011, 1001, 3, 125, 2);
INSERT INTO `Cart` (`restaurant_id`, `order_id`, `food_item_id`, `customer_id`, `quantity`) VALUES(10011, 1001, 4, 125, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `street_number` varchar(30) default NULL,
  `street_name` varchar(100) default NULL,
  `city` varchar(30) default NULL,
  `state` varchar(30) default NULL,
  `country` varchar(30) default NULL,
  `zip_code` int(11) default NULL,
  `password` varchar(50) NOT NULL,
  `customer_id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`customer_id`),
  UNIQUE KEY `email_id` (`email_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`first_name`, `last_name`, `email_id`, `street_number`, `street_name`, `city`, `state`, `country`, `zip_code`, `password`, `customer_id`) VALUES('Swaroop', 'Raja Srinivas Setty', 'swa@gmail.com', '700', 'Mitchell Cir', 'Arlington', 'Tx', 'USA', 7603, 'swa123', 1);
INSERT INTO `Customer` (`first_name`, `last_name`, `email_id`, `street_number`, `street_name`, `city`, `state`, `country`, `zip_code`, `password`, `customer_id`) VALUES('test', 'user', 'test@gmail.com', '700', 'W Mitchell Cir', 'Arlington', 'Tx', 'US', 76013, 'test', 5);

-- --------------------------------------------------------

--
-- Table structure for table `Food_Item`
--

CREATE TABLE `Food_Item` (
  `food_item_id` int(11) NOT NULL auto_increment,
  `food_item_name` varchar(30) NOT NULL,
  `food_item_price` float NOT NULL,
  `food_item_description` varchar(100) default NULL,
  `restaurant_id` int(11) NOT NULL,
  `availability` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`food_item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Food_Item`
--

INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(1, 'Pizza', 19.99, 'Cheese Burst', 10011, 1);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(2, 'Burger', 9.99, 'Cheese ', 10011, 1);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(3, 'Coke', 2.99, 'Can', 10011, 0);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(4, 'mark', 12, 'mark omega update', 10012, 0);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(5, 'xyz', 99.99, 'abc', 10012, 0);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(6, 'Omega Add', 99.99, 'Add functionality', 10011, 1);
INSERT INTO `Food_Item` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES(7, 'Integrate', 99.99, 'Integrate add then modified', 10011, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL,
  `order_status` tinyint(1) NOT NULL default '0',
  `checkout_flag` tinyint(1) NOT NULL default '0',
  `restaurant_id` int(10) NOT NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`order_id`, `order_status`, `checkout_flag`, `restaurant_id`) VALUES(1000, 0, 0, 10011);
INSERT INTO `Orders` (`order_id`, `order_status`, `checkout_flag`, `restaurant_id`) VALUES(1001, 0, 0, 10011);

-- --------------------------------------------------------

--
-- Table structure for table `Restaurant`
--

CREATE TABLE `Restaurant` (
  `restaurant_name` varchar(30) NOT NULL,
  `rating` float default NULL,
  `operating_hours` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `restaurant_id` int(11) NOT NULL auto_increment,
  `street_number` int(11) NOT NULL,
  `street_name` varchar(100) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `zip_code` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY  (`restaurant_id`),
  UNIQUE KEY `email_id` (`email_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10014 ;

--
-- Dumping data for table `Restaurant`
--

INSERT INTO `Restaurant` (`restaurant_name`, `rating`, `operating_hours`, `status`, `email_id`, `restaurant_id`, `street_number`, `street_name`, `city`, `state`, `country`, `zip_code`, `password`) VALUES('Vendor', NULL, '123', 'Open', 'vendor@aramark.com', 10011, 1234, 'abc street', 'NYC', 'NY', 'USA', 761111, '123');
INSERT INTO `Restaurant` (`restaurant_name`, `rating`, `operating_hours`, `status`, `email_id`, `restaurant_id`, `street_number`, `street_name`, `city`, `state`, `country`, `zip_code`, `password`) VALUES('Chick-Fill-A', 4, '9am to 6pm', 'open', 'chick@aramark.com', 10012, 100, 'UC drive', 'Arlington', 'Texas', 'United States', 76013, 'aaa');
INSERT INTO `Restaurant` (`restaurant_name`, `rating`, `operating_hours`, `status`, `email_id`, `restaurant_id`, `street_number`, `street_name`, `city`, `state`, `country`, `zip_code`, `password`) VALUES('Subway', 5, '24 hours', 'open', 'panda@aramark.com', 10013, 101, 'UC DRIVE', 'Arlington', 'Texas', 'United States', 76013, 'bbb');

-- --------------------------------------------------------

--
-- Table structure for table `table1`
--

CREATE TABLE `table1` (
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Role` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `table1`
--

INSERT INTO `table1` (`Username`, `Password`, `Role`) VALUES('swaroop', 'swaroop', 'admin');
