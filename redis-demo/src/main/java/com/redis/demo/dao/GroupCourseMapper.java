package com.redis.demo.dao;

import com.redis.demo.bean.GroupCourse;

public interface GroupCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupCourse record);

    int insertSelective(GroupCourse record);

    GroupCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupCourse record);

    int updateByPrimaryKey(GroupCourse record);
}