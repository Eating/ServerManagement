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

public class MaintainItemsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<ItemlistBean> item_list ;
	private List<Items> items ;
	private List<Store> store_list ;
	private List<Category> category_list ;
	private int category_id, store_id, category_id2 ;
	private ItemlistBean curr ;
	
	public void getData()
	{
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_list = store_cri.list() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		Criteria items_cri = se.createCriteria(Items.class) ;
		if(category_id2 != 0)
		{
			Category temp_cate = (Category)se.load(Category.class, category_id2) ;
			items_cri.add(Restrictions.eq("category", temp_cate)) ;
		}
		items = items_cri.list() ;
		
		item_list = new LinkedList<ItemlistBean>() ;
		Criteria itemlist_cri = se.createCriteria(Itemlist.class) ;
		Iterator<Itemlist> list_it = itemlist_cri.list().iterator() ;
		
		//default
		if(category_id == 0 && store_id == 0)
		{	
			while(list_it.hasNext())
			{
				curr = new ItemlistBean(list_it.next()) ;
				item_list.add(curr) ;
			}
		}
		
		//given store
		else if(category_id == 0 && store_id != 0)
		{
			while(list_it.hasNext())
			{
				Itemlist temp_item = list_it.next() ;
				if(temp_item.getStore().getId() == store_id)
				{
					curr = new ItemlistBean(temp_item) ;
					item_list.add(curr) ;
				}
			}
		}
		
		//given category
		else if(category_id != 0 && store_id == 0)
		{
			while(list_it.hasNext())
			{
				Itemlist temp_item = list_it.next() ;
				if(temp_item.getItemsByItemsId().getCategory().getId() == category_id)
				{
					curr = new ItemlistBean(temp_item) ;
					item_list.add(curr) ;
				}
			}
		}
		
		//given category and store
		else
		{
			while(list_it.hasNext())
			{
				Itemlist temp_item = list_it.next() ;
				if(temp_item.getItemsByItemsId().getCategory().getId() == category_id && temp_item.getStore().getId() == store_id)
				{
					curr = new ItemlistBean(temp_item) ;
					item_list.add(curr) ;
				}
			}
		}
		
		se.close() ;
	}
	
	public void validate() {
		getData();
		request.setAttribute("categorylist", category_list) ;
		request.setAttribute("storelist", store_list) ;
		request.setAttribute("items", items) ;
		if(item_list.isEmpty())
			this.addActionMessage("您查询的信息不存在") ;
	}
	
	public String execute() throws Exception{
		request.setAttribute("itemlist", item_list) ;
		return SUCCESS;
	}

	public List<ItemlistBean> getItem_list() {
		return item_list;
	}

	public void setItem_list(List<ItemlistBean> item_list) {
		this.item_list = item_list;
	}

	public List<Store> getStore_list() {
		return store_list;
	}

	public void setStore_list(List<Store> store_list) {
		this.store_list = store_list;
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
