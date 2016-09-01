/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/1 12:37:52                            */
/*==============================================================*/


drop table if exists demo_app;

drop table if exists demo_company;

drop table if exists demo_leave;

drop table if exists demo_manage_user;

drop table if exists demo_staff;

/*==============================================================*/
/* Table: demo_app                                              */
/*==============================================================*/
create table demo_app
(
   id                   bigint(20) not null auto_increment comment '自增长id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   identity             varchar(64) not null comment '应用标示',
   type                 tinyint not null default 1 comment '类型',
   name                 varchar(100) not null comment '应用',
   logo                 varchar(200) comment '图标',
   remark               varchar(2000) comment '备注描述',
   primary key (id)
);

alter table demo_app comment '应用';

/*==============================================================*/
/* Table: demo_company                                          */
/*==============================================================*/
create table demo_company
(
   id                   bigint(20) not null auto_increment comment '自增长id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   name                 varchar(100) not null comment '公司名',
   remark               varchar(2000) comment '备注描述',
   primary key (id)
);

alter table demo_company comment '子公司信息';

/*==============================================================*/
/* Table: demo_leave                                            */
/*==============================================================*/
create table demo_leave
(
   id                   bigint(20) not null auto_increment comment '自增长id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   startTime            bigint(20) not null comment '开始时间',
   endTime              bigint(20) not null comment '结束时间',
   reason               varchar(1000) not null comment '缘由',
   staffId              bigint(20) not null comment '申请人',
   approver             bigint(20) not null comment '审批人',
   state                tinyint not null default 1 comment '状态，1等待审批，2已通过，3未通过，4已撤销',
   primary key (id)
);

alter table demo_leave comment '请假记录';

/*==============================================================*/
/* Table: demo_manage_user                                      */
/*==============================================================*/
create table demo_manage_user
(
   id                   bigint(20) not null auto_increment comment '自增长id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   name                 varchar(200) not null comment '登录名',
   password             varchar(100) not null comment '密码',
   primary key (id)
);

alter table demo_manage_user comment ' manage登录用户';

/*==============================================================*/
/* Table: demo_staff                                            */
/*==============================================================*/
create table demo_staff
(
   id                   bigint(20) not null auto_increment comment '自增长id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   companyId            bigint(20) not null comment '所属子公司id',
   name                 varchar(100) not null comment '员工姓名',
   mobile               varchar(20) comment '手机号',
   remark               varchar(2000) comment '备注描述',
   jobnumber            varchar(20) not null comment '工号',
   pwd                  varchar(200) not null comment '客户端登录密码',
   primary key (id)
);

alter table demo_staff comment '员工信息';

