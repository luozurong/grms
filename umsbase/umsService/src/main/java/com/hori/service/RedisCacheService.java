
package com.hori.service;



import java.util.concurrent.TimeUnit;

/**
 * redis缓存
 * 可以模仿ehcache缓存 的写法 
 */
public interface RedisCacheService {
	
	
	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) ;

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern);

	/**
	 * 清空所有缓存
	 */
	public void clearCache() ;
	
	
	
	 /**
     * 通过key删除
     * 
     * @param key
     */
    public  long delKeys(String... keys);
    
    /**
     * 通过key删除
     * 
     * @param key
     */
    public  boolean del(String keys);

    /**
     * 添加key value 并且设置存活时间(byte)
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    public  void set(byte[] key, byte[] value, long liveTime);

    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     *            单位秒
     */
    public  void set(String key, String value, long liveTime);

    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public  void set(String key, String value);
    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public  boolean setnx(String key, String value,final long expire);
    
    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    public  boolean setex(String key, String value,final long expireTime);

    /**
     * 添加key value (字节)(序列化)
     * 
     * @param key
     * @param value
     */
    public  void set(byte[] key, byte[] value);

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    public  String get(String key);

   
    /**
     * @param key
     * @param value
     * @return
     */
    public String getSet(String key,String value) ;
    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public  boolean exists(String key);

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public  String flushDB();

    /**
     * 查看redis里有多少数据
     */
    public  long dbSize();

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public  String ping();
	
    
    public boolean expire(String key,long  timeout,TimeUnit timeUnit);
    
    public long incr(String key,  long  timeout,TimeUnit timeUnit);
    
}
