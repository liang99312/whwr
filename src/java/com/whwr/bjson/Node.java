package com.whwr.bjson;

public class Node {  
    private String id;  
    private String parentId;  
    private String text;
    private boolean leaf;
    Node(){}  
    public Node(String id,String parentId,String text,boolean leaf){  
        this.id=id;  
        this.parentId = parentId;  
        this.text = text;
        this.leaf = leaf;
    }  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getParentId() {  
        return parentId;  
    }  
    public void setParentId(String parentId) {  
        this.parentId = parentId;  
    }
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}  
    
}  
