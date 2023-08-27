package com.wxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxy.dto.CrimeDto;
import com.wxy.exception.BaseException;
import com.wxy.mapper.CrimeMapper;
import com.wxy.pojo.Crime;
import com.wxy.service.ICrimeService;
import com.wxy.vo.CrimeStatVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 放学后海堤日记
 * @Date: 2023/8/27 16:04
 * @Desc:
 */
@Service
public class CrimeServiceImpl extends ServiceImpl<CrimeMapper, Crime> implements ICrimeService {

    @Autowired
    private CrimeMapper crimeMapper;

    /**
     * 城市风险指数统计
     *
     * @return
     */
    @Override
    public List getStat() {

        ArrayList<Map<String, Object>> result = new ArrayList<>();

        List<CrimeStatVO> statList = crimeMapper.getStat();

        List<String> list = statList.stream().map(crimeStatVO -> crimeStatVO.getYear()).distinct().collect(Collectors.toList());

        for (String year : list) {
            HashMap<String, Object> stat = new HashMap<>();

            ArrayList<Map<String, Object>> riskTop3 = new ArrayList<>();
            stat.put("riskTop3", riskTop3);

            List<String> list1 = statList.stream().filter(crimeStatVO -> {
                if (crimeStatVO.getYear().equals(year))
                    return true;
                return false;
            }).map(crimeStatVO -> crimeStatVO.getCity()).distinct().collect(Collectors.toList());

            int rank = 1;

            for (String city : list1) {
                HashMap<String, Object> map = new HashMap<>();
                ArrayList<String> crimeTop3 = new ArrayList<>();

                List<CrimeStatVO> list2 = statList.stream().filter(crimeStatVO -> {
                    if (crimeStatVO.getYear().equals(year) && crimeStatVO.getCity().equals(city))
                        return true;
                    return false;
                }).limit(3).collect(Collectors.toList());

                for (CrimeStatVO crimeStatVO : list2) {
                    map.put("city", crimeStatVO.getCity());
                    map.put("riskIndex", crimeStatVO.getRiskIndex());
                    map.put("rank", rank);
                    crimeTop3.add(crimeStatVO.getCrimeName());
                }

                rank++;

                map.put("crimeTop3", crimeTop3);

                riskTop3.add(map);
            }

            rank = 1;

            result.add(stat);

            stat.put("year", year);
        }

        return result;
    }

    /**
     * 新增犯罪记录
     *
     * @param crimeDto
     * @return
     */
    @Override
    public Map insertCrime(CrimeDto crimeDto) {

        if (crimeDto == null)
            throw new BaseException("参数异常");

        if (crimeDto.getStartDateTime().getYear() < 2020)
            throw new BaseException("参数不合法");

        crimeDto.setCity(crimeDto.getCity().toUpperCase());

        Crime crime = Crime.builder()
                .victims(crimeDto.getVictims())
                .build();

        BeanUtils.copyProperties(crimeDto, crime);

        crimeMapper.insert(crime);

        HashMap<String, Object> map = new HashMap<>();
        map.put("incidentID", crime.getIncidentId());

        return map;
    }
}
