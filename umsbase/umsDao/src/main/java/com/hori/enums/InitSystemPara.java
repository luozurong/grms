package com.hori.enums;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

@SuppressWarnings("rawtypes")
public class InitSystemPara {

	/**
	 * ALLOW_IP:允许访问的IP
	 * 
	 */
	public final static String ALLOW_IP = "allow_ip";
	
	/**
	 * XTGL:系统管理的模块
	 * 
	 */
	public final static String XTGL = "XTGL";
	
	/**
	 * JKDA:健康档案的模块
	 * 
	 */
	public final static String JKDA = "JKDA";
	
	/**
	 * JTBC:家庭病床的模块
	 * 
	 */
	public final static String JTBC = "JTBC";
	
	/**
	 * BRMK:病人模块
	 * 
	 */
	public final static String BRMK = "BRMK";

	/**
	 * HZJBXX:同步患者基本信息
	 */
	public final static String HZJBXXTB = "HZJBXXTB";
	
	/**
	 * HZZDXXTB:患者诊断信息
	 */
	public final static String HZZDXXTB = "HZZDXXTB";

	public static final String YHRYSJTB = "YHRYSJTB";


	private static JdbcTemplate jdbcTemplate;

	private static ClassPathXmlApplicationContext applicationContext = null;

	public static Map<String, String> systemParaMap = null;

	protected static class SystemParaRowMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int index) throws SQLException {
			SystemPara systemPara = new SystemPara();
			systemPara.setKey(rs.getString("name"));
			systemPara.setValue(rs.getString("value"));
			return systemPara;
		}
	}

	static {
		try {
			applicationContext = new ClassPathXmlApplicationContext(
					"classpath:applicationContext.xml");
			jdbcTemplate = (JdbcTemplate) applicationContext
					.getBean("jdbcTemplate");

			String sql = "select spv.name,spv.value from system_parameter_values spv";
			SystemParaRowMapper sprm = new SystemParaRowMapper();

			@SuppressWarnings("unchecked")
			List<SystemPara> list = (List<SystemPara>) jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor(sprm));

			systemParaMap = new HashMap<String, String>();
			for (SystemPara systemPara : list) {
				systemParaMap.put(systemPara.getKey(), systemPara.getValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("获取全局变量失败;");
		}

	}

}
