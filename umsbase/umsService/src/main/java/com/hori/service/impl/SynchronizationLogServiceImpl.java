package com.hori.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hori.dao.SynchronizationLogDao;
import com.hori.db.support.DataGridPage;
import com.hori.model.SynchronizationLog;
import com.hori.model.TerminalAdvertisementLogs;
import com.hori.pageModel.SLogPage;
import com.hori.service.SynchronizationLogService;

@Service("synchronizationLogService")
public class SynchronizationLogServiceImpl extends BaseServiceImpl implements SynchronizationLogService {
  
	@Autowired
	private  SynchronizationLogDao synchronizationLogDao;
	@Override
	public void add(SynchronizationLog logs) {
		this.synchronizationLogDao.save(logs);
	}
	@Override
	public List<TerminalAdvertisementLogs> getByMaterialId(String terminalSerial) {
		return synchronizationLogDao.getByMaterialId(terminalSerial);
	}
	@Override
	public DataGridPage getByPage(SLogPage sLogPage) {
		return synchronizationLogDao.getByPage(sLogPage);
	}

}
