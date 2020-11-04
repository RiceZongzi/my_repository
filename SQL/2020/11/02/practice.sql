-- 2020/11/02
use springbootdemo;

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
select s.*, sc.s_score, round(avg(sc.s_score), 2)
from student s,
     score sc
where s.s_id = sc.s_id
group by s.s_id, s.s_name
having round(avg(sc.s_score), 2) >= 60;
-- 隐式连接，其实是一回事儿
-- 2)
select s.s_id, s.s_name, round(avg(sc.s_score), 2)
from student s
         join score sc on s.s_id = sc.s_id
group by s.s_id, s.s_name
having round(avg(sc.s_score), 2) >= 60;
-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
-- (包括有成绩的和无成绩的)
select s.s_id, s.s_name, round(avg(sc.s_score), 2) as avg_score
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
group by s.s_id, s.s_name;
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
select s.s_id, s.s_name, avg(sc2.s_score)
from student s
         left join score sc2 on s.s_id = sc2.s_id
where s.s_id in (
    select sc1.s_id from score sc1 where sc1.s_score < 60 group by sc1.s_id having count(sc1.s_id) >= 2)
group by s.s_id, s.s_name;
-- 16、检索"01"课程分数小于60，按分数降序排列的学生信息
select s.*, sc.s_score
from student s,
     score sc
where sc.c_id = '01'
  and sc.s_id = s.s_id
order by sc.s_score desc;
-- 17、按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩
select s.s_id,
       (select sc.s_score from score sc where sc.c_id = '01' and sc.s_id = s.s_id) as 语文,
       (select sc.s_score from score sc where sc.c_id = '02' and sc.s_id = s.s_id) as 数学,
       (select sc.s_score from score sc where sc.c_id = '03' and sc.s_id = s.s_id) as 英语,
       round(avg(s.s_score), 2)                                                    as 平均分
from score s
group by s.s_id
order by 平均分;
-- 18.查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率
-- 1)
select s.c_id,
       c.c_name,
       max(s_score)                                          as 最高分,
       min(s_score)                                          as 最低分,
       round(avg(s_score), 2)                                as 平均分,
       round(100 * (sum(case when s.s_score >= 60 then 1 else 0 end)
           / sum(case when s.s_score then 1 else 0 end)), 2) as 及格率,
       round(100 * (sum(case when s.s_score >= 70 and s.s_score <= 80 then 1 else 0 end)
           / sum(case when s.s_score then 1 else 0 end)), 2) as 中等率,
       round(100 * (sum(case when s.s_score >= 80 and s.s_score <= 90 then 1 else 0 end)
           / sum(case when s.s_score then 1 else 0 end)), 2) as 优良率,
       round(100 * (sum(case when s.s_score >= 90 then 1 else 0 end)
           / sum(case when s.s_score then 1 else 0 end)), 2) as 优秀率
from score s
         left join course c on s.c_id = c.c_id
GROUP BY s.c_id, c.c_name;
-- 2)
select s.c_id,
       c.c_name,
       max(s_score)                        as 最高分,
       min(s_score)                        as 最低分,
       round(avg(s_score), 2)              as 平均分,
       round(100 * (sum(if(s.s_score >= 60, 1, 0))
           / sum(if(s.s_score, 1, 0))), 2) as 及格率,
       round(100 * (sum(if(s.s_score >= 70 and s.s_score <= 80, 1, 0))
           / sum(if(s.s_score, 1, 0))), 2) as 中等率,
       round(100 * (sum(if(s.s_score >= 80 and s.s_score <= 90, 1, 0))
           / sum(if(s.s_score, 1, 0))), 2) as 优良率,
       round(100 * (sum(if(s.s_score >= 90, 1, 0))
           / sum(if(s.s_score, 1, 0))), 2) as 优秀率
from score s
         left join course c on s.c_id = c.c_id
GROUP BY s.c_id, c.c_name;
-- 19、按各科成绩进行排序，并显示排名
-- 1) 好像不太对
select a.c_id,
       a.s_id,
       @i := @i + 1                                             as i保留排名,
       @k := (case when @score = a.s_score then @k else @i end) as rank不保留排名,
       @score := a.s_score                                      as score
from (
         select s_id, c_id, s_score from score sc GROUP BY s_id, c_id, s_score ORDER BY s_score DESC
     ) a,
     (select @k := 0, @i := 0, @score := 0) s;
