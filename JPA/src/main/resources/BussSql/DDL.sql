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