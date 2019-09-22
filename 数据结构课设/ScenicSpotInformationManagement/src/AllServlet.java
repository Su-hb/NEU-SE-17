import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人 贾敬哲
 * @创建时间 23:49 2018/12/11
 * @描述 景区信息管理系统
 */

@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {

  /**
   * @Author: 贾敬哲
   * @Description：构造函数
   * @Date： 14:59 2018/12/12
   */
  public AllServlet() {

    super();
  }


  /**
   * @Author: 贾敬哲
   * @Description：HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   * @Date： 14:54 2018/12/12
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * @Author: 贾敬哲
   * @Description：HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   * @Date： 14:58 2018/12/12
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // TODO Auto-generated method stub
    //设置编码为UTF-8
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    //选择模块
    String modelName = request.getParameter("modelName");
    int model = Integer.parseInt(modelName);

//    try {
//      switch (model){
//        case  0:
    String name = request.getParameter("username");

//            //管理员登录
//            administratorLogin();
//        case  1:
//            //输出景区景点分布图
//            disViewpoint();
//        case 2:
//            //景点的查找和排序
//            findAndSortViewpoint();
//        case 3:
//            //输出导游线路图
//            guidePath();
//        case 4:
//            //输出两个景点间的最短路和最短距离
//            shortestPath();
//        case 5:
//            //停车场车辆进出记录信息
//            carInformationManagement(HttpServletRequest request, HttpServletResponse response);

//      }
//    }

  }
  public void carInformationManagement(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    response.sendRedirect("CarInformationManagement");
  }
}



