package src.com.server.hiber;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Set itemses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String name) {
		this.name = name;
	}

	/** full constructor */
	public Category(String name, Set itemses) {
		this.name = name;
		this.itemses = itemses;
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

	public Set getItemses() {
		return this.itemses;
	}

	public void setItemses(Set itemses) {
		this.itemses = itemses;
	}

}