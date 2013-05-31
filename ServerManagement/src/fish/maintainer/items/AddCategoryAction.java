package fish.maintainer.items;

import java.io.UnsupportedEncodingException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Items;

import com.opensymphony.xwork2.ActionSupport;

public class AddCategoryAction extends ActionSupport {
	private String addCateName ;

	boolean add() throws UnsupportedEncodingException {
			String tempName = new String(addCateName.getBytes("ISO-8859-1"),"UTF-8") ;
			Session se = HibernateSessionFactory.getSession() ;
			Criteria category_cri = se.createCriteria(Category.class) ;
			category_cri.add(Restrictions.eq("name", tempName)) ;
			if(!category_cri.list().isEmpty())
			{
				se.close() ;
				return false ;
			}
			
			Transaction tran = se.beginTransaction() ;
			tran.begin() ;
			Category newCate = new Category() ;
			newCate.setName(tempName) ;
			se.save(newCate) ;
			tran.commit() ;
			se.close() ;
		
			return true ;
	}
	
	public String execute() throws Exception {
		if(addCateName == null || addCateName.isEmpty())
			return "inputError" ;
		if(!add()) 
			return "inputError" ;
		
		return SUCCESS;
	}

	public String getAddCateName() {
		return addCateName;
	}

	public void setAddCateName(String addCateName) {
		this.addCateName = addCateName;
	}
	
}
