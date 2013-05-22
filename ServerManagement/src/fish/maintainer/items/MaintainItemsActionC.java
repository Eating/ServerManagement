package fish.maintainer.items;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class MaintainItemsActionC extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<Category> category_list ;
	
	public void getData()
	{
		Session se = HibernateSessionFactory.getSession() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData();
		request.setAttribute("categorylist", category_list) ;
		return SUCCESS;
	}

	public List<Category> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<Category> category_list) {
		this.category_list = category_list;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
