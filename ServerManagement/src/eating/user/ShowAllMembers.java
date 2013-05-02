package eating.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.HibernateSessionFactory;
import src.com.server.hiber.Staff;

public class ShowAllMembers {
	private int type = 1;
	private List<AllMembersInfo> listMembers = new ArrayList<AllMembersInfo>();
	public ShowAllMembers(int type){
		this.type = type;
	}
	public void setStaff(){
		
		

		Session se = HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Staff.class);
		crit.add(Restrictions.gt("staffType", new Integer(type)));
		List<Staff> listStaff = crit.list();
		for(Staff info:listStaff){
			AllMembersInfo temp = new AllMembersInfo();
			int getType = info.getStaffType();
			String storeName = info.getStore().getName();
			String getTypeS = "";
			switch(getType){
			case 2:
				getTypeS = "二级管理员";
				break;
			case 3:
				getTypeS = "三级管理员";
				break;
			case 4:
				getTypeS = "维护人员";
				break;
			}
			temp.setUserId(info.getId());
			temp.setEmail(info.getEmail());
			temp.setUserName(info.getUserName());
			temp.setUserType(getTypeS);
			temp.setStoreName(storeName);
			listMembers.add(temp);
		}
    	
    	HibernateSessionFactory.closeSession();
		
	}
	public List<AllMembersInfo> getStaff(){
		return listMembers;
	}


}
