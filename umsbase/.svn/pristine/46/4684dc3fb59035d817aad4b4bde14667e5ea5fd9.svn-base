package com.hori.service;

import java.util.List;

import com.hori.db.support.DataGridPage;
import com.hori.model.SynchronizationLog;
import com.hori.model.TerminalAdvertisementLogs;
import com.hori.pageModel.SLogPage;

public interface SynchronizationLogService extends BaseServiceI {
	public void add(SynchronizationLog logs);

	public List<TerminalAdvertisementLogs> getByMaterialId(String terminalSerial);

	public DataGridPage getByPage(SLogPage sLogPage);
}
