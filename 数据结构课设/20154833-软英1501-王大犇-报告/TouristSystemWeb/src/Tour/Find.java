package Tour;

import java.util.ArrayList;
import java.util.List;

import Tour.TourGraph;


public class Find {

    private TourGraph graph;
    
    public Find(TourGraph graph){
    	this.graph=graph;
    }
    
    /**
     * 根据关键字find景点和相关信息
     * @param keyword
     */
    
    public List<Integer> findArc(String keyword){
    	List<Integer> findNodes=new ArrayList<Integer>();
    	for(int i=0;i<graph.getAttractNum();i++){
    		String nameDoc=graph.getNodes().get(i).getName();
    		String desDoc=graph.getNodes().get(i).getDes();
    		//根据KMP算法匹配关键字与景点名称和简介部分
    		if(KMP(nameDoc,keyword)||KMP(desDoc,keyword)){
    			findNodes.add(i);
    		}
    	}
    	return findNodes;
    }
    
    /**
     * KMP算法
     * 匹配关键字
     * 通过对关键字移动匹配，调用计算步长函数
     */
    private boolean KMP(String doc,String keyword){
    	String newDoc="@"+doc;
    	String newKeyword="@"+keyword;
    	
    	int[] key=calculatek(keyword);
    	
    	int i=1,j=1;
    	while(i<=doc.length() && j<=keyword.length()){
    		if(j==0||newDoc.charAt(i)==newKeyword.charAt(j)){
    			i++;
    			j++;
    		}else{
    			j=key[j];
    		}
    	}
    	if(j>keyword.length()){
    		return true;
    	}
    	return false;
    }
    
    /**
     * 对关键字进行计算，计算移动步长
     */
    public int[] calculatek(String keyword){
    	String newKeyword="@"+keyword;
    	int[] k=new int[newKeyword.length()];
    	int i=1;
    	k[1]=0;
    	int j=0;
    	
    	while(i<keyword.length()){
    		if(j==0||newKeyword.charAt(i)==newKeyword.charAt(j)){
    			i++;
    			j++;
    			k[i]=j;
    		}else{
    			j=k[j];
    		}
    	}
    	return k;
    }
}
