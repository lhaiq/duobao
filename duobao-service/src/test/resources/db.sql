CREATE TABLE `mall_code` (
  `id` bigint(20) NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `log_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `mall_shop_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shoping_id` bigint(20) NOT NULL COMMENT 'shopping ',
  `user_id` bigint(20) DEFAULT NULL,
  `num` int(11) NOT NULL COMMENT '参与人次',
  `serial_number` int(11) NOT NULL COMMENT '期数',
  `money` double NOT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0-未抽奖 1-已抽奖',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `mall_goods` (
  `id` int(11) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `image` varchar(128) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `excute_periods` int(11) DEFAULT NULL COMMENT '已执行期数',
  `status` int(11) DEFAULT '0' COMMENT '0-下架 1-上架  2-预下架',
  `add_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0-普通商品 1-十分精彩 2-极速夺宝',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8CREATE TABLE `mall_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(32) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8