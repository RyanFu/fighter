/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011/8/18 17:10:14                           */
/*==============================================================*/


drop table if exists ad;

drop table if exists ad_ars;

drop table if exists ad_phonetype;

drop table if exists ad_plat_type;

drop table if exists ad_zone;

drop table if exists adshow_info;

drop table if exists app;

drop table if exists click_info;

drop table if exists commision_info;

drop table if exists member;

drop table if exists paypal_info;

drop table if exists recharge_account;

drop table if exists recharge_info;

drop table if exists request_info;

/*==============================================================*/
/* Table: ad                                                    */
/*==============================================================*/
create table ad
(
   id                   int not null auto_increment,
   name                 varchar(200),
   start_date           date,
   end_date             date,
   type                 varchar(10) comment '生活资讯
            影音娱乐等',
   max_pay_perday       float(20,4),
   description          varchar(1024),
   app_platform         varchar(100) comment '1：Android平台
            2：Ophone平台
            3：两者都选',
   show_type            varchar(10) comment '点击显示网页
            点击下载apk
            点击通电话
            点击google map定位
            点击google搜索
            点击发短信
            点击发email',
   show_src             varchar(256),
   img_word_type        varchar(10) comment '仅文字
            仅图片
            图文并存',
   title                varchar(50),
   img_path             varchar(256),
   pay_mode             varchar(10),
   mem_id               int,
   state                varchar(10) comment '1：可用
            2：未完成
            3：待审批
            4：运行中
            5：暂停
            6：删除
            7：未通过
            8：结束',
   primary key (id)
);

/*==============================================================*/
/* Table: ad_ars                                                */
/*==============================================================*/
create table ad_ars
(
   id                   int not null auto_increment,
   ad_id                int not null,
   ars_name             varchar(20),
   primary key (id)
);

alter table ad_ars comment '广告所选的运营商';

/*==============================================================*/
/* Table: ad_phonetype                                          */
/*==============================================================*/
create table ad_phonetype
(
   id                   int not null auto_increment,
   ad_id                int not null,
   phone_name           varchar(100),
   phone_code           varchar(50),
   primary key (id)
);

alter table ad_phonetype comment '广告投放的手机型号';

/*==============================================================*/
/* Table: ad_plat_type                                          */
/*==============================================================*/
create table ad_plat_type
(
   id                   int not null auto_increment,
   ad_id                int not null,
   platform             varchar(10) comment '1：Android平台
            2：Ophone平台',
   type_name            varchar(200) comment '主题桌面，电子阅读等',
   primary key (id)
);

/*==============================================================*/
/* Table: ad_zone                                               */
/*==============================================================*/
create table ad_zone
(
   id                   int not null auto_increment,
   ad_id                int not null,
   zone_name            varchar(50),
   zone_code            varchar(50),
   primary key (id)
);

alter table ad_zone comment '广告投放的地区';

/*==============================================================*/
/* Table: adshow_info                                           */
/*==============================================================*/
create table adshow_info
(
   id                   int(20) not null auto_increment,
   app_id               varchar(100) not null,
   ad_id                int not null,
   show_time            datetime,
   cur_cpm              float(20,4),
   primary key (id)
);

/*==============================================================*/
/* Table: app                                                   */
/*==============================================================*/
create table app
(
   id                   int not null auto_increment,
   app_name             varchar(200) not null,
   app_type             varchar(20) not null comment '体育竞技
            电子书籍
            实用工具等',
   pid                  varchar(100),
   create_time          datetime,
   mem_id               int not null,
   app_platform         varchar(20) comment '1：Android平台
            2：Ophone平台',
   primary key (id)
);

/*==============================================================*/
/* Table: click_info                                            */
/*==============================================================*/
create table click_info
(
   id                   int(20) not null auto_increment,
   app_id               varchar(100) not null,
   ad_id                int not null,
   click_time           datetime,
   cur_cpc              float(20,4),
   primary key (id)
);

