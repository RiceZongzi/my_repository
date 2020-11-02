-- 2020/11/02
-- use springbootdemo;

-- init
-- 建表
-- 学生表
CREATE TABLE `student`
(
    `s_id`    VARCHAR(20),
    `s_name`  VARCHAR(20) NOT NULL DEFAULT '',
    `s_birth` VARCHAR(20) NOT NULL DEFAULT '',
    `s_sex`   VARCHAR(10) NOT NULL DEFAULT '',
    PRIMARY KEY (`s_id`)
);
-- 课程表
CREATE TABLE `course`
(
    `c_id`   VARCHAR(20),
    `c_name` VARCHAR(20) NOT NULL DEFAULT '',
    `t_id`   VARCHAR(20) NOT NULL,
    PRIMARY KEY (`c_id`)
);
-- 教师表
CREATE TABLE `teacher`
(
    `t_id`   VARCHAR(20),
    `t_name` VARCHAR(20) NOT NULL DEFAULT '',
    PRIMARY KEY (`t_id`)
);
-- 成绩表
CREATE TABLE `score`
(
    `s_id`    VARCHAR(20),
    `c_id`    VARCHAR(20),
    `s_score` INT(3),
    PRIMARY KEY (`s_id`, `c_id`)
);
-- 插入学生表测试数据
insert into student
values ('01', '赵雷', '1990-01-01', '男');
insert into student
values ('02', '钱电', '1990-12-21', '男');
insert into student
values ('03', '孙风', '1990-05-20', '男');
insert into student
values ('04', '李云', '1990-08-06', '男');
insert into student
values ('05', '周梅', '1991-12-01', '女');
insert into student
values ('06', '吴兰', '1992-03-01', '女');
insert into student
values ('07', '郑竹', '1989-07-01', '女');
insert into student
values ('08', '王菊', '1990-01-20', '女');
-- 课程表测试数据
insert into course
values ('01', '语文', '02');
insert into course
values ('02', '数学', '01');
insert into course
values ('03', '英语', '03');

-- 教师表测试数据
insert into teacher
values ('01', '张三');
insert into teacher
values ('02', '李四');
insert into teacher
values ('03', '王五');

-- 成绩表测试数据
insert into score
values ('01', '01', 80);
insert into score
values ('01', '02', 90);
insert into score
values ('01', '03', 99);
insert into score
values ('02', '01', 70);
insert into score
values ('02', '02', 60);
insert into score
values ('02', '03', 80);
insert into score
values ('03', '01', 80);
insert into score
values ('03', '02', 80);
insert into score
values ('03', '03', 80);
insert into score
values ('04', '01', 50);
insert into score
values ('04', '02', 30);
insert into score
values ('04', '03', 20);
insert into score
values ('05', '01', 76);
insert into score
values ('05', '02', 87);
insert into score
values ('06', '01', 31);
insert into score
values ('06', '03', 34);
insert into score
values ('07', '02', 89);
insert into score
values ('07', '03', 98);

-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
select s.*, sc1.s_score, sc2.s_score
from student s,
     score sc1,
     score sc2
where sc1.c_id = '01'
  and sc2.c_id = '02'
  and s.s_id = sc1.s_id
  and s.s_id = sc2.s_id
  and sc1.s_score > sc2.s_score;
-- 2、查询"01"课程比"02"课程成绩低的学生的信息及课程分数
select s.*, sc1.s_score, sc2.s_score
from student s
         left join score sc1 on s.s_id = sc1.s_id and sc1.c_id = '01' or sc1.c_id IS NULL
         join score sc2 on s.s_id = sc2.s_id and sc2.c_id = '02'
where sc1.s_score < sc2.s_score;
-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
-- 1)
select s.*, sc1.s_score, round(avg(sc1.s_score), 2)
from student s,
     score sc1
where s.s_id = sc1.s_id
group by s.s_id, s.s_name
having round(avg(sc1.s_score), 2) >= 60;
-- 隐式连接，其实是一回事儿
-- 2)
select s.s_id, s.s_name, ROUND(AVG(sc1.s_score), 2)
from student s
         join score sc1 on s.s_id = sc1.s_id
