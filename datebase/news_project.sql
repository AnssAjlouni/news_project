-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2017 at 06:17 PM
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
(1, 'كريستيانو رونالدو يحصل على جائزة الكرة الذهبية لهذا العام', 'حصل كريستيانو رونالدو على الكرة الذهبية لهذا العام 2016-2017 للمرة الرابعة بتاريخه ليصقلص الفارق مع منافسه ليونيل ميسي بفارق كرة واحدة لان ميسي حصل على 5 كرات ذهبية ', 'd.jpg', 1, 1),
(3, ' زيدان: بيكيه؟ لا اريد ان ادخل في ورطة', 'ظهر زين الدين زيدان مدرب ريال مدريد في مؤتمر صحفي يخص مواجهة اشبيلية مساء الخميس في اياب دور الـ 16 من بطولة كاس ملك اسبانيا، وحول تصريحات بيكيه وغياب لاعبي البارسا، فضل الفني الفرنسي تجنب التصريح كي لا يدخل في ورطة كما قال. ', 'z.jpg', 1, 1),
(4, 'حصل حادث سير', 'حصل حادث سير في فلسطين في مدينة الخليل', 'a.jpg', 2, 1),
(5, 'شركة google تشتري سيرفرات ضخمة', 'قامت شركة google بشراء سيرفرات ضخمة ', 'e.jpg', 2, 1),
(6, 'انييستا اعتذر للاعبي الريال !', 'اندريس انييستا كان قد ظهر في فيديو مسجل في حفل الفيفا يعتذر فيه عن عدم حضوره مع زملائه في نادي برشلونة بسبب التركيز على المباراة المهمة ضد بيلباو، وتقول صحيفة الماركا الاسبانية ان كابتن البارسا ايضا اعتذر شخصيا من لاعبي الريال عن عدم التواجد معهم في هذا الحدث المهم.', 'inesta.jpg', 1, 1),
(7, 'مر مجنون على عابد يناجي ربه وهو يبكي والدموع منهمرة على خديه وهو يقول :', 'مر مجنون على عابد يناجي ربه وهو يبكي والدموع منهمرة على خديه وهو يقول :\nربي لا تدخلني النار فارحمني وارفق بي يارحيم يارحمن لاتعذبني بالنار اني ضعيف فلاقوة لي على تحمل النار فارحمني ،وجلدي رقيق لايستطيع تحمل حرارة النار فارحمني ،وعظمي دقيق لا يقوى على شدة النار فارحمني .\nضحك المجنون بصوت مرتفع ،فالتفت اليه العابد قائلا :\nماذا يضحكك ايها المجنون ؟؟\nقال المجنون كلامك اضحكني \nفرد العابد :وماذا يضحكك في ؟\nقال المجنون لانك تبكي خوفا من النار ..\nقال العابد وانت الا تخاف من النار ؟\nقال المجنون لا لا اخاف من النار \nضحك العابد وقال :صحيح انك مجنون \nقال المجنون :كيف تخاف ايها العابد وعندك رب رحيم ،رحمته وسعت كل شيء ؟\nقال العابد :ان علي ذنوبا لو يؤاخذني الله بعدله لادخلني النار ،واني لابكي كي يرحمني ويغفر لي ولا يحاسبني بعدله بل بفضله ولطفه ورحمته حتى لا ادخل النار !!', 'ff.jpg', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE `section` (
  `section_id` int(10) NOT NULL,
  `section_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `section_icon` varchar(5000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `section`
--

INSERT INTO `section` (`section_id`, `section_name`, `section_icon`) VALUES
(1, 'أخبار رياضية', 'a.jpg'),
(2, 'أخبار سياسية', 'b.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(10) NOT NULL,
  `user_name` text COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `img_url` varchar(5000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `email`, `password`, `img_url`) VALUES
(1, 'anas ajlouni', 'anss.ajlony1995@gmail.com', '123', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`news_id`);

--
-- Indexes for table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`section_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `news_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'رقم الخبر', AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `section`
--
ALTER TABLE `section`
  MODIFY `section_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
