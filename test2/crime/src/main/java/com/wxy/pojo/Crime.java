package com.wxy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("test1_crime")
public class Crime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("incident_id")
    private Integer incidentId;

    @TableField("offence_code")
    private String offenceCode;

    @TableField("dispatch_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dispatchTime;

    @TableField("victims")
    private Integer victims;

    @TableField("crime_name1")
    private String crimeName1;

    @TableField("crime_name2")
    private String crimeName2;

    @TableField("crime_name3")
    private String crimeName3;

    @TableField("city")
    private String city;

    @TableField("start_date_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

}
