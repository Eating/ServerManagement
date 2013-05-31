package fish.maintainer.stores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Store;

import com.opensymphony.xwork2.ActionSupport;

public class MaintainStoresAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request ;
	private List<Store> store_list ;
	private int flag ;
	
	void getData()
	{
		Session se = HibernateSessionFactory.getSession() ;
		Criteria store_cri = se.createCriteria(Store.class) ;
		store_cri.add(Restrictions.and(Restrictions.ne("name", "superDepartment"), Restrictions.ne("name", "server"))) ;
		store_list = store_cri.list() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData() ;
		request.setAttribute("storelist", store_list) ;
		if(flag == 1)
			request.setAttribute("inputError", "您输入的名称已存在或输入内容有误") ;
		return SUCCESS;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}
}
