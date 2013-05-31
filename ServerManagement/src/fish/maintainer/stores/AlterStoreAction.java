package fish.maintainer.stores;

import java.io.UnsupportedEncodingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class AlterStoreAction extends ActionSupport{
	private int alterStoreId ;
	private String storeName ;
	private String storeAddr ;
	
	private boolean alter() throws UnsupportedEncodingException
	{
		if(storeName == null || storeName.isEmpty())
			return false ;
		if(storeAddr == null || storeAddr.isEmpty())
			return false ;
		
		String tempName = new String(storeName.getBytes("ISO-8859-1"),"UTF-8") ;
		Session se = HibernateSessionFactory.getSession() ;
		Store tempS = (Store)se.load(Store.class, alterStoreId) ;
		if(!tempS.getName().equals(tempName))
		{
			Criteria category_cri = se.createCriteria(Store.class) ;
			category_cri.add(Restrictions.eq("name", tempName)) ;
			if(!category_cri.list().isEmpty())
			{
				se.close() ;
				return false ;
			}
		}
			
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Store currStore = (Store)se.load(Store.class, alterStoreId) ;
		
		currStore.setName(tempName) ;
		String tempAddr = new String(storeAddr.getBytes("ISO-8859-1"),"UTF-8") ;
		currStore.setAddress(tempAddr) ;
	
		se.update(currStore) ;
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception{
		if(!alter())
			return "inputError" ;
		return SUCCESS;
	}

	public int getAlterStoreId() {
		return alterStoreId;
	}

	public void setAlterStoreId(int alterStoreId) {
		this.alterStoreId = alterStoreId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	
}
