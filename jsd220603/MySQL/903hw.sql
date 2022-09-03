use empdb;
# 查询工资大于等于 3000 的员工姓名和工资
select name,sal from emp where sal>=3000;
# 查询 1 号部门的员工姓名和工作
select name,job from emp where dept_id=1;
# 查询不是程序员的员工姓名和工作(两种写法)
select name,job from emp where job!='程序员';
select name,job from emp where job<>'程序员';
# 查询奖金等于 300 的员工姓名,工资和工作
select name,sal,job from emp where comm=300;
# 查询 1 号部门工资大于 2000 的员工信息
select * from emp where dept_id=1 and sal>2000;
# 查询 3 号部门或工资等于 5000 的员工信息
select * from emp where dept_id=3 or sal=5000;
# 查询出 CEO 和项目经理的名字
select name from emp where job in('CEO','项目经理');
# 查询工资为 3000,1500 和 5000 的员工信息
select * from emp where sal in(3000,1500,5000);
# 查询工资不等于 3000,1500 和 5000 的员工信息
select * from emp where sal not in(3000,1500,5000);
# 查询工资在 1000 到 2000 之间的员工信息
select * from emp where sal>1000 and sal<2000;
# 查询工资在 1000 到 2000 以外的员工信息
select * from emp where sal<1000 or sal>2000;
# 查询有领导的员工姓名和领导 id
select name,manager from emp where manager is not NULL;
# 查询没有领导的员工姓名和工资
select name,sal from emp where manager is null;
# 查询员工表中出现了哪几种不同的工作
select distinct job from emp;
# 查询员工表中出现了那几个部门的 id
select distinct dept_id from emp;
# 查询姓孙的员工姓名
select name from emp where name like '孙%';
# 查询名字最后一个字是精的员工信息
select * from emp where name like '%精';
# 查询工作中包含销售的员工信息
select * from emp where job like '%销售%';
# 查询工作中第二个字是售的员工信息
select * from emp where job like '_售%';
# 查询名字中包含僧的员工并且工资高于 2000 的员工信息
select * from emp where name like'%僧%' and sal>2000;
# 查询 1 号和 2 号部门中工作以市开头的员工信息
select * from emp where job like'市%' and (dept_id=1 or dept_id=2);
# 查询所有员工的姓名和工资 按照工资升序排序
select name,sal from emp where sal order by sal;
# 查询所有员工的姓名和工资 按照工资降序排序
select name,sal from emp where sal order by sal desc;
# 查询所有员工姓名 工资和部门 id 按照部门 id 降序排序,如果部门 id 一致则按照工资升序排序
select name,sal,dept_id from emp where dept_id or sal order by dept_id desc , sal;
# 查询员工表中 3 号部门工资高于 1500 的员工信息
select * from emp where dept_id=3 and sal>1500;
# 查询 2 号部门员工或者没有领导的员工信息
select * from emp where dept_id=2 or manager is null;
# 查询有领导的员工姓名,工资按照工资降序排序
select name,sal from emp where manager is not null and sal order by sal desc ;
# 查询 2 号和 3 号部门的员工姓名和入职日期 hiredate 按照入职日期降序排序
select name,hiredate from emp where dept_id=2 or dept_id=3 and hiredate order by hiredate desc ;
# 查询名字中包含僧和包含精的员工姓名
select name from emp where name like '%僧%' or name like '%精%';
# 查询工资高于 2000 的工作有哪几种?
select distinct job from emp where sal>2000;
# 查询工资最高的前三个员工
select * from emp where sal order by sal desc limit 0,3;
# 查询员工表按照 id 排序, 第 2 页的 5 条数据
select * from emp where id order by id limit 5,5;
# 查询员工表按照 id 排序, 第 3 页的 4 条数据
select * from emp where id order by id limit 8,4;
# 查询 3 号部门工资最低的员工姓名和工资
select name,sal from emp where dept_id=3 order by sal limit 0,1;
# 查询工作不是人事的员工中工资降序第二页的 3 条数据
select * from emp where job!='人事' order by sal desc limit 3,3;
# 查询没有领导的员工和 3 号部门的员工,工资降序取前三条
select * from emp where manager is null or dept_id=3 order by sal desc limit 3;
# 查询 2 号部门的最高工资
select max(sal) from emp where dept_id=2;
# 查询有领导的员工中工资在 1000 到 2000 之间的人数
select count(*) from emp where manager is not null and sal>1000 and sal<2000;
# 查询 3 号部门的工资总和
select sum(sal) from emp where dept_id=3;
# 查询程序员和销售的总人数
select count(*) from emp where job in('程序员','销售');
# 查询 1 号部门有领导的员工的平均工资
select avg(sal) from emp where dept_id=1 and manager is not null;
# 查询 1 号部门的最低工资和最高工资
select max(sal) as '最高工资',min(sal) as '最低工资' from emp where dept_id=1;
# 查询和销售相关的工作人数
select count(*) from emp where job like  '%销售%';
# 查询工资不是 1500 和 3000 的员工人数
select count(*) from emp where sal not in(1500,3000);
# 查询 1 号部门出现了哪几种工作
select distinct job from emp where dept_id=1;
# 查询名字包含精的员工数量
select count(*) from emp where name like '%精%';
# 查询和销售相关的工作一个月工资总和
select sum(sal) from emp where job like '%销售%';
# 查询 2 号部门的最高工资和最低工资起别名
select max(sal) as '最高工资',min(sal) as '最低工资' from emp where dept_id=2;
# 查询每个部门的平均工资
select dept_id,avg(sal) from emp group by dept_id;
# 查询每种工作的平均工资
select job,avg(sal) from emp group by job;
# 查询每个部门的最高工资
select dept_id,max(sal) from emp group by dept_id;
# 查询每种工作的最低工资
select job,min(sal) from emp group by job;
# 查询每个部门工资高于 2000 的人数
select dept_id,count(*) from emp where sal>2000 group by dept_id;
# 查询每个部门有领导的员工人数
select dept_id,count(*) from emp where manager is not null group by dept_id;
# 查询 1 号部门每种工作的最低工资
select job,min(sal) from emp where dept_id=1 group by job;
# 查询平均工资最高的部门 id 和平均工资
select dept_id,avg(sal) from emp group by dept_id limit 1;