-- 2）稍加修改
select @i := @i + 1                                             as 序号,
       a.s_id                                                   as 学号,
       a.c_id                                                   as 科目,
       @l := (case when @score = a.s_score then @l else @i end) as 排名,
       @score := a.s_score                                      as 分数
from (
         select sc.s_id, sc.c_id, sc.s_score
         from score sc
         where sc.c_id = '01'
         GROUP BY sc.s_id, sc.c_id, sc.s_score
         ORDER BY sc.s_score DESC
     ) a,
     (select @l := 0, @i := 0, @score := 0) s
union
select @j := @j + 1                                             as 序号,
       a.s_id                                                   as 学号,
       a.c_id                                                   as 科目,
       @m := (case when @score = a.s_score then @m else @j end) as 排名,
       @score := a.s_score                                      as 分数
from (
         select sc.s_id, sc.c_id, sc.s_score
         from score sc
         where sc.c_id = '02'
         GROUP BY sc.s_id, sc.c_id, sc.s_score
         ORDER BY sc.s_score DESC
     ) a,
     (select @m := 0, @j := 0, @score := 0) s
union
select @k := @k + 1                                             as 序号,
       a.s_id                                                   as 学号,
       a.c_id                                                   as 科目,
       @n := (case when @score = a.s_score then @n else @k end) as 排名,
       @score := a.s_score                                      as 分数
from (
         select sc.s_id, sc.c_id, sc.s_score
         from score sc
         where sc.c_id = '03'
         GROUP BY sc.s_id, sc.c_id, sc.s_score
         ORDER BY sc.s_score DESC
     ) a,
     (select @n := 0, @k := 0, @score := 0) s;
-- 20、查询学生的总成绩并进行排名
select @i := @i + 1                                               as 序号,
       a.s_id                                                     as 学号,
       @k := (case when @score = a.sum_score then @k else @i end) as 排名,
       @score := a.sum_score                                      as 总分
from (select sc.s_id, SUM(sc.s_score) as sum_score from score sc GROUP BY sc.s_id ORDER BY sum_score DESC) a,
     (select @k := 0, @i := 0, @score := 0) s;
-- 21、查询不同老师所教不同课程平均分从高到低显示
select t.t_id                                                               as 教师编号,
       t.t_name                                                             as 教师姓名,
       c.c_name                                                             as 课程名称,
       (select round(avg(s.s_score), 2) from score s where c.c_id = s.c_id) as 平均分
from teacher t
         left join course c on t.t_id = c.t_id
order by 平均分 desc;
-- 22、查询所有课程的成绩第2名到第3名的学生信息及该课程成绩
-- 1) 不加括号用不了 union，不加 limit 也用不了
(select s.*, sc.s_score
 from score sc
          left join student s on sc.s_id = s.s_id
 where sc.c_id = '01'
 order by s_score desc
 limit 1, 2)
union
(select s.*, sc.s_score
 from score sc
          left join student s on sc.s_id = s.s_id
 where sc.c_id = '02'
 order by s_score desc
 limit 1, 2)
union
(select s.*, sc.s_score
 from score sc
          left join student s on sc.s_id = s.s_id
 where sc.c_id = '03'
 order by s_score desc
 limit 1, 2);
-- 23、统计各科成绩各分数段人数：课程编号,课程名称,[100-85],[85-70],[70-60],[0-60]及所占百分比
select c.c_id,
       c.c_name,
       sum(if(s.s_score < 60, 1, 0))                                                        as `[0-60]`,
       round(100 * sum(if(s.s_score <= 60, 1, 0)) / count(s.s_score), 2)                    as `[0-60]%`,
       sum(if(s.s_score >= 60 and s_score <= 70, 1, 0))                                     as `[70-60]`,
       round(100 * sum(if(s.s_score >= 60 and s_score <= 70, 1, 0)) / count(s.s_score), 2)  as `[70-60]%`,
       sum(if(s.s_score >= 70 and s_score <= 85, 1, 0))                                     as `[85-70]`,
       round(100 * sum(if(s.s_score >= 70 and s_score <= 85, 1, 0)) / count(s.s_score), 2)  as `[85-70]%`,
       sum(if(s.s_score >= 85 and s_score <= 100, 1, 0))                                    as `[100-85]`,
       round(100 * sum(if(s.s_score >= 85 and s_score <= 100, 1, 0)) / count(s.s_score), 2) as `[100-85]%`
from course c
         left join score s on c.c_id = s.c_id
group by c.c_id, c.c_name;
-- 24、查询学生平均成绩及其名次
-- 1) 查询学生平均成绩
select s.s_id,
       s.s_name,
       avg(sc.s_score)
