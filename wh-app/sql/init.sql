CREATE TABLE IF NOT EXISTS `t_user` (                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `userName` varchar(20) DEFAULT '',
   `password` varchar(50) DEFAULT '', 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `t_recommend_edit`(                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `title`    varchar(300) DEFAULT '',
   `content`   varchar(300) DEFAULT '',
   `intro`      varchar(500) DEFAULT '',
   `contentUrl`    varchar(200) DEFAULT '',
   `categoryId`    bigint(20) DEFAULT 0,
   `categoryName`  varchar(50),
   `accountId`      bigint(20) NOT NULL,
   `accountName`    varchar(30) DEFAULT '',
   `pubDate`        timestamp,       
   `createTime`     timestamp,
   `lastUpdateTime` timestamp,
   `status` int,
    PRIMARY KEY (`id`)        
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `t_public_account_edit`(                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `accountId` varchar(100) DEFAULT '',
   `chineseName` varchar(50) DEFAULT '', 
   `headImg` VARCHAR(150) DEFAULT '', 
   	`funcintro` varchar(300) DEFAULT '',
	`wxcredit`  varchar(300)  DEFAULT '',
    `sinacredit` varchar(300) DEFAULT '',
	`isCrawler`  boolean DEFAULT FALSE,
	`qrCodeUrl`  varchar(150) DEFAULT '',
	`cateIds`  varchar(150) DEFAULT '',
	`cateNames`  varchar(150) DEFAULT '',
	`bizId` varchar(200) DEFAULT '',
	`isShielded` boolean DEFAULT FALSE,
	`lastCrawlerDate` varchar(30) DEFAULT '', 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
   `version` varchar(20),
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `t_eassay_edit`(                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `sourceId` varchar(100) DEFAULT '',
   `title`    varchar(300) DEFAULT '',
   `content`   varchar(300) DEFAULT '',
   `intro`      varchar(500) DEFAULT '',
   `contentUrl`    varchar(200) DEFAULT '',
   `categoryId`    bigint(20) DEFAULT 0,
   `categoryName`  varchar(50),
   `accountId`      bigint(20) NOT NULL,
   `accountName`    varchar(30) DEFAULT '',
   `openId`         varchar(100) DEFAULT '',
   `isRecommanded`  BOOLEAN DEFAULT FALSE,
   `pubDate`        timestamp,       
   `createTime`     timestamp,
   `lastUpdateTime` timestamp,
    PRIMARY KEY (`id`)        
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE INDEX idx_t_eassay_edit_pubdate ON t_eassay_edit (pubdate);
CREATE INDEX idx_t_eassay_edit_title ON t_eassay_edit (title);
CREATE INDEX idx_t_eassay_edit_contentUrl ON t_eassay_edit (contentUrl);
CREATE INDEX idx_t_eassay_edit_categoryName ON t_eassay_edit (categoryName);
CREATE INDEX idx_t_eassay_edit_categoryId ON t_eassay_edit (categoryId);

CREATE TABLE IF NOT EXISTS `t_category_edit` (                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `cateName` varchar(20) DEFAULT '',
   `orderNum` int(2) DEFAULT 0, 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
   `version` varchar(20),
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE IF NOT EXISTS `t_member` (                                  
   `id` bigint(20) NOT NULL AUTO_INCREMENT , 
    `deviceId` varchar(100) DEFAULT '',
   `macAddress` varchar(100) DEFAULT '', 
    `createTime` timestamp,
   `lastUpdateTime` timestamp,
   `version` varchar(20),
    PRIMARY KEY (`id`)                                       
)  ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;