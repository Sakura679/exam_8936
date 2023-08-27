package com.wxy.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询犯罪记录返回的数据模型")
public class CrimeStatVO implements Serializable {

    @TableField("Year")
    @ApiModelProperty("年份")
    private String year;

    @TableField("City")
    @ApiModelProperty("城市")
    private String city;

    @TableField("RiskIndex")
    @ApiModelProperty("犯罪指数")
    private Double riskIndex;

    @TableField("crime_name3")
    @ApiModelProperty("罪名")
    private String crimeName;

    @TableField("CrimeCount")
    @ApiModelProperty("犯罪记录数量")
    private Integer crimeCount;
}
