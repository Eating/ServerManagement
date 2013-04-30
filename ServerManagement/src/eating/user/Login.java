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
    	userInfo = (UserInfo)request.getSession().getAttribute("currUser"); //��֪����һ������
    	Session se = HibernateSessionFactory.getSession();
    	Criteria crit = se.createCriteria(Staff.class); 
    	crit.add(Restrictions.eq("userName", loginName));
    	crit.add(Restrictions.eq("password", password)); 
    	/**
    	 * �������д���session��ͨ��Hibernate�ṹ��Staff������ݿ��ȡ��Ϣ��
    	 * ������userName �� password �����ϵ���Ϣ��������м������þ����ԡ�
    	 */
    	List<Staff> staff = crit.list();
    	if(staff.size() <= 0)
    		this.addActionError("�û����������������������");
    	HibernateSessionFactory.closeSession();
    	
    	
    }
	
	
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.response = response;
	}





	

}
