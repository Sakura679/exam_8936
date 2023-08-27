package com.wxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxy.dto.CrimeDto;
import com.wxy.pojo.Crime;

import java.util.List;
import java.util.Map;

public interface ICrimeService extends IService<Crime> {

    /**
     * 城市风险指数统计
     *
     * @return
     */
    List getStat();

    /**
     * 新增犯罪记录
     *
     * @param crimeDto
     * @return
     */
    Map insertCrime(CrimeDto crimeDto);
}
