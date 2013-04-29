package src.com.server.hiber;

import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */
public class Store extends AbstractStore implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Store() {
	}

	/** minimal constructor */
	public Store(String name, String address) {
		super(name, address);
	}

	/** full constructor */
	public Store(String name, String address, Set orderses, Set staffs,
			Set itemlists) {
		super(name, address, orderses, staffs, itemlists);
	}

}
