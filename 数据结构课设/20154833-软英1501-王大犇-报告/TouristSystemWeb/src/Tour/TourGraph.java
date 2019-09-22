package Tour;
import java.util.ArrayList;
import java.util.List;

public class TourGraph {

	private int attractNum;//景点数量
	private int vNum;//路的数量
	private List<AttractNode> nodes;
	
	public TourGraph(int attractNum){
		this.attractNum=attractNum;
		nodes=new ArrayList<AttractNode>();
	}
	
	public TourGraph(int attractNum,int rNum){
		this.attractNum=attractNum;
		this.vNum=rNum;
		nodes=new ArrayList<AttractNode>();
	}
	
	public int getAttractNum(){
		return attractNum;
	}
	
	public void setAttractNum(int attractNum){
		this.attractNum=attractNum;
	}
	
	public int getRNum(){
		return vNum;
	}
	
	public void setRNum(int vNum){
		this.vNum=vNum;
	}
	
	public List<AttractNode> getNodes(){
		return nodes;
	}
	
	public void setNodes(List<AttractNode> nodes){
		this.nodes=nodes;
	}
	
	public AttractNode getOneNodes(int i){
		return nodes.get(i);
	}
}
