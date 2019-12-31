package com.hori.pageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * easyui使用的tree模型
 * 
 * @author 
 * 
 */
public class TreeNode implements java.io.Serializable {

	private String id;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Map<String, Object> attributes;// 其他参数
	private List<TreeNode> children;// 子节点
	private String state = "closed";// 是否展开(open,closed)
	private String parentId;  //父节点id
	 /** 
     * 空的构造函数 
     */  
    public TreeNode(){  
          
    }  
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	 /** 
     * 添加子节点的方法 
     * @param node 树节点实体 
     */  
    public void addChild(TreeNode node){  
        if(this.children==null){  
            children=new ArrayList<TreeNode>();  
            children.add(node);  
        }else{  
            children.add(node);  
        }  
    } 
	
	
	public TreeNode(String id, String text, String iconCls, Boolean checked, Map<String, Object> attributes,
			List<TreeNode> children, String state, String parentId) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
		this.state = state;
		this.parentId = parentId;
	}

}
