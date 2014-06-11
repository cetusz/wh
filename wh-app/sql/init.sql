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
    `userName` varchar(20) DEFAULT '',
   `password` varchar(50) DEFAULT '', 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `t_public_account_edit`(                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `accountId` varchar(100) DEFAULT '',
   `chineseName` varchar(50) DEFAULT '', 
   	`funcintro` varchar(300) DEFAULT '',
	`wxcredit`  varchar(300)  DEFAULT '',
    `sinacredit` varchar(300) DEFAULT '',
	`isCrawler`  boolean DEFAULT FALSE,
	`qrCodeUrl`  varchar(150) DEFAULT '',
	`cateIds`  varchar(150) DEFAULT '',
	`bizId` varchar(200) DEFAULT '',
	`isShielded` boolean DEFAULT FALSE,
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
   `version` varchar(20),
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;