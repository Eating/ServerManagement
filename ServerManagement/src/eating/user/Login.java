package eating.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import src.com.server.hiber.*;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	private String loginName;
	private String password;
	private UserInfo userInfo;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
    
	public void validate(){
    	userInfo = new UserInfo();//= (UserInfo)request.getSession().getAttribute("userInfo"); //不知道这一句干嘛的
    	Session se = HibernateSessionFactory.getSession();
    	Criteria crit = se.createCriteria(Staff.class); 
    	crit.add(Restrictions.eq("userName", loginName));
    	crit.add(Restrictions.eq("password", password)); 
    	/**
    	 * 以上四行创建session，通过Hibernate结构的Staff类从数据库读取信息。
    	 * 局限于userName 和 password 均符合的信息，最后两行即是设置局限性。
    	 */
    	List<Staff> staffList = crit.list();
    	if(staffList.size() <= 0)
    		this.addActionError("用户名或密码错误，请重新输入");
    	Staff staff = staffList.get(0);
    	int staffType = staff.getStaffType();
    	
    	userInfo.setId(staff.getId());
    	userInfo.setUserName(staff.getUserName());
        userInfo.setType(staff.getStaffType());
        
    	request.getSession().setAttribute("userInfo", userInfo);
    	if(staffType == 1){   // means supermanager
    		try {
				response.sendRedirect("SuperManager.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(staffType == 2){
    		try {
				response.sendRedirect("Maintainer.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	HibernateSessionFactory.closeSession();
    	
    	
    	
    }
	
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}





	

}
