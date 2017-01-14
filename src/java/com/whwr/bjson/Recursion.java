package com.whwr.bjson;

import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
  
//生成json数据  
public class Recursion {  
	public List nodeList =new ArrayList(); 
    public StringBuffer returnStr=new StringBuffer(); 
    public void recursionFn(List list , Node node){ 
    	 
        if(hasChild(list,node)){    
        	if(!"root".equals(node.getParentId())){
            returnStr.append("{\"id\":");  
            returnStr.append("\"" + node.getId() + "\"");  
            returnStr.append(",\"parentId\":");  
            returnStr.append("\"" + node.getParentId() + "\"");  
            returnStr.append(",\"text\":");  
            returnStr.append("\"" + node.getText() + "\"");  
            returnStr.append(",\"leaf\":");  
            returnStr.append(node.isLeaf()? "\"true\"":"\"false\"");  
            returnStr.append(",\"children\":[");  
        	}
            List childList = getChildList(list,node);  
            System.out.println(childList);
            Iterator it = childList.iterator();    
            while(it.hasNext()){    
                Node n = (Node)it.next();    
                recursionFn(list,n);    
            }    
            if(!"root".equals(node.getParentId())){
            	returnStr.append("]},");    
            }
        }else{    
        	returnStr.append("{\"id\":");  
            returnStr.append("\"" + node.getId() + "\"");  
            returnStr.append(",\"parentId\":");  
            returnStr.append("\"" + node.getParentId() + "\"");  
            returnStr.append(",\"text\":");  
            returnStr.append("\"" + node.getText() + "\"");  
            returnStr.append(",\"leaf\":");  
            returnStr.append(node.isLeaf()? "\"true\"":"\"false\"");  
            returnStr.append("},");    
        }    
    }    
    public boolean hasChild(List list, Node node){  //判断是否有子节点  
        return getChildList(list,node).size()>0?true:false;  
    }  
    public List getChildList(List list , Node node){  //得到子节点列表  
        List li = new ArrayList();    
        Iterator it = list.iterator();    
        while(it.hasNext()){    
            Node n = (Node)it.next();    
            if(n.getParentId().equals(node.getId())){    
                li.add(n);    
            }    
        }    
        return li;    
    }  
    public String modifyStr(String returnStr){//修饰一下才能满足Extjs的Json格式  
        return "{children:" + ("["+returnStr+"]").replaceAll(",]", "]") + "}"; 
          
    }  
    public static void main(String[] args) {  
    	Recursion r = new Recursion();
    	List list = new ArrayList<Node>();
    	list.add(new Node("1","root","全班",false));
    	list.add(new Node("01","1","a",false));
    	list.add(new Node("0101","01","a01",true));
    	list.add(new Node("0102","01","a02",true));
    	list.add(new Node("02","1","b",true));
    	list.add(new Node("03","1","c",false));
    	list.add(new Node("0301","03","c01",true));
    	list.add(new Node("0302","03","c02",true));
    	r.nodeList.addAll(list);
    	r.recursionFn(list, new Node("1","root","全班",false));
        System.out.println(r.modifyStr(r.returnStr.toString()));    
    }    
}  