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

public class MaintainItemsActionB extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<Items> items ;
	private List<Category> category_list ;
	private int category_id2, store_id ;
	
	public void getData()
	{
		Session se = HibernateSessionFactory.getSession() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		Criteria items_cri = se.createCriteria(Items.class) ;
		if(category_id2 != 0)
		{
			Category temp_cate = (Category)se.load(Category.class, category_id2) ;
			items_cri.add(Restrictions.eq("category", temp_cate)) ;
		}
		items = items_cri.list() ;
		
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData();
		request.setAttribute("categorylist", category_list) ;
		request.setAttribute("items", items) ;
		return SUCCESS;
	}

	public List<Category> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<Category> category_list) {
		this.category_list = category_list;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	public int getCategory_id2() {
		return category_id2;
	}

	public void setCategory_id2(int category_id2) {
		this.category_id2 = category_id2;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
