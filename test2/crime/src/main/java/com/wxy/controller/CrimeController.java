package com.wxy.controller;

import com.wxy.common.Result;
import com.wxy.dto.CrimeDto;
import com.wxy.service.ICrimeService;
import com.wxy.vo.CrimeStatVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crime")
@Api(tags = "犯罪记录相关接口")
public class CrimeController {

    @Autowired
    private ICrimeService crimeService;

    @GetMapping("/stat")
    @ApiOperation("城市风险指数统计")
    public Result<List> stat() {
        //
        List result = crimeService.getStat();

        return Result.success(result);
    }

    @PostMapping
    @ApiOperation("新增犯罪记录")
    public Result insertCrime(@RequestBody CrimeDto crimeDto) {

        Map map = crimeService.insertCrime(crimeDto);

        return Result.success(map);
    }
}
