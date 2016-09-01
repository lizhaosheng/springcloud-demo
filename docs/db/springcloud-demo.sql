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
   id                   bigint(20) not null auto_increment comment '������id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   identity             varchar(64) not null comment 'Ӧ�ñ�ʾ',
   type                 tinyint not null default 1 comment '����',
   name                 varchar(100) not null comment 'Ӧ��',
   logo                 varchar(200) comment 'ͼ��',
   remark               varchar(2000) comment '��ע����',
   primary key (id)
);

alter table demo_app comment 'Ӧ��';

/*==============================================================*/
/* Table: demo_company                                          */
/*==============================================================*/
create table demo_company
(
   id                   bigint(20) not null auto_increment comment '������id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   name                 varchar(100) not null comment '��˾��',
   remark               varchar(2000) comment '��ע����',
   primary key (id)
);

alter table demo_company comment '�ӹ�˾��Ϣ';

/*==============================================================*/
/* Table: demo_leave                                            */
/*==============================================================*/
create table demo_leave
(
   id                   bigint(20) not null auto_increment comment '������id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   startTime            bigint(20) not null comment '��ʼʱ��',
   endTime              bigint(20) not null comment '����ʱ��',
   reason               varchar(1000) not null comment 'Ե��',
   staffId              bigint(20) not null comment '������',
   approver             bigint(20) not null comment '������',
   state                tinyint not null default 1 comment '״̬��1�ȴ�������2��ͨ����3δͨ����4�ѳ���',
   primary key (id)
);

alter table demo_leave comment '��ټ�¼';

/*==============================================================*/
/* Table: demo_manage_user                                      */
/*==============================================================*/
create table demo_manage_user
(
   id                   bigint(20) not null auto_increment comment '������id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   name                 varchar(200) not null comment '��¼��',
   password             varchar(100) not null comment '����',
   primary key (id)
);

alter table demo_manage_user comment ' manage��¼�û�';

/*==============================================================*/
/* Table: demo_staff                                            */
/*==============================================================*/
create table demo_staff
(
   id                   bigint(20) not null auto_increment comment '������id',
   createTime           bigint(20) not null,
   updateTime           bigint(20) not null default 0,
   companyId            bigint(20) not null comment '�����ӹ�˾id',
   name                 varchar(100) not null comment 'Ա������',
   mobile               varchar(20) comment '�ֻ���',
   remark               varchar(2000) comment '��ע����',
   jobnumber            varchar(20) not null comment '����',
   pwd                  varchar(200) not null comment '�ͻ��˵�¼����',
   primary key (id)
);

alter table demo_staff comment 'Ա����Ϣ';

