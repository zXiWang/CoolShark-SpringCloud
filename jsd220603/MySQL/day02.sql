/*	○ 创建数据库newdb1, 字符集utf8并使用
	○ 在数据库中创建员工表emp 字段:id,name,sal(工资),deptId(部门id) 字符集utf8
	○ 创建部门表dept 字段:id,name,loc(部门地址) 字符集utf8
	○ 部门表插入以下数据: 1 神仙部 天庭       2 妖怪部  盘丝洞
	○ 员工表插入一下数据:  1 悟空 5000 1  ,   2 八戒  2000  1  ,3 蜘蛛精 8000  2  ,  4 白骨精 9000  2
	○ 查询工资6000以下的员工姓名和工资
	○ 修改神仙部的名字为取经部
	○ 给员工表添加奖金comm字段
	○ 修改员工表中部门id为1的 奖金为500
	○ 把取经部的地址改成五台山
	○ 修改奖金字段为性别gender字段 类型为varchar(5)
	○ 修改孙悟空和猪八戒性别为男
	○ 删除没有性别的员工
	○ 删除性别字段
	○ 删除表  和 删除数据库
*/
create database newdb1 charset utf8;

use newdb1;
create table emp(id int,name varchar(20),sal varchar(20),deptId int ) charset utf8;
create table dept(id int,name varchar(20),loc varchar(50)) charset utf8;
insert into dept values(1,'神仙部','天庭'),(2,'妖怪部','盘丝洞');
insert into emp values(1,'悟空',5000,1),(2,'八戒',2000,1),(3,'蜘蛛精',8000,2),(4,'白骨精',9000,2);
select name,sal from emp where sal<6000;
update dept set name='取经部' where name='神仙部';
alter table emp add comm int;
update emp set comm=500 where id=1;
update dept set loc='五台山' where name='取经部';
alter table emp change comm gender varchar(5);
update emp set gender='男' where name='孙悟空' or name='八戒';
delete from emp where gender is null;
create table t3(t1 date,t2 time,t3 datetime,t4 timestamp);
insert into t3 values("2022-5-20",null,null,null);
insert into t3 values(null,"10:38:40","2008-10-20 10:20:30",null);
select * from emp;

# drop table emp;
# drop database newdb1;