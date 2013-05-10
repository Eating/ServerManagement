package fish.maintainer.items;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class RmvCategoryAction extends ActionSupport {
	private int rmvCategoryId ;
	
	private void remove() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Category curr_cate = (Category)se.load(Category.class, rmvCategoryId) ;
		Criteria item_cri = se.createCriteria(Items.class) ;
		item_cri.add(Restrictions.eq("category", curr_cate)) ;
		while(item_cri.list().iterator().hasNext())
		{
			Items temp_item = (Items)item_cri.list().iterator().next() ;
			Criteria list_cri = se.createCriteria(Itemlist.class) ;
			list_cri.add(Restrictions.eq("itemsByItemsId", temp_item)) ;
			while(list_cri.list().iterator().hasNext())
			{
				Itemlist temp_list = (Itemlist)list_cri.list().iterator().next() ;
				se.delete(temp_list) ;
			}
			se.delete(temp_item) ;
		}
		se.delete(curr_cate) ;
		tran.commit() ;
		se.close() ;
	}
	public String execute() throws Exception{
		remove() ;
		return SUCCESS;
	}
	public int getRmvCategoryId() {
		return rmvCategoryId;
	}
	public void setRmvCategoryId(int rmvCategoryId) {
		this.rmvCategoryId = rmvCategoryId;
	}

}
