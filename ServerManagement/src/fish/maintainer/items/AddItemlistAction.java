package fish.maintainer.items;

import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class AddItemlistAction extends ActionSupport {
	private int addListStore ;
	private int addListItem ;
	
	private void add() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Store currStore = (Store)se.load(Store.class, addListStore) ;
		Items currItem = (Items)se.load(Items.class, addListItem) ;
		Itemlist newList = new Itemlist() ;
		newList.setStore(currStore) ;
		newList.setItemsByItemsId(currItem) ;
		newList.setDiscount((float) 0) ;
		newList.setGiftNum(0) ;
		newList.setItemsByGiftId(null) ;
		newList.setNumber(0) ;
		newList.setStock(0) ;
		newList.setState(false) ;
		se.save(newList) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception {
		add() ;
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
