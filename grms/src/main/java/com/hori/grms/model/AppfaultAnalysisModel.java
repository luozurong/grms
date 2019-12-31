package com.hori.grms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AppfaultAnalysisModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6851553977128413571L;

	@Id
	@GeneratedValue(generator="UUID")
    private String id;

    private Integer compatibleProblemNum=0;

    @Override
	public String toString() {
		return "AppfaultAnalysisModel [id=" + id + ", compatibleProblemNum=" + compatibleProblemNum
				+ ", softwareBugNum=" + softwareBugNum + ", networkProblemNum=" + networkProblemNum + ", useProblemNum="
				+ useProblemNum + ", year=" + year + ", month=" + month + ", day=" + day + ", type=" + type
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", occurrenceTime=" + occurrenceTime + "]";
	}

	private Integer softwareBugNum=0;

    private Integer networkProblemNum=0;

    private Integer useProblemNum=0;

    private String year;

    private String month;

    private String day;

    private String type;

    private Date startTime;

    private Date endTime;
    
    public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	private Date occurrenceTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCompatibleProblemNum() {
        return compatibleProblemNum;
    }

    public void setCompatibleProblemNum(Integer compatibleProblemNum) {
        this.compatibleProblemNum = compatibleProblemNum;
    }

    public Integer getSoftwareBugNum() {
        return softwareBugNum;
    }

    public void setSoftwareBugNum(Integer softwareBugNum) {
        this.softwareBugNum = softwareBugNum;
    }

    public Integer getNetworkProblemNum() {
        return networkProblemNum;
    }

    public void setNetworkProblemNum(Integer networkProblemNum) {
        this.networkProblemNum = networkProblemNum;
    }

    public Integer getUseProblemNum() {
        return useProblemNum;
    }

    public void setUseProblemNum(Integer useProblemNum) {
        this.useProblemNum = useProblemNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}