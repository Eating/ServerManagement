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
	private String userName;
	private List<AllMembersInfo> listMembers = new ArrayList<AllMembersInfo>();
	private AllMembersInfo memberInfo = new AllMembersInfo();
	public ShowAllMembers(int type){
		this.type = type;
	}
	public void setUserName(String userName){
		this.userName = userName;
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
			temp.setUserTypeInt(info.getStaffType());
			temp.setUserName(info.getUserName());
			temp.setUserType(getTypeS);
			temp.setStoreName(storeName);
			listMembers.add(temp);
		}
    	
    	HibernateSessionFactory.closeSession();
		
	}
	public void setOneStaff(){
		Session se = HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Staff.class);
		crit.add(Restrictions.eq("userName", userName));
		List<Staff> listStaff = crit.list();
			
			Staff st = listStaff.get(0);
			int getType = st.getStaffType();
			String storeName = st.getStore().getName();
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
			memberInfo.setUserId(st.getId());
			memberInfo.setEmail(st.getEmail());
			memberInfo.setUserTypeInt(st.getStaffType());
			memberInfo.setUserName(st.getUserName());
			memberInfo.setUserType(getTypeS);
			memberInfo.setStoreName(storeName);
			HibernateSessionFactory.closeSession();
	}
	public AllMembersInfo getOneStaff(){
		return memberInfo;
	}
	public List<AllMembersInfo> getStaff(){
		return listMembers;
	}


}
