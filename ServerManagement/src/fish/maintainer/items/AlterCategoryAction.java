package fish.maintainer.items;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Itemlist;

import com.opensymphony.xwork2.ActionSupport;

public class AlterCategoryAction extends ActionSupport {
	private String categoryName ;
	private int alterCategoryId ;
	
	private void alter() throws UnsupportedEncodingException {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Category currCategory = (Category)se.load(Category.class, alterCategoryId) ;
		String tempName = new String(categoryName.getBytes("ISO-8859-1"),"UTF-8") ;
		currCategory.setName(tempName) ;
		se.update(currCategory) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception {
		if(!(categoryName == null || categoryName.isEmpty()))
			alter() ;
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
