package com.redis.demo.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class GroupCourse implements Serializable {

    private static final long serialVersionUID = 6386285863739578538L;

    private Long id;

    private String groupName;

    private Integer groupNumber;

    private Long groupPrice;

    private Long originalPrice;

    private String subject;

    private Long subjectId;

    private Date startTime;

    private Date endTime;

    private Boolean isBanner;

    private String smallImage;

    private String bigImage;

    private String remark;

    private Date createTime;

    private String createId;

    private Date updateTime;

    private String updateId;

    private Boolean isDelete;
}