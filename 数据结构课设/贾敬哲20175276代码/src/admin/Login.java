package admin;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @创建人
 * @创建时间 22:13 2019/1/14
 * @描述 景区信息管理系统
 */
public class Login {
	private static final long serialVersionUID = 1L;
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/testdb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "root";
	static final String PASS = "Delete1350";
	/**
	 * @Author:
	 * @Description：数据库连接的获取
	 * @Date： 22:28 2019/1/14
	 */
	//数据库连接
	public static  Connection connect() throws ClassNotFoundException,SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册 JDBC 驱动器
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 打开一个连接
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

			return conn;

		}catch(SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch(Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Author:
	 * @Description：关闭数据库资源
	 * @Date： 11:11 2019/1/15
	 */
	public static void close(Statement stat,Connection conn) throws SQLException{
		if(stat!=null){
			stat.close();
		}
		if(conn != null){
			conn.close();
		}
	}
	/**
	 * @Author:
	 * @Description：注册(添加）
	 * @Date： 22:28 2019/1/14
	 */
	public  void insert(administrator user) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stat = null;
		String admin_Name = user.getAdmin_Name();
		String admin_PWD = user.getAdmin_PWD();
		conn = connect();
		stat = conn.createStatement();
		stat.execute("insert into viewpointadmin(admin_Name,admin_PWD) values ('"+admin_Name+"','"+admin_PWD+"')");
		close(stat,conn);
	}

	/**
	 * @Author:
	 * @Description：删除账号信息
	 * @Date： 11:23 2019/1/15
	 */
	public void delete(administrator admin) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stat = null;
		conn = connect();
		stat = conn.createStatement();
		String user_Name = admin.getAdmin_Name();
		String user_PWD = admin.getAdmin_PWD();
		stat.execute("delete FROM viewpointadmin where admin_Name="+user_Name+"");
		close(stat,conn);
	}

	/**
	 * @Author:
	 * @Description：登陆操作
	 * @Date： 11:32 2019/1/15
	 */
	public static boolean login(String name,String PWD) throws SQLException, ClassNotFoundException {
		administrator admin = new administrator(name,PWD);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs=null;
		conn = connect();
		stat = conn.createStatement();
		ArrayList<administrator> result=new ArrayList<>();
		rs = stat.executeQuery("select * from viewpointadmin where admin_Name='"+admin.getAdmin_Name()+"';");
		while (rs.next()){
			String pwdInDB = rs.getString("admin_PWD");
			if (pwdInDB.equals(admin.getAdmin_PWD())) {close(stat,conn);return true;}
		}
		close(stat,conn);
		return false;
	}

	public static void main(String[] args) {
		administrator tmp = new administrator("aadmaain","123456");
		Login l = new Login();
		try {

			boolean res = l.login("admin","123456");
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
