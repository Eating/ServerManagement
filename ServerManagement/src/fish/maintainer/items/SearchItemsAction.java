package fish.maintainer.items;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

public class SearchItemsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private String search_item ;
	private List<Items> item_list ;
	private List<Category> category_list ;
	
	private void getData() throws UnsupportedEncodingException {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		Criteria item_cri = se.createCriteria(Items.class) ;
		String transStr = new String(search_item.getBytes("ISO-8859-1"),"UTF-8") ;
		item_cri.add(Restrictions.like("name", "%"+transStr+"%")) ;
		item_list = item_cri.list() ;
		se.close() ;
	}
	
	public void validate() {
		if(search_item == null || search_item.isEmpty())
			this.addActionError("请输入搜索内容") ;
		else {
			try {
				getData() ;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if(item_list.isEmpty())
				this.addActionMessage("您查询的信息不存在") ;
		}
	}
	
	public String execute() throws Exception{
		request.setAttribute("categorylist", category_list) ;
		request.setAttribute("items", item_list) ;
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public String getSearch_item() {
		return search_item;
	}

	public void setSearch_item(String search_item) {
		this.search_item = search_item;
	}

	public List<Items> getItem_list() {
		return item_list;
	}

	public void setItem_list(List<Items> item_list) {
		this.item_list = item_list;
	}

	public List<Category> getCategory_list() {
		return category_list;
	}

	public void setCategory_list(List<Category> category_list) {
		this.category_list = category_list;
	}
	
}
