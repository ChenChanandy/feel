CREATE TABLE `feel` (
  `id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `feel` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `img` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  `fid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;