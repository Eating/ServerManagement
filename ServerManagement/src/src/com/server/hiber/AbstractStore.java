package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractStore entity provides the base persistence definition of the Store
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStore implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String address;
	private Set orderses = new HashSet(0);
	private Set staffs = new HashSet(0);
	private Set itemlists = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractStore() {
	}

	/** minimal constructor */
	public AbstractStore(String name, String address) {
		this.name = name;
		this.address = address;
	}

	/** full constructor */
	public AbstractStore(String name, String address, Set orderses, Set staffs,
			Set itemlists) {
		this.name = name;
		this.address = address;
		this.orderses = orderses;
		this.staffs = staffs;
		this.itemlists = itemlists;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

	public Set getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set staffs) {
		this.staffs = staffs;
	}

	public Set getItemlists() {
		return this.itemlists;
	}

	public void setItemlists(Set itemlists) {
		this.itemlists = itemlists;
	}

}