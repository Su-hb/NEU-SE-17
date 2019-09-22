package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

/*
 * @author 王大犇
 * 删除景点节点的控制器类
 */

@WebServlet("/deleteAttract")
public class DeleteAttract extends HttpServlet  {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String infoPath="F:/JavaWorkspaceEE/TouristSystemWeb/Info.txt";
		String mapPath="F:/JavaWorkspaceEE/TouristSystemWeb/Map.txt";
		int count=0;
		int i=0;
		String line=null;
		String line1=null;
		String str=null;
		String str1=null;
		int offset=0;
		
		String path=request.getSession().getServletContext().getRealPath("");
		
		request.setCharacterEncoding("GB2312");
		String deleteAttract=request.getParameter("delete");
		//System.out.println(deleteAttract);
		
		File file=new File(infoPath);
		if(!file.exists()){
			file.createNewFile();
		}
		
		BufferedReader readInfo=new BufferedReader(new FileReader(infoPath));
		while((line=readInfo.readLine())!=null){
			if(line.startsWith(deleteAttract)){
				i=count;
				continue;
			}else{
				if(count==0){
					str1=line+"-";
				}else{
					str1=str1+line+"-";
				}
				count++;
			}
		}
		readInfo.close();
		
		str=str1;
		
		//System.out.println(str);
		
		FileWriter fileWriter =new FileWriter(file);
	      fileWriter.write("");
	      fileWriter.flush();
	      fileWriter.close();
	    
	   FileWriter writer=new FileWriter(infoPath,true);
	   String l=null;
	   while((offset=str.indexOf("-",offset))!=-1){
		   line1=str1.substring(0, str1.indexOf("-"));
		   str1=str1.substring(str1.indexOf("-")+1);
		   
		   l=l+line1+"\r\n";
		   
		   offset=offset+"-".length();
		   count++;
	   }
	   int h1=l.lastIndexOf("\r\n");
	   l=l.substring(4,h1);
	   writer.write(l);
	   writer.close();
	   
	   //下面是删除景点坐标信息
	   String x[]=null;
	   String y[]=null;
	   String coord=null;
	   
	   String coordx=path+"/coordX.txt";
	   String coordy=path+"/coordY.txt";
	   BufferedReader brx=new BufferedReader(new FileReader(coordx));
	   BufferedReader bry=new BufferedReader(new FileReader(coordy));
	   
	   x=brx.readLine().split(",");
	   brx.close();
	   FileWriter fwx=new FileWriter(new File(coordx));
	   fwx.write("");
	   fwx.flush();
	   fwx.close();
	   FileWriter fwx1=new FileWriter(coordx,true);
	   for(int c=0;c<x.length;c++){
		   if(c!=i){
			   coord+=x[c]+",";
		   }
	   }
	   fwx1.write(coord.substring(4, coord.length()-1));
	   fwx1.close();
	   
	   y=bry.readLine().split(",");
	   bry.close();
	   FileWriter fwy=new FileWriter(new File(coordy));
	   fwy.write("");
	   fwy.flush();
	   fwy.close();
	   FileWriter fwy1=new FileWriter(coordy,true);
	   coord=null;
	   for(int c=0;c<y.length;c++){
		   if(c!=i){
			   coord+=y[c]+",";
		   }
	   }
	   fwy1.write(coord.substring(4,coord.length()-1));
	   fwy1.close();
	   
	   //下面是Map文件的删除部分
	   String line_map = null;
	   String str_1_map = null;
	   String str_map = null;
	   int count_map = 0;
	   
	   File file_map = new File(mapPath);
	   if(!file_map.exists()){
	             file_map.createNewFile();
	   }
	   
	   BufferedReader readInfo_map = new BufferedReader(new FileReader(mapPath));
	   while((line_map = readInfo_map.readLine())!=null)
	      {
			  if(line_map.contains(deleteAttract)){
				  continue;
			  }else{
			  if(count_map == 0) {str_1_map = line_map + "&";}
			  else{str_1_map = str_1_map + line_map + "&";}
			  //System.out.println("KKDD: " + line_map);
			  count_map++;
			  }
	      }
	   readInfo_map.close();
	   
	   str_map=str_1_map;
	   //System.out.println(str_map);
	   
	   FileWriter fileWriter_map =new FileWriter(file_map);
	   fileWriter_map.write("");
	   fileWriter_map.flush();
	   fileWriter_map.close();
	   
	   int offset_map = 0;
	   String line_1_map = null;
	   FileWriter writer_map = new FileWriter(mapPath, true);
	   String s=null;
	   while((offset_map = str_map.indexOf("&", offset_map))!=-1){
			  line_1_map = str_1_map.substring(0, str_1_map.indexOf("&"));
			  str_1_map = str_1_map.substring(str_1_map.indexOf("&") + 1);
			  
			  s=s+line_1_map+"\r\n";
			  
			  offset_map = offset_map + "&".length();
	          count_map++;
	   }
	   int h=s.lastIndexOf("\r\n");
	   s=s.substring(4, h);
	   writer_map.write(s);
	   writer_map.close();
	   
	   response.sendRedirect("Administrator.jsp");
	}
}
