create database school;
use school;
drop table student;
create table student
(
    Sno   char(6) comment '学号',
    Sname varchar(8) comment '姓名',
    Ssex  char(2) comment '性别',
    Sage  smallint comment '年龄',
    Sdept varchar(15) comment '系科'
);

drop table course;
create table course
(
    Cno     char(4) comment '课程号',
    cname   varchar(20) comment '课程名',
    Cpno    char(4) comment '选修课',
    Ccredit tinyint comment '学分'
);

drop table sc;
create table sc
(
    Sno   char(6) comment '学号',
    Cno   char(4) comment ' 课程号 ',
    Grade decimal(12, 2) comment ' 成绩 '
);

use school;
insert into student(Sno,Sname,Ssex,Sage,Sdept) values ('4001','赵茵','男',20,'SX'),('4002','杨华','女',21,'JSJ');

update student set Sdept='JSJ' where Sno='4001';

update student set Ssex='女' ,Sage=Sage+1 where Sname='赵茵';

delete from student where Sdept='JSJ' and Ssex='男';

use school;
create table cust(studentno int primary key ,name varchar(10),address varchar(50),telno varchar(50));
create table mark(studnntno int primary key ,english double,math double,computer double);


select avg(english) from mark;

select math,name,address,telno from cust,mark where studentno in(11,22,33,44,55);

select name,computer from cust,mark order by computer desc ;

select english+math+computer as score,studentno,name from  mark join cust on mark.studnntno=cust.studentno where english+math+computer>240 order by score desc;