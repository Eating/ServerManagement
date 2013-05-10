package eating.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction; 

import org.hibernate.Criteria; 
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;

import com.opensymphony.xwork2.ActionSupport;

public class AcknowledgeDelete extends ActionSupport implements ServletRequestAware, ServletResponseAware{
  private int userId;
  private HttpServletRequest request;
  private HttpServletResponse response;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String execute() throws Exception
	{
       Session se = HibernateSessionFactory.getSession();
		
		Staff sta =(Staff)se.load(Staff.class, userId);
		Transaction tx = se.beginTransaction();
		se.delete(sta);
		tx.commit();
		se.close();
		return SUCCESS;
	}
	
	
  @Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = response ;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = request;
	}


}
