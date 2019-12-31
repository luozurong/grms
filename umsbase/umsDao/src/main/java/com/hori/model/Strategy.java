package com.hori.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity(name="Strategy")
@Table(name="strategy")
public class Strategy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3954725357051771122L;
	

	private String id;
	/**
	 * 亮度
	 */
	private Integer birghtness;
	/**
	 * 视频广告音量
	 */
	private Integer videoVolume;
	/**
	 * 音频广告音量
	 */
	private Integer audioVolume;
	private String beginTime;
	private String endTime;
	/**
	 * 策略所属策略包id
	 */
	private String strategyBagId;
	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "com.hori.hibernate.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="birghtness")
	public Integer getBirghtness() {
		return birghtness;
	}
	public void setBirghtness(Integer birghtness) {
		this.birghtness = birghtness;
	}
	@Column(name="video_volume")
	public Integer getVideoVolume() {
		return videoVolume;
	}
	public void setVideoVolume(Integer videoVolume) {
		this.videoVolume = videoVolume;
	}
	
	@Column(name="audio_volume")
	public Integer getAudioVolume() {
		return audioVolume;
	}
	public void setAudioVolume(Integer audioVolume) {
		this.audioVolume = audioVolume;
	}
	
	@Column(name="begin_time")
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="strategy_bag_id")
	public String getStrategyBagId() {
		return strategyBagId;
	}

	public void setStrategyBagId(String strategyBagId) {
		this.strategyBagId = strategyBagId;
	}
	
	@Override
	public String toString() {
		return "Strategy [id=" + id + ", birghtness=" + birghtness + ", videoVolume=" + videoVolume + ", audioVolume="
				+ audioVolume + ", beginTime=" + beginTime + ", endTime=" + endTime + ", strategyBagId=" + strategyBagId
				+ "]";
	}

}
