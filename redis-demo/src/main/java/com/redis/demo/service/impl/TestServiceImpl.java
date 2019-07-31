package com.redis.demo.service.impl;

import com.redis.demo.bean.GroupCourse;
import com.redis.demo.dao.GroupCourseMapper;
import com.redis.demo.service.TestService;
import com.redis.demo.util.async.TestAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: WangKun
 * @Description:
 * @Date: Created in 2019/6/27 11:27 PM
 * @ProjectName: redis-demo
 * @Version: 1.0.0
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

	@Autowired
	private TestAsync testAsync;

	@Autowired
	private GroupCourseMapper groupCourseMapper;

	@Override
	public void testAsync() {
		log.info("testAsync--start");
		testAsync.test();
		log.info("testAsync--end");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void opt(Long id) {
		try {
			GroupCourse groupCourse = groupCourseMapper.selectByPrimaryKey(id);
			groupCourse.setGroupNumber(10);
			groupCourseMapper.updateByPrimaryKeySelective(groupCourse);
			//int a = 10 / 0;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
}
