package fish.maintainer.stores;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class RmvStoreAction extends ActionSupport{
	private int rmvStoreId ;
	
	private void remove(){
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Store curr_store = (Store)se.load(Store.class, rmvStoreId) ;
		Criteria staff_cri = se.createCriteria(Staff.class) ;
		staff_cri.add(Restrictions.eq("store", curr_store)) ;
		while(staff_cri.list().iterator().hasNext())
		{
			Staff temp_staff = (Staff)staff_cri.list().iterator().next() ;
			se.delete(temp_staff) ;
		}
		se.delete(curr_store) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		remove() ;
		return SUCCESS;
	}

	public int getRmvStoreId() {
		return rmvStoreId;
	}

	public void setRmvStoreId(int rmvStoreId) {
		this.rmvStoreId = rmvStoreId;
	}
	
}
