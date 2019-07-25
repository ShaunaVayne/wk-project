package com.redis.demo.util;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisTemplateUtil {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	public void set(String key, Object value, Long time) {
		if (value.getClass().equals(String.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Integer.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Double.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Float.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Short.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Long.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else if (value.getClass().equals(Boolean.class)) {
			redisTemplate.opsForValue().set(key, value.toString());
		} else {
			redisTemplate.opsForValue().set(key, value);
		}
		if (time != null) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	/**
	 * 获取锁
	 *
	 * @param key
	 * @param lock
	 * @param time
	 * @return
	 */
	public boolean setLock(String key, String lock, Long time) {
		boolean flag = redisTemplate.opsForValue().setIfAbsent(key, lock);
		if (flag) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
		return flag;
	}


	// 操作hash
	public <T> void hashSet(String key, Map<String, Object> map, Long time) {
		redisTemplate.opsForHash().putAll(key, map);
		if (time != null) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}


	public <T> void hashAdd(String key, String field, T obj) {
		redisTemplate.opsForHash().put(key, field, obj);
	}

	public Map<Object, Object> getHash(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public <T> Map<String, T> mget(String key, Class<T> clazz) {
		BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
		return boundHashOperations.entries();
	}

	// 操作list
	public <T> void setList(String key, List<T> list, Long time){
		redisTemplate.opsForValue().set(key, list);
		if(time !=null ){
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	// 操作set
	public void sSet(String key, Object[] arr, Long time) {
		redisTemplate.opsForSet().add(key, arr);
		if (time != null) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	public Set<Object> getSet(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	public boolean isSetMember(String key, String value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	// 操作SortedSet
	public void zSet(String key, String value, double score, Long time) {
		boolean flag = redisTemplate.opsForZSet().add(key, value, score);
		if (flag && time != null) {
			redisTemplate.expire(key, time, TimeUnit.SECONDS);
		}
	}

	public void zIncr(String key, String value, double delta) {
		redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}

	public Set<Object> zRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	public Set<Object> getZReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	public Set<Object> getZRangeWithScore(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	public Set<ZSetOperations.TypedTuple<Object>> getZReverseRangeWithScore(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
	}

	public long getZCount(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}

	public double getZMemberScore(String key, String value) {
		return redisTemplate.opsForZSet().score(key, value);
	}

	//获取key
	public Object get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Class<T> clazz) {
		return (T) redisTemplate.boundValueOps(key).get();
	}

	public void del(String key) {
		redisTemplate.delete(key);
	}

	public boolean isExist(String key) {
		return redisTemplate.hasKey(key);
	}

	public <T>List<T> hashValue(String key,Class<T> clazzz) {
		List<T> result = redisTemplate.opsForHash().values(key).stream().map(e -> {
			return JSON.parseObject(e + "", clazzz);
		}).collect(Collectors.toList());
		return result;
	}

	public Object hashGet(String redisKey, String hashKey) {
		return redisTemplate.opsForHash().get(redisKey, hashKey);
	}

	public Long increment(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}

	public Long rightPush(String key,Object value){
		return redisTemplate.opsForList().rightPush(key, value);
	}

	public Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 判断缓存是否存在
	 * @param key
	 * @return
	 */
	public Boolean hashKey(String key) {
		Boolean res = false;
		Set<Object> keys = redisTemplate.opsForHash().keys(key);
		if(keys != null && keys.size() > 0) {
			res = true;
		}
		return res;
	}

}