/*==============================================================*/
/* Table: commision_info                                        */
/*==============================================================*/
create table commision_info
(
   id                   int not null auto_increment,
   mem_id               int not null,
   commision_num        varchar(100),
   bank_name            varchar(100),
   bank_num             varchar(100),
   start_time           datetime comment '用户注册时间或用户最近一次提现成功时间',
   end_time             datetime comment '雇佣金发放成功的日期',
   income               float(20,4),
   deduct_count         float(20,4),
   full_count           float(20,4),
   poundage             float(20,4),
   real_count           float(20,4),
   state                varchar(10),
   primary key (id)
);

/*==============================================================*/
/* Table: member                                                */
/*==============================================================*/
create table member
(
   id                   int not null auto_increment,
   email                varchar(200) not null,
   password             varchar(200) not null,
   regist_time          datetime,
   name                 varchar(100),
   telephone            varchar(50),
   cellphone            varchar(50),
   account_type         varchar(10) comment '1：程序开发者
            2：广告商
            3：两者都是',
   qq                   varchar(20),
   com_name             varchar(200),
   com_homepage         varchar(256),
   com_address          varchar(512),
   zipcode              varchar(10),
   account_attr         varchar(10) comment '1：企业账户
            2：个人账户',
   invoice_able         varchar(10),
   get_money_mode       varchar(10) comment '1：月底自动把本月的收入存入我的指定帐户 
            2：每个月我主动提出提现申请时再给我结算，否则累计到下次结算 ',
   open_bank            varchar(100),
   account_number       varchar(100),
   open_name            varchar(100),
   address_bank         varchar(512),
   zipcode_bank         varchar(10),
   ad_balance           float(20,4),
   income_balance       float(20,4),
   state                varchar(10),
   ser_num              varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: paypal_info                                           */
/*==============================================================*/
create table paypal_info
(
   id                   int not null auto_increment,
   mem_id               int not null,
   apply_time           datetime,
   bod_number           varchar(100),
   apply_amount         float(20,4),
   allow_amount         float(20,4),
   state                varchar(10),
   pay_date             datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: recharge_account                                      */
/*==============================================================*/
create table recharge_account
(
   id                   int not null auto_increment,
   open_name            varchar(200) not null,
   bank_name            varchar(200) not null,
   bank_num             varchar(100) not null,
   bank_address         varchar(256),
   primary key (id)
);

/*==============================================================*/
/* Table: recharge_info                                         */
/*==============================================================*/
create table recharge_info
(
   id                   int not null auto_increment,
   mem_id               int not null,
   recharge_time        datetime,
   ord_number           varchar(100),
   recharge_count       float(20,4),
   real_count           float(20,4),
   state                varchar(10),
   bank_num             varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: request_info                                          */
/*==============================================================*/
create table request_info
(
   id                   int(20) not null auto_increment,
   app_id               varchar(100) not null,
   ad_id                int not null,
   request_time         datetime,
   imei                 varchar(100),
   primary key (id)
);









insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-10 09:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-11 09:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-12 09:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-13 09:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-13 09:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,1,'2011-07-13 09:09:08','test');

insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-10 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-11 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-12 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-13 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-13 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-07-13 08:09:08','test');

insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-10 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-11 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-12 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-13 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-13 08:08:08','test');
insert into request_info (app_id,ad_id,request_time,imei) values (1,2,'2011-08-13 08:09:08','test');

insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,1,'2011-07-10 06:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,1,'2011-07-11 06:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,1,'2011-07-12 06:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,1,'2011-07-13 06:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,1,'2011-07-13 06:08:09',8.0);

insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-07-11 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-07-12 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-07-13 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-07-13 08:08:09',8.0);

insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-08-11 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-08-12 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-08-13 08:08:09',8.0);
insert into adshow_info (app_id,ad_id,show_time,cur_cpm) values (1,2,'2011-08-13 08:08:09',8.0);

insert into click_info (app_id,ad_id,click_time,cur_cpc) values (1,2,'2011-07-13 08:09:08',0.3);
insert into click_info (app_id,ad_id,click_time,cur_cpc) values (1,2,'2011-07-13 09:09:08',0.3);

insert into click_info (app_id,ad_id,click_time,cur_cpc) values (1,2,'2011-08-13 09:09:08',0.3);

