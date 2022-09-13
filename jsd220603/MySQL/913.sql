create table trade(id int primary key auto_increment,money int ,type varchar(10),time date,p_id int);
create table person(id int primary key auto_increment,name varchar(20),gender char(1),rel varchar(10));
insert into person values(null,'刘德华','男','亲戚'),(null,'杨幂','女','亲戚'),(null,'马云','男','同事'),(null,'特朗普','男','朋友'),(null,'貂蝉','女','朋友');
insert into trade values(null,1000,'微信','2021-03-20',1),

                        (null,500,'现金','2021-04-14',2),(null,-50,'现金','2021-04-14',2),

                        (null,20000,'支付宝','2021-03-11',3),(null,-5,'支付宝','2021-03-11',3),

                        (null,2000,'微信','2021-05-18',4),

                        (null,-20000,'微信','2021-07-22',5);

select sum(money) from trade where time>2021-02-15;
select name,money from trade t join person p on t.p_id=p.id where money not between -100 and 100 and gender='女' and rel='亲戚' and time>2021-02-15   ;
