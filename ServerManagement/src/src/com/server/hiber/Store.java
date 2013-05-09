package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String address;
	private Set staffs = new HashSet(0);
	private Set itemlists = new HashSet(0);

	// Constructors

	/** default constructor */
	public Store() {
	}

	/** minimal constructor */
	public Store(String name, String address) {
		this.name = name;
		this.address = address;
	}

	/** full constructor */
	public Store(String name, String address, Set staffs, Set itemlists) {
		this.name = name;
		this.address = address;
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