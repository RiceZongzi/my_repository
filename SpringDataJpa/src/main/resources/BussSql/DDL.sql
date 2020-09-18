-- 2020-09-16 创建新表
create table `cst_customer`(
    `cust_id` bigint(20) not null auto_increment,
    `cust_name` varchar(100) default null comment '客户名称',
    `cust_source` varchar(100) default null comment '客户来源',
    `cust_level` varchar(100) default null comment '客户级别',
    `cust_industry` varchar(100) default null comment '客户所属行业',
    `cust_phone` varchar(100) default null comment '客户的联系方式',
    `cust_address` varchar(100) default null comment '客户地址',
    primary key (`cust_id`)
)engine=InnoDB auto_increment=9 default charset = utf8;

-- 2020-09-18 创建新表
CREATE TABLE cst_linkman (
  lkm_id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  lkm_name varchar(16) DEFAULT NULL COMMENT '联系人姓名',
  lkm_gender char(1) DEFAULT NULL COMMENT '联系人性别',
  lkm_phone varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
  lkm_mobile varchar(16) DEFAULT NULL COMMENT '联系人手机',
  lkm_email varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
  lkm_position varchar(16) DEFAULT NULL COMMENT '联系人职位',
  lkm_memo varchar(512) DEFAULT NULL COMMENT '联系人备注',
  lkm_cust_id bigint(32) NOT NULL COMMENT '客户id(外键)',
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
