package com.hori.grms.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hori.grms.service.RedisCacheService;


@Service("redisCacheService")
public class RedisCacheServiceImpl implements RedisCacheService{
	public final static String CAHCENAME="cache";//缓存名
	public final static int CAHCETIME=24*60*60;//默认缓存时间 一天

	private static final Logger logger = Logger.getLogger(RedisCacheServiceImpl.class);
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	


	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	/**
	 * 清空所有缓存
	 */
	public void clearCache() {
		deleteCacheWithPattern(CAHCENAME+"|*");
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
     * @param key
     */
    public long delKeys(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }
    /**
     * @param key
     */
    public boolean del(final String keys) {
    	return redisTemplate.execute(new RedisCallback<Long>() {
    		public Long doInRedis(RedisConnection connection) throws DataAccessException {
    			return connection.del(keys.getBytes());
    		}
    	}) == 1;
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                   // return new String(connection.get(key.getBytes()));
                	 byte[] result = connection.get(key.getBytes());
       	            if(result!=null){
       	               return new String(result);
       	            }
       	            return null;
                } catch (DataAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

  
    /**
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * @return
     */
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
    
    /**
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key,String value) {
    	final byte[] bkey = key.getBytes();
		final byte[] bvalue = value.getBytes();
    	return redisTemplate.execute(new RedisCallback<String>() {
    		public String doInRedis(RedisConnection connection) throws DataAccessException {
    			  byte[] result = connection.getSet(bkey,
    					  bvalue);
    	            if(result!=null){
    	               return new String(result);
    	            }
    	            return null;
    		}
    	});
    }

    /**
     * @return
     */
    public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.ping();
            }
        });
    }


	public boolean setnx(String key, String value,final long expire) {
		// TODO Auto-generated method stub
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = value.getBytes();
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				boolean locked =  connection.setNX(bkey, bvalue);
				//true 证明有值 直接做个超时 防止死锁
				if(locked)
				connection.expire(bkey, expire);
				return locked;
			}
		});
		return result;
	}

	
	public boolean setex(String key, final String value, final long expireTime) {
		// TODO Auto-generated method stub
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = value.getBytes();
		boolean result =redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
		return result;
	}

	@Override
	public boolean expire(String key, long timeout, TimeUnit timeUnit) {
		
		return redisTemplate.expire(key, timeout, timeUnit);
	}

	@Override  
    public long incr(String key, long  timeout,TimeUnit timeUnit) {  
		final byte[] bkey = key.getBytes();
        return redisTemplate.execute(new RedisCallback<Long>() {  
            @Override  
            public Long doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
  
                Long incr = 0L;  
                try {  
                    incr = connection.incr(bkey); 
                    expire(key, timeout, timeUnit);
                } catch (Exception e) {  
                    logger.error("com.utils.redis.impl.RedisCommonDaoImpl incr "  
                                    + e);  
                    throw (DataAccessException)e;  
                }  
                return incr;  
            }  
        });  
    }  
}
