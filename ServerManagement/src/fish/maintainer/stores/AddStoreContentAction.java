package fish.maintainer.stores;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Store;

public class AddStoreContentAction extends ActionSupport {
	private List<Integer> addContent ;
	private String addStoreName ;
	private String addStoreAddr ;
	
	private boolean add() throws UnsupportedEncodingException {
		if(addStoreName == null || addStoreName.isEmpty())
			return false ;
		if(addStoreAddr == null || addStoreAddr.isEmpty())
			return false ;
		
		String tempName = new String(addStoreName.getBytes("ISO-8859-1"),"UTF-8") ;
		Session se = HibernateSessionFactory.getSession() ;
		Criteria category_cri = se.createCriteria(Store.class) ;
		category_cri.add(Restrictions.eq("name", tempName)) ;
		if(!category_cri.list().isEmpty())
		{
			se.close() ;
			return false ;
		}
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Store newStore = new Store() ;
		String tempAddr = new String(addStoreAddr.getBytes("ISO-8859-1"),"UTF-8") ;
		newStore.setName(tempName) ;
		newStore.setAddress(tempAddr) ;
		se.save(newStore) ;
		
		Iterator<Integer> c_it = addContent.iterator() ;
		while(c_it.hasNext())
		{
			int currId = c_it.next() ;
			Items newItem = (Items)se.load(Items.class, currId) ;
			Itemlist newlist = new Itemlist() ;
			newlist.setStore(newStore) ;
			newlist.setItemsByItemsId(newItem) ;
			newlist.setDiscount((float) 0) ;
			newlist.setGiftNum(0) ;
			newlist.setItemsByGiftId(null) ;
			newlist.setNumber(0) ;
			newlist.setStock(0) ;
			newlist.setState(false) ;
			se.save(newlist) ;
		}
		
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception{
		if(!add())
			return "inputError" ;
		return SUCCESS;
	}

	public List<Integer> getAddContent() {
		return addContent;
	}

	public void setAddContent(List<Integer> addContent) {
		this.addContent = addContent;
	}

	public String getAddStoreName() {
		return addStoreName;
	}

	public void setAddStoreName(String addStoreName) {
		this.addStoreName = addStoreName;
	}

	public String getAddStoreAddr() {
		return addStoreAddr;
	}

	public void setAddStoreAddr(String addStoreAddr) {
		this.addStoreAddr = addStoreAddr;
	}

}
