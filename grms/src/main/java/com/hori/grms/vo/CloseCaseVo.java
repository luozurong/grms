package com.hori.grms.vo;

import java.util.List;

import com.hori.grms.model.Attachment;
import com.hori.grms.model.CloseCaseInfo;

public class CloseCaseVo extends CloseCaseInfo {
	
	private List<Attachment> attachments;

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
}
