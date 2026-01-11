-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 11, 2026 at 10:14 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `todo_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `task_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ktjq1slw0ldkuy5rx8fbte2p` (`task_id`),
  KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `created_at`, `message`, `task_id`, `user_id`) VALUES
(3, '2026-01-11 20:56:42.063855', 'Tomorrow: \"task4\" is due', 4, 5),
(4, '2026-01-11 20:56:42.132670', 'OVERDUE: \"task5\" was due on 2026-01-10', 5, 5),
(10, '2026-01-11 22:10:00.054373', 'OVERDUE: \"Task1\" was due on 2026-01-07', 6, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE IF NOT EXISTS `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `due_date` date DEFAULT NULL,
  `priority` enum('HIGH','LOW','MEDIUM') COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` enum('COMPLETED','PENDING') COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `category`, `description`, `due_date`, `priority`, `status`, `title`, `user_id`, `created_at`, `updated_at`) VALUES
(1, 'idk', NULL, '2026-12-01', 'MEDIUM', 'COMPLETED', 'idk', 5, '2026-01-11 15:03:12.414322', '2026-01-11 15:23:31.008142'),
(2, 'task2.1', NULL, '2026-11-01', 'HIGH', 'COMPLETED', 'task2', 5, '2026-01-11 15:10:45.049973', '2026-01-11 15:23:32.131537'),
(3, 'task3', NULL, '2026-02-03', 'MEDIUM', 'COMPLETED', 'task3', 5, '2026-01-11 16:16:23.712283', '2026-01-11 16:55:51.679912'),
(4, 'task4', NULL, '2026-01-12', 'HIGH', 'PENDING', 'task4', 5, '2026-01-11 16:17:06.339919', '2026-01-11 16:17:06.339919'),
(5, 'task5', NULL, '2026-01-10', 'MEDIUM', 'PENDING', 'task5', 5, '2026-01-11 16:17:29.667651', '2026-01-11 16:17:29.668649'),
(6, 'Task1', NULL, '2026-01-07', 'LOW', 'PENDING', 'Task1', 8, '2026-01-11 21:49:26.803425', '2026-01-11 21:49:26.803425'),
(7, 'Task2', NULL, '2026-01-14', 'MEDIUM', 'COMPLETED', 'Task2', 8, '2026-01-11 21:49:55.930626', '2026-01-11 21:50:31.338662'),
(8, 'Task3', NULL, '2026-01-12', 'HIGH', 'PENDING', 'Task3', 8, '2026-01-11 21:50:26.874401', '2026-01-11 21:50:26.874401');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` enum('ADMIN','MEMBER') COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `role`, `username`) VALUES
(2, '{bcrypt}$2a$10$0pyAiRNuJ2UrKCii2RbySegQbzAtPhCP5uxVPuM78scOoGk1OAFBC', 'MEMBER', 'agg'),
(3, '{bcrypt}$2a$10$tTaMLVDwfIF...muid4MR.mLaVFxniwUmybKYB4D3vmjrJErFQrB2', 'ADMIN', 'aggwe'),
(4, '{bcrypt}$2a$10$hy1HreEO3Kf2/UJ/CKCfM.hDLrFgamhUwUsN3gti6ukTNlimrzaEa', 'ADMIN', 'losko'),
(5, '{bcrypt}$2a$10$L5Tz0YjnM468qrq91q36A.wd62bKTVS.8XxuFrF/4DeVq64NAWZCy', 'ADMIN', 'qwer'),
(8, '{bcrypt}$2a$10$gRJ5bQaiIbjYszB6yiQabeFNKJ3pBow.hwj1x4Eq9fTIcqQ5EB8k2', 'MEMBER', 'Kostas');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK2ktjq1slw0ldkuy5rx8fbte2p` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`),
  ADD CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
