package eating.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private String userId;
	private String originalPsw;
	private String newPsw;
	private String newPswAgain;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Session se = HibernateSessionFactory.getSession();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOriginalPsw() {
		return originalPsw;
	}

	public void setOriginalPsw(String originalPsw) {
		this.originalPsw = originalPsw;
	}

	public String getNewPsw() {
		return newPsw;
	}

	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}

	public String getNewPswAgain() {
		return newPswAgain;
	}

	public void setNewPswAgain(String newPswAgain) {
		this.newPswAgain = newPswAgain;
	}

	public void validate() {
		System.out.println(userId + "@" + originalPsw + "@" + newPsw);
		if (originalPsw.equals("") || newPsw.equals("")
				|| newPswAgain.equals("")) {
			se.close();
			this.addActionError("信息不完整");
		} else if (!newPsw.equals(newPswAgain)) {
			se.close();
			this.addActionError("两次新密码不一致");
		} else {

			Staff sta = new Staff();
			sta = (Staff) se.get(Staff.class,
					new Integer(Integer.valueOf(userId)));
			if (!sta.getPassword().equals(originalPsw)) {
				se.close();
				this.addActionError("原密码错误");
			} else {
				sta.setPassword(newPsw);
				Transaction tran = se.beginTransaction();
				se.update(sta);
				tran.commit();
				HibernateSessionFactory.closeSession();
				try {
					response.sendRedirect("ChangePasswordSuccess.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
