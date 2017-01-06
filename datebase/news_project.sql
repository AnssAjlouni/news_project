-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2017 at 08:24 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `news_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `news_id` int(10) NOT NULL COMMENT 'رقم الخبر',
  `news_title` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'عنوان الخبر',
  `news_text` varchar(1000) COLLATE utf8_unicode_ci NOT NULL COMMENT 'نص الخبر',
  `news_img` varchar(5000) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL COMMENT 'صورة الخبر',
  `section_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`news_id`, `news_title`, `news_text`, `news_img`, `section_id`, `user_id`) VALUES
(1, 'كريستيانو رونالدو يحصل على جائزة الكرة الذهبية لهذا العام', 'حصل كريستيانو رونالدو على الكرة الذهبية لهذا العام 2016-2017 للمرة الرابعة بتاريخه ليصقلص الفارق مع منافسه ليونيل ميسي بفارق كرة واحدة لان ميسي حصل على 5 كرات ذهبية ', 'a.jpg', 1, 1),
(2, 'مرحبا بكم', 'hello', 'b.jpg', 1, 1),
(3, 'anas ajlouni', 'hello', 'c.png', 1, 1),
(4, 'حصل حادث سير', 'حصل حادث سير في فلسطين في مدينة الخليل', 'd.jpg', 1, 1),
(5, 'شركة google تشتري سيرفرات ضخمة', 'قامت شركة google بشراء سيرفرات ضخمة ', 'e.jpg', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`news_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `news_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'رقم الخبر', AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
