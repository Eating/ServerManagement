package fish.maintainer.stores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AddStoreDetailAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<Category> category_list ;
	private int category_id ;
	private List<Items> items ;
	
	private void getData() {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		Criteria items_cri = se.createCriteria(Items.class) ;
		if(category_id != 0)
		{
			Category temp_cate = (Category)se.load(Category.class, category_id) ;
			items_cri.add(Restrictions.eq("category", temp_cate)) ;
		}
		items = items_cri.list() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData() ;
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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

}
