# - 创建三张主表: 用户表, 角色表,权限表
use db2;
create table user
(
    id   int primary key auto_increment,
    name varchar(20)
);

create table role
(
    id   int primary key auto_increment,
    name varchar(20)
);

create table module
(
    id   int primary key auto_increment,
    name varchar(20)
);

# - 创建两张关系表: 用户角色关系表      角色权限关系表

create table u_r
(
    uid int,
    rid int
);

create table r_m
(
    rid int,
    mid int
);

# - 准备数据:

insert into user
values (null, '刘德华'),
       (null, '杨幂'),
       (null, '张学友');

insert into role
values (null, '男游客'),
       (null, '男管理员'),
       (null, '女游客');

insert into module
values (null, '男浏览'),
       (null, '男发帖'),
       (null, '男评论'),
       (null, '男删帖'),
       (null, '女浏览');

# 刘德华(男游客,女游客)    杨幂(女游客)     张学友(男管理员)

insert into u_r
values (1, 1),
       (1, 3),
       (2, 3),
       (3, 2);

insert into r_m
values (1, 1),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 5);

# 查询刘德华拥有哪些角色
select r.name
from user u
         join u_r ur on u.id = ur.uid
         join role r on r.id = ur.rid
where u.name = '刘德华';
# 查询女游客对应的用户都有谁
select u.name
from user u
         join u_r ur on u.id = ur.uid
         join role r on r.id = ur.rid
where r.name = '女游客';
# 查询男管理员对应哪些权限
select m.name
from module m
         join r_m rm on m.id = rm.mid
         join role r on r.id = rm.rid
where r.name = '男管理员';
# 查询刘德华拥有哪些权限
select m.name
from user u
         join u_r ur on u.id = ur.uid
         join r_m rm on ur.rid = rm.rid
         join module m on rm.mid = m.id
where u.name = '刘德华';
# 查询有女浏览权限的用户都有谁
select u.name
from user u
         join u_r ur on u.id = ur.uid
         join r_m rm on ur.rid = rm.rid
         join module m on rm.mid = m.id
where m.name = '女浏览';