from student s
         left join score sc on s.s_id = sc.s_id
group by s.s_id, s_name;
-- 2) 查询学生平均成绩及其名次
select @i := @i + 1                             as 序号,
       a.s_id                                   as 学号,
       @score := a.avg_score                    as 平均成绩,
       @k := (if(@score = a.avg_score, @k, @i)) as 排名
from (select sc.s_id, avg(sc.s_score) as avg_score from score sc group by sc.s_id order by avg_score desc) a,
     (select @k := 0, @i := 0, @score := 0) s;
-- 25、查询各科成绩前三名的记录
(select * from score sc where sc.c_id = '01' order by s_score desc limit 0, 3)
union
(select * from score sc where sc.c_id = '02' order by s_score desc limit 0, 3)
union
(select * from score sc where sc.c_id = '03' order by s_score desc limit 0, 3);
-- 26、查询每门课程被选修的学生数
select c.c_id, c.c_name, count(s.s_id)
from course c
         left join score s on c.c_id = s.c_id
group by c.c_id, c.c_name;
-- 27、查询出只有两门课程的全部学生的学号和姓名

-- 28、查询男生、女生人数
select sum(if(s_sex = '男', 1, 0)) as boy,
       sum(if(s_sex = '女', 1, 0)) as girl
from student;
-- 29、查询名字中含有"风"字的学生信息
-- 30、查询同名同性学生名单，并统计同名人数
-- 1)
select s.s_name, s.s_sex, count(s.s_name)
from (select s1.s_name, s1.s_sex
      from student s1,
           student s2
      where s1.s_name = s2.s_name
        and s1.s_sex = s2.s_sex
        and s1.s_id <> s2.s_id
      group by s1.s_name, s1.s_sex) s;
-- 2)
select a.s_name, a.s_sex, count(*)
from student a
         JOIN student b on a.s_id != b.s_id and a.s_name = b.s_name and a.s_sex = b.s_sex
GROUP BY a.s_name, a.s_sex;
-- 31、查询1990年出生的学生名单
select *
from student s
where s.s_birth like '1990-%';
-- 32、查询每门课程的平均成绩，结果按平均成绩降序排列，平均成绩相同时，按课程编号升序排列
select s.c_id, avg(s.s_score)
from score s
group by s.c_id
order by avg(s.s_score) desc, s.c_id asc;
-- 33、查询平均成绩大于等于85的所有学生的学号、姓名和平均成绩
select s.s_id, s.s_name, avg(sc.s_score)
from student s
         left join score sc on s.s_id = sc.s_id
group by s.s_id, s.s_name
having avg(sc.s_score) > 85;
-- 34、查询课程名称为"数学"，且分数低于60的学生姓名和分数
-- 1)
select s.s_name, sc.s_score
from student s,
     score sc,
     course c
where sc.s_id = s.s_id
  and c.c_id = sc.c_id
  and c.c_name = '数学'
  and sc.s_score < 60;
-- 2)
select a.s_name, b.s_score
from score b
         join student a on a.s_id = b.s_id
where b.c_id = (
    select c_id
    from course
    where c_name = '数学')
  and b.s_score < 60;
-- 35、查询所有学生的课程及分数情况；
select s.s_id,
       s.s_name,
       sum(if(c.c_name = '语文', sc.s_score, 0)) as '语文',
       sum(if(c.c_name = '数学', sc.s_score, 0)) as '数学',
       sum(if(c.c_name = '英语', sc.s_score, 0)) as '英语',
       sum(sc.s_score)                         as '总分'
from student s
         left join score sc on s.s_id = sc.s_id
         left join course c on sc.c_id = c.c_id
GROUP BY s.s_id, s.s_name;
-- 36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数；
select s.s_name                                               as '姓名',
       (select c.c_name from course c where c.c_id = sc.c_id) as '课程名称',
       sc.s_score                                             as '分数'
from score sc
         left join student s on sc.s_id = s.s_id
where sc.s_score > 70;
-- 37、查询不及格的课程
select s.s_id, c.c_name, s.s_score
from score s
         left join course c on s.c_id = c.c_id
where s.s_score < 60;
-- 38、查询课程编号为01且课程成绩在80分以上的学生的学号和姓名；
select s.s_id, s.s_name
from score sc,
     student s
where sc.s_id = s.s_id
  and sc.c_id = '01'
  and sc.s_score > 80;
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
