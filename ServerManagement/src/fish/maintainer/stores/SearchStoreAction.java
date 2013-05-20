package fish.maintainer.stores;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class SearchStoreAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private String search_store ;
	private List<Store> store_list ;
	
	private void getData() throws UnsupportedEncodingException {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		String transStr = new String(search_store.getBytes("ISO-8859-1"),"UTF-8") ;
		store_cri.add(Restrictions.and(Restrictions.like("name", "%"+transStr+"%") ,Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server")))) ;
		store_list = store_cri.list() ;
		se.close() ;
	}
	
	public void validate() {
		if(search_store == null || search_store.isEmpty())
			this.addActionError("请输入搜索内容") ;
		else {
			try {
				getData() ;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if(store_list.isEmpty())
				this.addActionMessage("您查询的信息不存在") ;
		}
	}
	
	public String execute() throws Exception{
		request.setAttribute("storelist", store_list) ;
		return SUCCESS;
	}
	
	public String getSearch_store() {
		return search_store;
	}

	public void setSearch_store(String search_store) {
		this.search_store = search_store;
	}

	public List<Store> getStore_list() {
		return store_list;
	}

	public void setStore_list(List<Store> store_list) {
		this.store_list = store_list;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
