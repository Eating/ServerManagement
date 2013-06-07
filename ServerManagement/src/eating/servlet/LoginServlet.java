package eating.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;

import com.opensymphony.xwork2.ActionSupport;


public class LoginServlet extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userName;
	private String password;

	public void LoginVerification() throws IOException {
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		System.out.println("we get the parameter" + userName + "!!!" + password);


		if (checkUser())
		{
			System.out.println("user exists");
			PrintWriter out = response.getWriter();
			out.println("success");
			out.flush();
			out.close();
		}
		else
		{
			System.out.println("not exists");
			PrintWriter out = response.getWriter();
			out.println("failure");
			out.flush();
			out.close();
		}
		
	}

	public boolean checkUser() {
		Session se = src.com.server.hiber.HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Staff.class);
		crit.add(Restrictions.eq("userName", userName));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("StaffType", 5));
		if (crit.list().size() == 0){
			
			HibernateSessionFactory.closeSession();
			return false;
		}
		else{
			HibernateSessionFactory.closeSession();
			return true;
		}
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		// TODO Auto-generated method stub
		response = res;

	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		request = req;
	}

}