GROUP BY s.s_id, s.s_name
HAVING ROUND(AVG(sc1.s_score), 2) >= 60;
-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
-- (包括有成绩的和无成绩的)
select s.s_id, s.s_name, ROUND(AVG(sc.s_score), 2) as avg_score
from student s
         left join score sc on s.s_id = sc.s_id
group by s.s_id, s.s_name
having avg_score < 60
union
-- UNION 操作符用于合并两个或多个 SELECT 语句的结果集。
-- UNION 内部的每个 SELECT 语句必须拥有相同数量的列、相同的列的顺序、列也必须拥有相似的数据类型。
select s.s_id, s.s_name, 0 as avg_score
from student s
where s.s_id not in (select distinct s_id from score);
-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
select s.s_id, s.s_name, count(sc.s_id), sum(sc.s_score)
from student s
         left join score sc on s.s_id = sc.s_id
group by s.s_id;
-- 6、查询"李"姓老师的数量
select count(t.t_id)
from teacher t
where t.t_name like '李%';
-- 7、查询学过"张三"老师授课的同学的信息
select s.*
from student s
         left join score sc on s.s_id = sc.s_id
where sc.c_id in (select c.c_id -- in 和 = 均可，但考虑到可能有老师兼课的情况
                  from course c
                           left join teacher t on c.t_id = t.t_id
                  where t.t_name = '张三');
-- 8、查询没学过"张三"老师授课的同学的信息
select *
from student
where s_id not in (select s.s_id
                   from student s
                            left join score sc on s.s_id = sc.s_id
                   where sc.c_id =
                         (select c.c_id
                          from course c
                                   left join teacher t on c.t_id = t.t_id
                          where t.t_name = '张三'));
-- 9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息
-- 1) 比较扯淡，没啥代表性，也没啥思路可归纳
select s.*
from student s
         left join (select * from score where c_id in ('01', '02')) sc on s.s_id = sc.s_id
group by s.s_id
having count(s.s_id) = 2;
-- 2)
select s.*
from student s,
     score sc1,
     score sc2
where s.s_id = sc1.s_id
  and s.s_id = sc2.s_id
  and sc1.c_id = '01'
  and sc2.c_id = '02';
-- 3)
select s.*
from student s
         left join score sc1 on s.s_id = sc1.s_id
         left join score sc2 on s.s_id = sc2.s_id
where sc1.c_id = '01'
  and sc2.c_id = '02';
-- 4) 按照这个sql思路，下一题变迎刃而解
select s.*
from student s
where s.s_id in (select sc1.s_id from score sc1 where sc1.c_id = '01')
  and s.s_id in (select sc2.s_id from score sc2 where sc2.c_id = '02');
-- 10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息
select s.*
from student s
where s.s_id in (select sc1.s_id from score sc1 where sc1.c_id = '01')
  and s.s_id not in (select sc2.s_id from score sc2 where sc2.c_id = '02');
-- 11、查询没有学全所有课程的同学的信息
select s.*
from student s
         left join score sc on s.s_id = sc.s_id
group by s.s_id
having count(s.s_id) < (select count(c.c_id) from course c);
-- 12、查询至少有一门课与学号为"01"的同学所学相同的同学的信息
-- 1)
select distinct s.*
from student s
         left join score sc2 on s.s_id = sc2.s_id
