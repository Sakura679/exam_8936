-- 建表
CREATE TABLE `test1_crime` (
                               `incident_id` int NOT NULL AUTO_INCREMENT COMMENT '事件编码',
                               `offence_code` varchar(5) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '犯罪代号',
                               `dispatch_time` datetime DEFAULT NULL COMMENT '派单时间',
                               `victims` int DEFAULT NULL COMMENT '受害者人数',
                               `crime_name1` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '罪名1',
                               `crime_name2` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '罪名2',
                               `crime_name3` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '罪名3',
                               `city` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '城市',
                               `start_date_time` datetime DEFAULT NULL COMMENT '发生时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- incident id 作为主键使用，如数据文件中有重复 incident id，则保留 start date time 最1晚的那一条，start date time 一样，就保留那个 offence Code 最大的
DELETE t1
FROM test1_crime t1
         JOIN test1_crime t2
              ON t1.incident_id = t2.incident_id
WHERE (t1.start_date_time < t2.start_date_time)
   OR (t1.start_date_time = t2.start_date_time AND t1.offence_code < t2.offence_code);
ALTER TABLE test1_crime ADD PRIMARY KEY (incident_id);

-- 所有字段不能为空，如果 dispatch time 值为空，则以 start date time 的值代替。如其它字段值为空，则删除本条记录
UPDATE test1_crime SET dispatch_time = start_date_time WHERE dispatch_time IS NULL;
DELETE FROM test1_crime WHERE offence_code IS NULL OR crime_name1 IS NULL OR crime_name2 IS NULL OR victims IS NULL OR crime_name3 IS NULL OR city IS NULL OR start_date_time IS NULL;

-- 删除 start date time 在 2020 年之前的所有记录
DELETE FROM test1_crime WHERE YEAR(start_date_time) < 2020;


-- 确保城市名称都为大写字母
UPDATE test1_crime SET city = UPPER(city);

-- 如有其它异常数据，也要删除或修正
DELETE FROM test1_crime WHERE victims < 0;