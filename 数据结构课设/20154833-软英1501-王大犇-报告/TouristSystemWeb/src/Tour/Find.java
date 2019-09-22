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
     * ���ݹؼ���find����������Ϣ
     * @param keyword
     */
    
    public List<Integer> findArc(String keyword){
    	List<Integer> findNodes=new ArrayList<Integer>();
    	for(int i=0;i<graph.getAttractNum();i++){
    		String nameDoc=graph.getNodes().get(i).getName();
    		String desDoc=graph.getNodes().get(i).getDes();
    		//����KMP�㷨ƥ��ؼ����뾰�����ƺͼ�鲿��
    		if(KMP(nameDoc,keyword)||KMP(desDoc,keyword)){
    			findNodes.add(i);
    		}
    	}
    	return findNodes;
    }
    
    /**
     * KMP�㷨
     * ƥ��ؼ���
     * ͨ���Թؼ����ƶ�ƥ�䣬���ü��㲽������
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
     * �Թؼ��ֽ��м��㣬�����ƶ�����
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