where sc2.c_id in (select sc1.c_id from score sc1 where sc1.s_id = '01');
-- 2) 这个不用联表，更好一点
select *
from student
where s_id in (
    select distinct a.s_id
    from score a
    where a.c_id in (
        select a.c_id
        from score a
        where a.s_id = '01')
);
-- 13、查询和"01"号的同学学习的课程完全相同的其他同学的信息
-- 1)
SELECT Student.*
FROM Student
WHERE s_id IN (SELECT s_id
               FROM Score
               GROUP BY s_id
               HAVING COUNT(s_id) = (
                   #下面的语句是找到'01'同学学习的课程数
                   SELECT COUNT(c_id)
                   FROM Score
                   WHERE s_id = '01'
               )
)
  AND s_id NOT IN (
    #下面的语句是找到学过‘01’同学没学过的课程，有哪些同学。并排除他们
    SELECT s_id
    FROM Score
    WHERE c_id IN (
        #下面的语句是找到‘01’同学没学过的课程
        SELECT DISTINCT c_id
        FROM Score
        WHERE c_id NOT IN (
            #下面的语句是找出‘01’同学学习的课程
            SELECT c_id
            FROM Score
            WHERE s_id = '01'
        )
    )
    GROUP BY s_id
) #下面的条件是排除01同学
  AND s_id NOT IN ('01');
-- 2) 没看懂，group_concat()将group by产生的同一个分组中的值连接起来，返回一个字符串结果
SELECT t3.*
FROM (
         SELECT s_id,
                group_concat(c_id ORDER BY c_id) group1
         FROM score
         WHERE s_id <> '01'
         GROUP BY s_id
     ) t1
         INNER JOIN (
    SELECT group_concat(c_id ORDER BY c_id) group2
    FROM score
    WHERE s_id = '01'
    GROUP BY s_id
) t2 ON t1.group1 = t2.group2
         INNER JOIN student t3 ON t1.s_id = t3.s_id;
-- 14、查询没学过"张三"老师讲授的任一门课程的学生姓名
select s.s_name
from student s
# 不在此列之学生姓名
where s.s_id not in (
    # 选张三考试之课之学生id
    select distinct sc.s_id
    from score sc
    where sc.c_id in (
        # 张三老师所教之课
        select c.c_id
        from course c
        where c.t_id in (
            # 名为张三老师之id
            select t.t_id
            from teacher t
            where t.t_name = '张三')));
-- 15、查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩
-- 16、检索"01"课程分数小于60，按分数降序排列的学生信息
-- 17、按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
-- 18.查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
-- 19、按各科成绩进行排序，并显示排名
-- 20、查询学生的总成绩并进行排名
-- 21、查询不同老师所教不同课程平均分从高到低显示
-- 22、查询所有课程的成绩第2名到第3名的学生信息及该课程成绩
-- 23、统计各科成绩各分数段人数：课程编号,课程名称,[100-85],[85-70],[70-60],[0-60]及所占百分比
-- 24、查询学生平均成绩及其名次
-- 25、查询各科成绩前三名的记录
-- 26、查询每门课程被选修的学生数
-- 27、查询出只有两门课程的全部学生的学号和姓名
-- 28、查询男生、女生人数
-- 29、查询名字中含有"风"字的学生信息
-- 30、查询同名同性学生名单，并统计同名人数
-- 31、查询1990年出生的学生名单
-- 32、查询每门课程的平均成绩，结果按平均成绩降序排列，平均成绩相同时，按课程编号升序排列
-- 33、查询平均成绩大于等于85的所有学生的学号、姓名和平均成绩
-- 34、查询课程名称为"数学"，且分数低于60的学生姓名和分数
-- 35、查询所有学生的课程及分数情况；
-- 36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数；
-- 37、查询不及格的课程
-- 38、查询课程编号为01且课程成绩在80分以上的学生的学号和姓名；
-- 39、求每门课程的学生人数
-- 40、查询选修"张三"老师所授课程的学生中，成绩最高的学生信息及其成绩
-- 41、查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩
-- 42、查询每门功成绩最好的前两名
-- 43、统计每门课程的学生选修人数（超过5人的课程才统计）。要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列
-- 44、检索至少选修两门课程的学生学号
-- 45、查询选修了全部课程的学生信息
-- 46、查询各学生的年龄
-- 47、查询本周过生日的学生
-- 48、查询下周过生日的学生
-- 49、查询本月过生日的学生
-- 50、查询下月过生日的学生