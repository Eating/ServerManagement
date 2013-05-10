package src.com.server.hiber;

import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
public class Category extends AbstractCategory implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String name) {
		super(name);
	}

	/** full constructor */
	public Category(String name, Set itemses) {
		super(name, itemses);
	}

}
