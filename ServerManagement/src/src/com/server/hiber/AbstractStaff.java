package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractStaff entity provides the base persistence definition of the Staff
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStaff implements java.io.Serializable {

	// Fields

	private Integer id;
	private Store store;
	private String userName;
	private String email;
	private Integer staffType;
	private String password;
	private Set orderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractStaff() {
	}

	/** minimal constructor */
	public AbstractStaff(Store store, String userName, Integer staffType,
			String password) {
		this.store = store;
		this.userName = userName;
		this.staffType = staffType;
		this.password = password;
	}

	/** full constructor */
	public AbstractStaff(Store store, String userName, String email,
			Integer staffType, String password, Set orderses) {
		this.store = store;
		this.userName = userName;
		this.email = email;
		this.staffType = staffType;
		this.password = password;
		this.orderses = orderses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStaffType() {
		return this.staffType;
	}

	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

}