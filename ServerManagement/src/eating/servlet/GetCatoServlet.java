package eating.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;

import src.com.server.hiber.Category;
import src.com.server.json.JSONArray;
import src.com.server.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class GetCatoServlet extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void getCato() throws IOException{
		System.out.println("get request");
		
		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		
		//storeId = Integer.valueOf(request.getParameter("userId"));  // get the catagory of that store
		
		Session se = src.com.server.hiber.HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Category.class);
		List<Category> list = crit.list();
		
		JSONArray jsonArray = new JSONArray();  
		for(Category cate:list){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("catoId", cate.getId());
			jsonObject.put("catoName", cate.getName());
			jsonArray.put(jsonObject);
		}
		this.response.getWriter().write(jsonArray.toString());
		
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
