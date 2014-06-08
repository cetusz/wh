CREATE TABLE IF NOT EXISTS `t_channel_day_crawler` (                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `channelId` varchar(20) DEFAULT NULL,
   `crawlerStatus` int(2) DEFAULT NULL, 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
   `content` longtext COLLATE utf8_bin,
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `t_user` (                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `userName` varchar(20) DEFAULT NULL,
   `password` varchar(50) DEFAULT NULL, 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;