package fish.maintainer.items;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;

import com.opensymphony.xwork2.ActionSupport;

public class AlterCategoryAction extends ActionSupport {
	private String categoryName ;
	private int alterCategoryId ;
	
	private boolean alter() throws UnsupportedEncodingException {
		String tempName = new String(categoryName.getBytes("ISO-8859-1"),"UTF-8") ;
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Category currCategory = (Category)se.load(Category.class, alterCategoryId) ;
		
		if(!currCategory.getName().equals(tempName))
		{
			Criteria category_cri = se.createCriteria(Category.class) ;
			category_cri.add(Restrictions.eq("name", tempName)) ;
			List<Category> tempL = category_cri.list() ;
			if(!tempL.isEmpty())
			{
				se.close() ;
				return false ;
			}
		}
		
		currCategory.setName(tempName) ;
		se.update(currCategory) ;
		tran.commit() ;
		se.close() ;
		
		return true ;
	}
	
	public String execute() throws Exception {
		if(categoryName == null || categoryName.isEmpty())
			return "inputError" ;
		if(!alter())
			return "inputError" ;
		
		return SUCCESS;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getAlterCategoryId() {
		return alterCategoryId;
	}

	public void setAlterCategoryId(int alterCategoryId) {
		this.alterCategoryId = alterCategoryId;
	}

}
