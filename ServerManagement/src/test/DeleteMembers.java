package test;

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

public class DeleteMembers extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userType;
	private int managerType;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getManagerType() {
		return managerType;
	}

	public void setManagerType(int managerType) {
		this.managerType = managerType;
	}
	public String execute() throws Exception{
		System.out.println("10");
		if(userType <= managerType){
			this.addActionError("您没有权限删除此人员");
			return INPUT;
		}
		else{
			Session se = HibernateSessionFactory.getSession();
			Criteria crit = se.createCriteria(Staff.class);
			crit.add(Restrictions.eq("id",id));
			if(crit.list().isEmpty()){
				se.close(); 
				this.addActionError("此人员不存在");
				return INPUT;
			}
			else{
				System.out.println("before");
				Staff staff = (Staff)se.load(Staff.class,id);
				Transaction tx = se.beginTransaction();
				se.delete(staff);
				tx.commit();  
				se.close(); 
				return SUCCESS;
			}
				
			
			
		}
		
	}

	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	


}
