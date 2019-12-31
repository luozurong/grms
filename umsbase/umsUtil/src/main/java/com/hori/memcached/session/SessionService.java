package com.hori.memcached.session;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;


public class SessionService {
	//创建全局的唯一实例
	protected static MemCachedClient mcc = null;
	
	private static SessionService instance = null;

	private static SockIOPool pool = null;

	private static String poolName = "sidsock";
	//超时时间设置为8小时
	private long expiryTime = 8*60;// ministes
	
	private static String serverlist = "127.0.0.1:11211";
	
	
	static {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = cl.getResourceAsStream("memcached.properties");
		Properties props = new Properties();
		try {
			props.load(input);
			serverlist = props.getProperty("serverlist", serverlist);
			poolName = props.getProperty("poolname", poolName);
			
			//服务器列表和其权重
			String[] servers = serverlist.split(",");
			//Integer[] weights = {3,4};
			
			//获取socke连接池的实例对象
			pool = SockIOPool.getInstance(poolName);
			
			//设置服务器信息
			pool.setServers(servers);
			//pool.setWeights(weights);
			
			//设置初始连接数、最小和最大连接数以及最大处理时间
			pool.setInitConn(5);
			pool.setMinConn(5);
			pool.setMaxConn(250);
			//pool.setMaxIdle(1000*60*60*6);
			
			//设置主线程的睡眠时间
			pool.setMaintSleep(30);
			
			//设置TCP的参数，连接超时等
			pool.setNagle(false);
			pool.setSocketTO(3000);
			//pool.setSocketConnectTO(0);
			
			pool.setFailover(true);
			pool.setFailback(true);
			pool.setAliveCheck(true);
			pool.initialize();
			
			//压缩设置，超过指定大小（单位为K）的数据都会被压缩。
			
			mcc = new MemCachedClient(poolName);
			//mcc.setCompressEnable(false);
			//mcc.setCompressThreshold(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得超时时间
	 * 
	 * @return
	 */
	private Date getExpiryDate() {
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		time += expiryTime * 60 * 1000;
		calendar.setTimeInMillis(time);

		return calendar.getTime();
	}

	/**
	 * 单例模式创建SessionService对象
	 * 
	 * @return
	 */
	public static SessionService getInstance() {
		if (instance == null) {
			instance = new SessionService();
		}
		return instance;
	}

	/**
	 * 构造函数初始化
	 */
	protected SessionService() {

	}

	
	public static void main(String[] args){
		SessionService.getInstance();
	}
	/**
	 * 判断Memc中是否存在该session
	 * 
	 * @param id
	 * @return
	 */
	public boolean sessionExists(String id) {
		return mcc.keyExists(id);
	}

	@SuppressWarnings("unchecked")
	public Object get(String key, boolean create) {
		Object value = null; 
		if (mcc.keyExists(key)) {
			value =  mcc.get(key);
			mcc.replace(key,value, getExpiryDate());
		}
		
		return value;
	}

	/**
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void save(String key, Object value) {
		
		if (mcc.keyExists(key)) {
			
				mcc.replace(key, value, getExpiryDate());
				
			
		} else {
			
			mcc.add(key, value, getExpiryDate());
			
		}
	}

	@SuppressWarnings("unchecked")
	public void saveOutTime(String key, Object value, Date expiryDate) {
		if (mcc.keyExists(key)) {
			
			mcc.replace(key, value, expiryDate);
			
		} else {
			
			mcc.add(key, value, expiryDate);
			
		}
	}
	

	public void remove(String key) {
		mcc.delete(key);
	}

	@SuppressWarnings("unchecked")
	public void updateExpiryDate(String id) {
	
		mcc.replace(id, getExpiryDate());
		
	}


	@SuppressWarnings("static-access")
	protected void finalize() {
		if (this.pool != null) {
			this.pool.shutDown();
		}
	}

	public static MemCachedClient getMcc(){
		return mcc;
	}
	
}
