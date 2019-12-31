package com.hori.vo;

import java.util.ArrayList;
import java.util.List;

public class EasyUiTreeVo {
	//：绑定节点的标识值。
	private String id;	
	//：显示的节点文本。
	private String text;
	//：显示的节点图标CSS类ID。
	private String iconCls;
	//：该节点是否被选中。
	private Boolean checked;
	//：节点状态，'open' 或 'closed'。
	private String state;
	//：绑定该节点的自定义属性。
	private String attributes;
	//目标DOM对象
	private String target;
	//目标数量
	private String count;
	//父ID
	private String parentId;
	private List<EasyUiTreeVo> children=null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}



	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<EasyUiTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUiTreeVo> children) {
		this.children = children;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	
	

}
