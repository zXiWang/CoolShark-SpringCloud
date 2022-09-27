use empdb;
select *
from emp
where manager is not null
  and sal between 1000 and 2000;

select *
from emp
where manager is not null
order by sal desc
limit 3;

select distinct job
from emp
where dept_id = 1
   or dept_id = 3;

select dept_id, count(*)
from emp
group by dept_id;

select job, count(*)
from emp
where manager is not null
group by job;;

select dept_id, max(sal) as '最高工资', min(sal) as '最低工资', avg(sal) as '平均工资'
from emp
group by dept_id
order by dept_id;

select dept_id, avg(sal)
from emp
group by dept_id
having avg(sal) > 2000;

select job, count(*)
from emp
group by job
having count(*) > 1;

select dept_id, sum(sal)
from emp
where manager is not null
group by dept_id
having sum(sal) > 5400;

select dept_id, avg(sal)
from emp
where sal between 1000 and 3000
group by dept_id
having avg(sal) > 2000;

select *
from emp
where sal >
      (select avg(sal) from emp where dept_id = 2);

select *
from emp
where sal > (select max(sal) from emp where job = '程序员');

select *
from emp
where sal = (select max(sal) from emp);

select *
from emp
where job = (select job from emp where name = '孙悟空')
  and name <> '孙悟空';

select *
from emp
where dept_id = (
    select dept_id
    from emp
    where sal = (
        select min(sal)
        from emp))
  and sal != (select min(sal) from emp);

select name, sal, 5 * sal '年终奖'
from emp;

update emp
set sal=sal + 5
where dept_id = 2;

select e.name, d.name
from emp e,
     dept d
where e.dept_id = d.id;

select e.name sal, d.name
from emp e,
     dept d
where sal > 2000
  and e.dept_id = d.id;

select loc
from emp e,
     dept d
where e.name = '孙悟空'
  and e.dept_id = d.id;

select e.name, d.name
from emp e
         join dept d
              on e.dept_id = d.id;

select e.name, sal, d.name
from emp e
         join dept d
              on e.dept_id = d.id
where sal > 2000;

select loc
from emp e
         join dept d on e.dept_id = d.id
where e.name = '孙悟空';

insert into emp (name, sal) value ('灭霸', 5);

select e.name, d.name
from emp e
         right join dept d on e.dept_id = d.id;

select loc, e.name, job
from emp e
         right join dept d on e.dept_id = d.id;

select *
from emp
where sal > (
    select avg(sal)
    from emp
    where job = '程序员'
);

select job, count(*)
from emp
group by job
having count(*) = 1;

select e.name, d.name
from emp e
         join dept d on d.id = e.dept_id
where dept_id in (1, 2)
  and sal > 2000;

select e.name, sal, d.*
from emp e
         join dept d on e.dept_id = d.id;
