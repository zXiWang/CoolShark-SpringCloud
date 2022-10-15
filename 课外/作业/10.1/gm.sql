create database gm;
use gm;
create table customer_info(customer_id varchar(11) comment '客户编号' primary key,customer_name varchar(30) comment '客户名称' not null,
                           customer_status varchar(4) comment '客户状态\nN 正常/S 停用',input_date date comment '登记日期');

create table group_info(group_id varchar(11) comment '集团编号' primary key,group_name varchar(30) comment '集团名称' not null ,key_member_customer_id varchar(11) comment '核心企业',
group_status varchar(4) comment '集团状态\n N正常/S');

insert into customer_info(customer_id,customer_name,customer_status,input_date) values('20180707002','汽车新能源','正常','2018/07/07');

update customer_info set customer_name='创新集团' where customer_id='20180808003';

select customer_name from customer_info where customer_name='高新集团';

delete from group_info where group_id='201906000003';

create table employee(id nvarchar(10) primary key,sex varchar(2),name varchar(10),departmentid varchar(10),address varchar(50),birthdate date,postcode varchar(10) null ,salary double,
workdate date,remark varchar(50) null );

insert into employee (id,sex,name,departmentid,address,birthdate,postcode,salary,workdate,remark) values (0023,'男','牛逼','211','牛逼省','2009/07/07','53422',22223.77,'2022-07-08',null),
                                                                                                         (1023,'女','牛逼sile','911','牛逼省','2012/07/07','53492',229223.77,'2022-09-08',null);

select * from employee where id like '%02%';

select departmentid,max(TIMESTAMPDIFF(YEAR ,birthdate,CURDATE())) as '最大年龄',min(TIMESTAMPDIFF(YEAR ,birthdate,CURDATE())) as '最小年龄' from employee group by departmentid;
# SELECT  TIMESTAMPDIFF(YEAR, @birthday, CURDATE())

select departmentid,count(sex='男' or null) as '男',count(sex='女' or null) as '女' from employee group by departmentid;

select count(sex='男' or null) as '男' ,count(sex='女' or null) as '女' from employee ;