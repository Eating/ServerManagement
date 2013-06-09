package fish.maintainer.items;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class AddItemlistAction extends ActionSupport {
	private int addListStore ;
	private int addListItem ;
	
	private boolean add() {
		Session se = HibernateSessionFactory.getSession() ;
		Store currStore = (Store)se.load(Store.class, addListStore) ;
		Items currItem = (Items)se.load(Items.class, addListItem) ;
		Criteria list_cri = se.createCriteria(Itemlist.class) ;
		list_cri.add(Restrictions.and(Restrictions.eq("itemsByItemsId", currItem), Restrictions.eq("store", currStore))) ;
		List<Itemlist> currlist = list_cri.list() ;
		if(!currlist.isEmpty())
			return false ;
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		
		Itemlist newList = new Itemlist() ;
		newList.setStore(currStore) ;
		newList.setItemsByItemsId(currItem) ;
		newList.setDiscount((float) 1) ;
		newList.setGiftNum(0) ;
		newList.setItemsByGiftId(null) ;
		newList.setNumber(0) ;
		newList.setStock(0) ;
		se.save(newList) ;
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception {
		if(addListStore != 0 && addListItem != 0)
			if(!add())
				return "inputError" ;
		return SUCCESS;
	}

	public int getAddListStore() {
		return addListStore;
	}

	public void setAddListStore(int addListStore) {
		this.addListStore = addListStore;
	}

	public int getAddListItem() {
		return addListItem;
	}

	public void setAddListItem(int addListItem) {
		this.addListItem = addListItem;
	}
	
}
