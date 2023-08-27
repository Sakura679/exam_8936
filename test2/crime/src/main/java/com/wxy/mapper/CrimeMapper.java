package com.wxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxy.pojo.Crime;
import com.wxy.vo.CrimeStatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CrimeMapper extends BaseMapper<Crime> {

    /**
     * 城市风险指数统计
     *
     * @return
     */
    @Select("WITH YearlyStats AS (\n" +
            "    SELECT \n" +
            "        YEAR(start_date_time) AS Year,\n" +
            "\t\t\t\tCity,\n" +
            "        COUNT(*)/365.0 AS AvgDailyIncidents,\n" +
            "        SUM(Victims)/365.0 AS AvgDailyVictims,\n" +
            "        (COUNT(*) * 0.8 + SUM(Victims) * 0.2)/365.0 AS RiskIndex -- 计算风险指数\n" +
            "    FROM \n" +
            "        test1_crime\n" +
            "    GROUP BY \n" +
            "        City,\n" +
            "        YEAR(start_date_time)\n" +
            "\t\tORDER BY\n" +
            "\t\t\t\tYear,\n" +
            "\t\t\t\tRiskIndex DESC\n" +
            "),\n" +
            "TopCities AS (\n" +
            "    SELECT \n" +
            "        Year,\n" +
            "        City,\n" +
            "        RiskIndex\n" +
            "    FROM \n" +
            "        (\n" +
            "            SELECT \n" +
            "                Year,\n" +
            "                City,\n" +
            "                RiskIndex,\n" +
            "                DENSE_RANK() OVER (PARTITION BY Year ORDER BY RiskIndex DESC) AS rnk\n" +
            "            FROM \n" +
            "                YearlyStats\n" +
            "        ) AS RankedCities\n" +
            "    WHERE \n" +
            "        rnk <= 3\n" +
            ")\n" +
            "\n" +
            "SELECT\n" +
            "    t.Year,\n" +
            "    t.City,\n" +
            "\t\tt.RiskIndex,\n" +
            "    c.crime_name3 CrimeName,\n" +
            "    COUNT(*) AS CrimeCount\n" +
            "FROM \n" +
            "    test1_crime c\n" +
            "JOIN \n" +
            "    TopCities t ON c.City = t.City AND YEAR(c.start_date_time) = t.Year\n" +
            "GROUP BY\n" +
            "    t.Year,\n" +
            "    t.City,\n" +
            "    c.crime_name3\n" +
            "ORDER BY\n" +
            "    t.Year,\n" +
            "\t\tt.RiskIndex DESC,\n" +
            "    t.City,\n" +
            "    CrimeCount DESC")
    List<CrimeStatVO> getStat();
}
