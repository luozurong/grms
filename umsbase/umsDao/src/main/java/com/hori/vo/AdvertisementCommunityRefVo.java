package com.hori.vo;

public class AdvertisementCommunityRefVo {
	private String id;
	private String advertisementId;
	private String communityId;
	private String organizationSeq;
	private Integer appOpenNum;
	/**
	 * 广告播放日期
	 */
	private String adStartTime;
	/**
	 * 广告结束日期
	 */
	private String adEndTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}

	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}

	public Integer getAppOpenNum() {
		return appOpenNum;
	}

	public void setAppOpenNum(Integer appOpenNum) {
		this.appOpenNum = appOpenNum;
	}

	public String getAdStartTime() {
		return adStartTime;
	}

	public void setAdStartTime(String adStartTime) {
		this.adStartTime = adStartTime;
	}

	public String getAdEndTime() {
		return adEndTime;
	}

	public void setAdEndTime(String adEndTime) {
		this.adEndTime = adEndTime;
	}

	@Override
	public String toString() {
		return "AdvertisementCommunityRefVo [id=" + id + ", advertisementId=" + advertisementId + ", communityId="
				+ communityId + ", organizationSeq=" + organizationSeq + ", appOpenNum=" + appOpenNum + ", adStartTime="
				+ adStartTime + ", adEndTime=" + adEndTime + "]";
	}
	
}
