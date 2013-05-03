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

public class AddManager extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private String userName;
	private String userEmail;
	private String password;
	private String rePassword;
	private String setType;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session se = HibernateSessionFactory.getSession();
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getSetType() {
		return setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}
	public void validate() {
		if(password.equals("") || rePassword.equals("") || userName.equals("") || userEmail.equals(""))
			this.addActionError("��Ϣ������");
		else if(!rePassword.equals(password)){
				this.addActionError("�����������벻һ��");
		}
		else{
    	Criteria critCheckUserName = se.createCriteria(Staff.class);
    	Criteria critCheckEmail = se.createCriteria(Staff.class);
    	critCheckUserName.add(Restrictions.eq("userName", userName));
    	critCheckEmail.add(Restrictions.eq("email", userEmail));
    	if(!critCheckUserName.list().isEmpty()){
    		HibernateSessionFactory.closeSession();
    	
    		this.addActionError("�û����Ѵ��ڣ����������û���");
    		
    	}
    	else if(!critCheckEmail.list().isEmpty()){
    		HibernateSessionFactory.closeSession();
    		this.addActionError("�������ѱ�ʹ�ã�������������");
    	}
		}
	}
	
    public void add() throws IOException{
    	
    	Staff sta = new Staff();
    	Store sto = (Store)se.load(Store.class, 1);
    	sta.setUserName(userName);
    	sta.setEmail(userEmail);
    	sta.setStaffType(Integer.valueOf(setType));
    	sta.setPassword(password);
    	sta.setStore(sto);
    	Transaction tran = se.beginTransaction();
    	se.save(sta);
    	tran.commit();
    	HibernateSessionFactory.closeSession();
    	
    	
    }
	public String execute() throws Exception{
		add();
		return SUCCESS;
	}
	
	
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response ;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
