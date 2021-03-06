package fish.maintainer.stores;

import java.io.UnsupportedEncodingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class AddStoreAction extends ActionSupport{
	private String addStoreName ;
	private String addStoreAddr ;
	
	private boolean add() throws UnsupportedEncodingException {
		if(addStoreName == null || addStoreName.isEmpty())
			return false ;
		if(addStoreAddr == null || addStoreAddr.isEmpty())
			return false ;
		
		String tempName = new String(addStoreName.getBytes("ISO-8859-1"),"UTF-8") ;
		String tempAddr = new String(addStoreAddr.getBytes("ISO-8859-1"),"UTF-8") ;
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
		newStore.setName(tempName) ;
		newStore.setAddress(tempAddr) ;
		se.save(newStore) ;
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception{
		if(!add())
			return "inputError" ;
		return SUCCESS;
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
