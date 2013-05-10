package fish.maintainer.items;

import java.io.UnsupportedEncodingException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import src.com.server.hiber.Category;
import src.com.server.hiber.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;

public class AddCategoryAction extends ActionSupport {
	private String addCateName ;

	//缺少重名与名为空的提示
	void add() throws UnsupportedEncodingException {
		if(!(addCateName == null || addCateName.isEmpty()))
		{
			Session se = HibernateSessionFactory.getSession() ;
			Transaction tran = se.beginTransaction() ;
			tran.begin() ;
			Category newCate = new Category() ;
			String tempName = new String(addCateName.getBytes("ISO-8859-1"),"UTF-8") ;
			newCate.setName(tempName) ;
			se.save(newCate) ;
			tran.commit() ;
			se.close() ;
		}
	}
	
	public String execute() throws Exception {
		add() ;
		return SUCCESS;
	}

	public String getAddCateName() {
		return addCateName;
	}

	public void setAddCateName(String addCateName) {
		this.addCateName = addCateName;
	}
	
}
