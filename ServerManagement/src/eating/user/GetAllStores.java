package eating.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

public class GetAllStores {
	private List<Store> storesInfo;
	public void setStore(){
		Session se = HibernateSessionFactory.getSession();
		Criteria crit2 = se.createCriteria(Store.class);
		storesInfo = crit2.list();
		se.close();
		
	}
	public List<Store> getStore(){
		return storesInfo;
	}
}
