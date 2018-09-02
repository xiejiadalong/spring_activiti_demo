drop table if exists user_t;

/*==============================================================*/
/* Table: user_t                                                */
/*==============================================================*/
create table user_t
(
   user_id              varchar(50) not null,
   user_name            varchar(200),
   user_pwd             varchar(100),
   id_number            varchar(50),
   dev_position         varchar(50),
   status               varchar(50),
   level                varchar(50),
   primary key (user_id)
);

alter table user_t comment '用户表';

drop table if exists role_t;

/*==============================================================*/
/* Table: role_t                                                */
/*==============================================================*/
create table role_t
(
   role_id              varchar(50) not null,
   role_name            varchar(200),
   role_status          varchar(50),
   role_type            varchar(50),
   primary key (role_id)
);

alter table role_t comment '角色表';


drop table if exists permission_t;

/*==============================================================*/
/* Table: permission_t                                          */
/*==============================================================*/
create table permission_t
(
   permission_id        varchar(50) not null,
   permissiom_name      varchar(200),
   comments             varchar(500),
   primary key (permission_id)
);

alter table permission_t comment '权限点';

drop table if exists menu_t;

/*==============================================================*/
/* Table: menu_t                                                */
/*==============================================================*/
create table menu_t
(
   menu_id              varchar(50) not null,
   menu_name            varchar(200),
   p_id                 varchar(50),
   status               varchar(50),
   menu_type            varchar(50),
   primary key (menu_id)
);

alter table menu_t comment '菜单栏';


drop table if exists user_role_t;

/*==============================================================*/
/* Table: user_role_t                                           */
/*==============================================================*/
create table user_role_t
(
   user_id              varchar(50) not null,
   role_id              varchar(50) not null,
   status               varchar(50)
);

alter table user_role_t comment '用户角色映射表';

alter table user_role_t add constraint FK_Reference_3 foreign key (user_id)
      references user_t (user_id) on delete restrict on update restrict;

alter table user_role_t add constraint FK_Reference_4 foreign key (role_id)
      references role_t (role_id) on delete restrict on update restrict;

      
drop table if exists role_permission_t;

/*==============================================================*/
/* Table: role_permission_t                                     */
/*==============================================================*/
create table role_permission_t
(
   role_id              varchar(50) not null,
   permission_id        varchar(50) not null,
   status               varchar(50)
);

alter table role_permission_t comment '角色权限表';

alter table role_permission_t add constraint FK_Reference_1 foreign key (role_id)
      references role_t (role_id) on delete restrict on update restrict;

alter table role_permission_t add constraint FK_Reference_2 foreign key (permission_id)
      references permission_t (permission_id) on delete restrict on update restrict;

      
      drop table if exists permission_menu_t;

/*==============================================================*/
/* Table: permission_menu_t                                     */
/*==============================================================*/
create table permission_menu_t
(
   permission_id        varchar(50) not null,
   menu_id              varchar(50) not null,
   status               varchar(50)

);

alter table permission_menu_t comment '权限菜单关系表';

alter table permission_menu_t add constraint FK_Reference_5 foreign key (permission_id)
      references permission_t (permission_id) on delete restrict on update restrict;

alter table permission_menu_t add constraint FK_Reference_6 foreign key (menu_id)
      references menu_t (menu_id) on delete restrict on update restrict;




