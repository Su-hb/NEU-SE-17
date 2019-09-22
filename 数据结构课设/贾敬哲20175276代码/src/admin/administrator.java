package admin;

/**
 * @创建人
 * @创建时间 22:14 2019/1/14
 * @描述 管理员信息
 */
public class administrator {

	/**
	 * 用户名和密码
	 */
	private String admin_Name = new String();
	private String admin_PWD = new String();

	/**
	 * @Author:
	 * @Description：包含所有属性的构造器
	 * @Date： 22:16 2019/1/14
	 */
	public administrator(String admin_Name, String admin_PWD) {
		this.admin_Name = admin_Name;
		this.admin_PWD = admin_PWD;
	}

	/**
	 * @Author:
	 * @Description：无参构造器
	 * @Date： 22:16 2019/1/14
	 */
	public administrator() {
	}

	/**
	 * @Author:  
	 * @Description：set()&&get()
	 * @Date： 22:15 2019/1/14
	 */
	public String getAdmin_Name() {
		return admin_Name;
	}

	public void setAdmin_Name(String admin_Name) {
		this.admin_Name = admin_Name;
	}

	public String getAdmin_PWD() {
		return admin_PWD;
	}

	public void setAdmin_PWD(String admin_PWD) {
		this.admin_PWD = admin_PWD;
	}
}
