package eating.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteMembersB extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private String manType;
	private String userName;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session se = HibernateSessionFactory.getSession();
	private Criteria crit;
	private int id;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response ;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String getManType() {
		return manType;
	}

	public void setManType(String manType) {
		this.manType = manType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    public void validate(){
    	System.out.println("delemembers" +manType);
    	int manTypeI = Integer.valueOf(manType);
    	if(userName.equals("")){
    		se.close();
    		this.addActionError("用户名不能为空");
    	}
    	else{
    		crit = se.createCriteria(Staff.class);
    		crit.add(Restrictions.eq("userName", userName));
    		List<Staff> list = crit.list();
    		if(list.size() == 0){
    			se.close();
    			this.addActionError("此用户不存在");
    			}
    		else if(manTypeI >= list.get(0).getStaffType()){
    			se.close();
    			this.addActionError("您没有权限删除此用户");
    		}
    		else{
    			se.close();
    			id = list.get(0).getId();
    			try {
					response.sendRedirect("SuperManager.jsp?param="+userName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	
		
	}

	
}
