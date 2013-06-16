package eating.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class ChangePsdServlet extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	String userId;
	String originalPsd;
	String newPsd;
	String rePsd;
	
	public void change() throws IOException{
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		
		userId = request.getParameter("userId");
		originalPsd = request.getParameter("originalPsd");
		newPsd = request.getParameter("newPsd");
		rePsd = request.getParameter("rePsd");
		System.out.println("get userId:"+userId + "-- get OrigialPsd" + originalPsd +"-- getNewPsd:"+newPsd);
		if(check()){    // 原密码正确
			modify();
			System.out.println("modify successfully");
			PrintWriter out = response.getWriter();
			out.println("success");
			out.flush();
			out.close();
			
		}
		else{      //原密码不正确
			System.out.println("not exists");
			PrintWriter out = response.getWriter();
			out.println("failure");
			out.flush();
			out.close();
		}
		System.out.println("we get the parameter" + originalPsd + "!!!" + newPsd + "!!!" + rePsd);
		
		
	}
	public boolean check(){
		Session se = src.com.server.hiber.HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Staff.class);
		crit.add(Restrictions.eq("id", Integer.valueOf(userId)));
		crit.add(Restrictions.eq("password", originalPsd));
		List<Staff> list = crit.list();
		if(list.size() == 0){
			HibernateSessionFactory.closeSession();
			return false;
		}
			
		else{
			HibernateSessionFactory.closeSession();
			return true;
		}
		
	}
	public boolean modify(){
		Session se = HibernateSessionFactory.getSession();
		Staff sta = new Staff();
		sta = (Staff) se.get(Staff.class, new Integer(Integer.valueOf(userId)));
		sta.setPassword(newPsd);
		Transaction tran = se.beginTransaction();
		se.update(sta);
		tran.commit();
		HibernateSessionFactory.closeSession();
		return true;	
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
