-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2016 at 04:09 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mavsdiner`
--
CREATE DATABASE IF NOT EXISTS `mavsdiner` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `mavsdiner`;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `food_item_id` int(5) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `restaurant_id` int(5) NOT NULL,
  PRIMARY KEY (`order_id`,`food_item_id`,`customer_id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `food_item_id` (`food_item_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1002 ;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`order_id`, `food_item_id`, `customer_id`, `quantity`, `restaurant_id`) VALUES
(1000, 1, 123, 2, 10011),
(1000, 2, 123, 3, 10011),
(1001, 1, 125, 1, 10011),
(1001, 3, 125, 2, 10011),
(1001, 4, 125, 3, 10011);

-- --------------------------------------------------------

--
-- Table structure for table `fooditem`
--

CREATE TABLE IF NOT EXISTS `fooditem` (
  `food_item_id` int(5) NOT NULL AUTO_INCREMENT,
  `food_item_name` varchar(20) NOT NULL,
  `food_item_price` decimal(4,2) NOT NULL,
  `food_item_description` varchar(50) NOT NULL,
  `restaurant_id` int(5) NOT NULL,
  `availability` tinyint(1) NOT NULL,
  PRIMARY KEY (`food_item_id`),
  KEY `restaurant_id` (`restaurant_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `fooditem`
--

INSERT INTO `fooditem` (`food_item_id`, `food_item_name`, `food_item_price`, `food_item_description`, `restaurant_id`, `availability`) VALUES
(1, 'Pizza', '19.99', 'Cheese Burst', 10011, 1),
(2, 'Burger', '9.99', 'Cheese ', 10011, 1),
(3, 'Coke', '2.99', 'Can', 10011, 1),
(4, 'Pepsi', '5.00', 'Can', 10011, 0),
(12, 'XYZ', '16.00', 'ZBC', 10011, 0);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` int(10) NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Open',
  `rating` decimal(4,2) NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `operating_hours` time NOT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10014 ;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`restaurant_id`, `restaurant_name`, `status`, `rating`, `email_id`, `operating_hours`) VALUES
(10011, 'Pive Five', 'Open', '3.50', 'admin_pf@aramak.com', '13:47:46'),
(10012, 'Texa', 'Closed', '4.00', 'texa@aramak.com', '13:47:47'),
(10013, 'Frozen', 'Closed', '4.00', 'xyz@pqr.com', '00:00:05');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`food_item_id`) REFERENCES `fooditem` (`food_item_id`),
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);

--
-- Constraints for table `fooditem`
--
ALTER TABLE `fooditem`
  ADD CONSTRAINT `fooditem_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
