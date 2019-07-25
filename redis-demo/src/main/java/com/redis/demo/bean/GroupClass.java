package com.redis.demo.bean;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class GroupClass implements Serializable {

    private static final long serialVersionUID = 8584114395172145817L;

    private Long id;

    /**
     * 团报id
     */
    private Long groupId;

    /**
     * 班号
     */
    private String classCode;

    /**
     * 校区
     */
    private String areaName;

    /**
     * 年级
     */
    private String grade;

    /**
     * 上课时间
     */
    private String classTime;

    /**
     * 上课时段
     */
    private String classPeriod;

    private Date createTime;

    private String createId;

    private Date updateTime;

    private String updateId;

    private Boolean isDelete;

    public GroupClass(String classCode, String areaName, String grade, String classTime, String classPeriod) {
        this.classCode = classCode;
        this.areaName = areaName;
        this.grade = grade;
        this.classTime = classTime;
        this.classPeriod = classPeriod;
    }

    public GroupClass() {
    }
}