package fish.maintainer.stores;

import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class AlterStoreAction extends ActionSupport{
	private int alterStoreId ;
	private String storeName ;
	private String storeAddr ;
	
	private void alter() throws UnsupportedEncodingException
	{
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Store currStore = (Store)se.load(Store.class, alterStoreId) ;
		if(!(storeName == null || storeName.isEmpty()))
		{
			String tempName = new String(storeName.getBytes("ISO-8859-1"),"UTF-8") ;
			currStore.setName(tempName) ;
		}
		if(!(storeAddr == null || storeAddr.isEmpty()))
		{
			String tempAddr = new String(storeAddr.getBytes("ISO-8859-1"),"UTF-8") ;
			currStore.setAddress(tempAddr) ;
		}
		se.update(currStore) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		alter() ;
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
