package ViewPoint;

import java.util.ArrayList;

/**
 * @创建人
 * @创建时间 20:03 2019/1/14
 * @描述 景区信息管理系统
 */
public class FindViewPoint {
	/**
	 * @Author:
	 * @Description：从邻接表中找到所需要的点
	 * @Date： 20:21 2019/1/14
	 */
	public static Attractions Find(String name,ArrayList<Attractions> AdjList){
		for (Attractions att:AdjList
		) {
			if (att.getSpotName().equals(name)){
				return att;
			}
		}
		return null;
	}
	/**
	 * @Author:
	 * @Description：从邻接表中模糊查询所需要的景点，并返回列表
	 * @Date： 15:50 2019/1/17
	 */
	public static ArrayList<Attractions> Finds(String des,ArrayList<Attractions> AdjList){
		//使用contain函数进行模糊查找
		ArrayList<Attractions> res = new ArrayList<Attractions>();

		for (Attractions att:AdjList){
			if (att.getSpotName().contains(des) ||(att.getIntroductionToAttractions() !=null &&att.getIntroductionToAttractions().contains(des))){
				res.add(att);
			}
		}
		return res;
	}

	/**
	 * @Author:
	 * @Description：用于模糊查询的KMP算法
	 * @Date： 15:50 2019/1/17
	 */
	private static boolean KMP(String doc,String keyword){
		String newDoc="@"+doc;
		String newKeyword="@"+keyword;
		int[] key=calculatek(doc,keyword);
		int i = 0,j=0;
		while(i<=doc.length() &&j<=keyword.length()){
			if(j==0||newDoc.charAt(i)==newKeyword.charAt(j)){
				i++;
				j++;
			}else{
				j=key[j];
			}
		}
		if(doc.contains(keyword)){
			return true;
		}
		return false;
	}
	/**
	 * @Author:
	 * @Description：记步操作
	 * @Date： 15:53 2019/1/17
	 */
	public static int[] calculatek(String newKeyword,String keyword){

		int[] k=new int[newKeyword.length()];
		int i=0,j=0;
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
