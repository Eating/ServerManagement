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
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;
import src.com.server.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;


public class LoginServlet extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userName;
	private String password;
	private int userId = 0;

	public void LoginVerification() throws IOException {
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		System.out.println("we get the parameter" + userName + "!!!" + password);
		int result = checkUser();
		if (result!= 0)
		{
			System.out.println(" exists");
			JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("result", "success");
	        jsonObject.put("storeId",result);
	        jsonObject.put("userId", userId);
	      
	        try {  
	            this.response.getWriter().write(jsonObject.toString());  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
		}
		else
		{
			System.out.println("not exists");
			JSONObject jsonObject = new JSONObject();  
	        jsonObject.put("result", "failure");
	        jsonObject.put("storeId",result);
	        jsonObject.put("userId", userId);
	      
	        try {  
	            this.response.setCharacterEncoding("UTF-8");  
	            this.response.getWriter().write(jsonObject.toString());  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
		}
		
	}

	public int checkUser() {
		Session se = src.com.server.hiber.HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Staff.class);
		crit.add(Restrictions.eq("userName", userName));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("staffType", (Integer)5));
		List<Staff> list = crit.list();
		if (crit.list().size() == 0){
			
			HibernateSessionFactory.closeSession();
			return 0;
		}
		else{		
			userId = list.get(0).getId();
			HibernateSessionFactory.closeSession();
			return list.get(0).getStore().getId();
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
