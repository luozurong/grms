package com.hori.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.hori.dao.queryBean.OperationRecordQueryBean;
import com.hori.db.HibernateBaseDao;
import com.hori.model.OperationRecord;
import com.hori.utils.FuzzyQueryUtils;

/**
 * 操作记录
 * @author ChenPuzhen  
 * @date 2017年5月18日 上午11:06:28 
 * @version V1.0
 */
@Repository
public class OperationRecordDao extends HibernateBaseDao<OperationRecord>{
	/**
	 * log4j日志
	 */
	private static final Log log = LogFactory.getLog(OperationRecordDao.class);
	
	/**
	 * 操作记录的总数
	 * @author ChenPuzhen
	 */
	public Long total() {
		String hql = "select count(*) from OperationRecord o where 1=1 ";
		return this.count(hql);
	}


	
	/**
	 * 通过查询bean获取操作信息
	 * @param queryBean
	 * @return
	 */
	public List<OperationRecord> getByQueryBean(OperationRecordQueryBean queryBean) {
		StringBuffer hql =new StringBuffer("FROM OperationRecord m WHERE 1=1");
		List<Object> param =  new ArrayList<Object>();
		
		if(FuzzyQueryUtils.isCondition(queryBean.getOperationTime())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String operationTime = sdf.format(queryBean.getOperationTime());
			hql.append(" AND m.operationTime = '"+ operationTime + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getRole())){
			hql.append(" AND m.role ='" + queryBean.getRole() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getAccount())){
			hql.append(" AND m.account = '" + queryBean.getAccount() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getIpAddress())){
			hql.append(" AND m.ipAddress = '" + queryBean.getIpAddress() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getOperationType())){
			hql.append(" AND m.operationType = '" + queryBean.getOperationType() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getOperationModule())){
			hql.append(" AND m.operationModule = '" + queryBean.getOperationModule() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getOperationContent())){
			hql.append(" AND m.operationContent = '" + queryBean.getOperationContent() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getOperationResult())){
			hql.append(" AND m.operationResult = '" + queryBean.getOperationResult() + "' ");
		}
		
		if(FuzzyQueryUtils.isCondition(queryBean.getClient())){
			hql.append(" AND m.client = '" + queryBean.getClient() + "' ");
		}
		
		return this.find(hql.toString());
	}


}
