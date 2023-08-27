package com.wxy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("新增犯罪记录数据模型")
public class CrimeDto implements Serializable {

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("受害者人数")
    private Integer Victims;

    @ApiModelProperty("犯罪代号")
    private String offenceCode;

    @ApiModelProperty("发生时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @ApiModelProperty("派单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dispatchTime;
}
