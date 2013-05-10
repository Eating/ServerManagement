package eating.user;

import java.io.IOException;

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
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class Modification extends ActionSupport implements ServletRequestAware, ServletResponseAware{
    private String userId;
    private String userName;
    private String userEmail;
    private String store;
    private HttpServletRequest request;
	private HttpServletResponse response;
	private Session se = HibernateSessionFactory.getSession();
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response ;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public void validate() {
		System.out.println(store);
		if (userEmail.equals(""))
			this.addActionError("信息不完整");
		else {
			Criteria critCheckUserName = se.createCriteria(Staff.class);
			Criteria critCheckEmail = se.createCriteria(Staff.class);
			critCheckEmail.add(Restrictions.eq("email", userEmail));
			if (critCheckEmail.list().size() == 2) {
				HibernateSessionFactory.closeSession();
				this.addActionError("此邮箱已被使用，请设置新邮箱");
			}
		}
	}
	 public void modify() throws IOException{
	    	
	    	Staff sta = new Staff();
	    	Store sto = (Store)se.load(Store.class, Integer.valueOf(store));
	    	sta = (Staff)se.get(Staff.class, new Integer(Integer.valueOf(userId)));
	    	sta.setEmail(userEmail);
	    	sta.setStore(sto);
	    	Transaction tran = se.beginTransaction();
	    	se.update(sta);
	    	tran.commit();
	    	HibernateSessionFactory.closeSession();
	    	
	    	
	    }
		public String execute() throws Exception{
			modify();
			return SUCCESS;
		}
	
}
