package fish.maintainer.items;

import java.io.UnsupportedEncodingException;
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

public class SearchItemlistAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<ItemlistBean> item_list ;
	private ItemlistBean curr ;
	private String search_store ;
	private String search_item ;
	
	public boolean getData() throws UnsupportedEncodingException {
		Session se = HibernateSessionFactory.getSession() ;
		item_list = new LinkedList<ItemlistBean>() ;
		
		Criteria item_cri = se.createCriteria(Items.class) ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		String transStore = new String(search_store.getBytes("ISO-8859-1"),"UTF-8") ;
		String transItem = new String(search_item.getBytes("ISO-8859-1"),"UTF-8") ;
		item_cri.add(Restrictions.like("name", "%"+transItem+"%")) ;
		store_cri.add(Restrictions.like("name", "%"+transStore+"%")) ;
		List<Items> items = item_cri.list() ;
		List<Store> stores = store_cri.list() ;
		if(items.isEmpty() && stores.isEmpty())
			return false ;
		
		//此时search_item不为空
		if(search_store == null || search_store.isEmpty())
		{
			if(items.isEmpty())
				return false ;
			
			Iterator<Items> item_it = items.iterator() ;
			while(item_it.hasNext())
			{
				Items currItem = item_it.next() ;
				Criteria list_cri = se.createCriteria(Itemlist.class) ;
				List<Itemlist> currList = list_cri.list() ;
				Iterator<Itemlist> list_it = currList.iterator() ;
				while(list_it.hasNext())
				{	
					Itemlist tempList = list_it.next() ;
					if(tempList.getItemsByItemsId().equals(currItem))
					{
						curr = new ItemlistBean(tempList) ;
						item_list.add(curr) ;
					}
				}
			}
		}
		//此时search_store不为空
		else if(search_item == null || search_item.isEmpty())
		{
			if(stores.isEmpty())
				return false ;
			
			Iterator<Store> store_it = stores.iterator() ;
			while(store_it.hasNext())
			{
				Store currStore = store_it.next() ;
				Criteria list_cri = se.createCriteria(Itemlist.class) ;
				List<Itemlist> currList = list_cri.list() ;
				Iterator<Itemlist> list_it = currList.iterator() ;
				while(list_it.hasNext())
				{
					Itemlist tempList = list_it.next() ;
					if(tempList.getStore().equals(currStore))
					{
						curr = new ItemlistBean(tempList) ;
						item_list.add(curr) ;
					}
				}
			}
		}
		
		else
		{
			Iterator<Items> item_it = items.iterator() ;
			while(item_it.hasNext())
			{
				Items currItem = item_it.next() ;
				Iterator<Store> store_it = stores.iterator() ;
				while(store_it.hasNext())
				{
					Store currStore = store_it.next() ;
					Criteria list_cri = se.createCriteria(Itemlist.class) ;
					List<Itemlist> currList = list_cri.list() ;
					Iterator<Itemlist> list_it = currList.iterator() ;
					while(list_it.hasNext())
					{
						Itemlist tempList = list_it.next() ;
						if(tempList.getItemsByItemsId().equals(currItem) && tempList.getStore().equals(currStore))
						{
							curr = new ItemlistBean(tempList) ;
							item_list.add(curr) ;
						}
					}
				}
			}
		}
		
		se.close() ;
		
		return true ;
	}
	
	public void validate() {
		if((search_store == null || search_store.isEmpty()) && (search_item == null || search_item.isEmpty()))
			this.addActionError("请输入查询信息") ;

		try {
			if(getData())
				this.addActionMessage("您查询的信息不存在") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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

	public String getSearch_store() {
		return search_store;
	}

	public void setSearch_store(String search_store) {
		this.search_store = search_store;
	}

	public String getSearch_item() {
		return search_item;
	}

	public void setSearch_item(String search_item) {
		this.search_item = search_item;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
