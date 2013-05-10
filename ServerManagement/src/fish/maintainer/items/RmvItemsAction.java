package fish.maintainer.items;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class RmvItemsAction extends ActionSupport {
	private int rmvItemsId ;
	
	private void remove() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Items curr_item = (Items)se.load(Items.class, rmvItemsId) ;
		Criteria list_cri = se.createCriteria(Itemlist.class) ;
		list_cri.add(Restrictions.eq("itemsByItemsId", curr_item)) ;
		while(list_cri.list().iterator().hasNext())
		{
			Itemlist temp_list = (Itemlist)list_cri.list().iterator().next() ;
			se.delete(temp_list) ;
		}
		se.delete(curr_item) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		remove() ;
		return SUCCESS;
	}

	public int getRmvItemsId() {
		return rmvItemsId;
	}

	public void setRmvItemsId(int rmvItemsId) {
		this.rmvItemsId = rmvItemsId;
	}

}
