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
	private int category_id, store_id ;
	private String cateDefault, storeDefault ;
	private ItemlistBean curr ;
	private int flag ;
	private int stockFlag ;
	
	public void getData()
	{
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_cri.add(Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server"))) ;
		store_list = store_cri.list() ;
		Criteria category_cri = se.createCriteria(Category.class) ;
		category_list = category_cri.list() ;
		Criteria items_cri = se.createCriteria(Items.class) ;
		items = items_cri.list() ;
		
		item_list = new LinkedList<ItemlistBean>() ;
		Criteria itemlist_cri = se.createCriteria(Itemlist.class) ;
		Iterator<Itemlist> list_it = itemlist_cri.list().iterator() ;
		
		//default
		if(category_id == 0 && store_id == 0)
		{	
			cateDefault = "�������" ;
			storeDefault = "���е���" ;
			while(list_it.hasNext())
			{
				curr = new ItemlistBean(list_it.next()) ;
				item_list.add(curr) ;
			}
		}
		
		//given store
		else if(category_id == 0 && store_id != 0)
		{
			cateDefault = "�������" ;
			storeDefault = ((Store)se.load(Store.class, store_id)).getName() ;
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
			cateDefault = ((Category)se.load(Category.class, category_id)).getName() ;
			storeDefault = "���е���" ;
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
			cateDefault = ((Category)se.load(Category.class, category_id)).getName() ;
			storeDefault = ((Store)se.load(Store.class, store_id)).getName() ;
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
		request.setAttribute("cateDefault", cateDefault) ;
		request.setAttribute("storeDefault", storeDefault) ;
		if(item_list.isEmpty())
			this.addActionMessage("����ѯ����Ϣ������") ;
	}
	
	public String execute() throws Exception{
		request.setAttribute("itemlist", item_list) ;
		if(flag == 1)
			request.setAttribute("inputError", "��������������� (������Ϣ�������磺0.88)") ;
		if(stockFlag == 1)
			request.setAttribute("notEnough", "��治���������ֵ���������ֵΪ"+Integer.MAX_VALUE+")") ;
		
		return SUCCESS;
	}
	
	public String getCateDefault() {
		return cateDefault;
	}

	public void setCateDefault(String cateDefault) {
		this.cateDefault = cateDefault;
	}

	public String getStoreDefault() {
		return storeDefault;
	}

	public void setStoreDefault(String storeDefault) {
		this.storeDefault = storeDefault;
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getStockFlag() {
		return stockFlag;
	}

	public void setStockFlag(int stockFlag) {
		this.stockFlag = stockFlag;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
