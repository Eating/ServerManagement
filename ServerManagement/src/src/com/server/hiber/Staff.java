package src.com.server.hiber;

import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */
public class Staff extends AbstractStaff implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(Store store, String userName, Integer staffType,
			String password) {
		super(store, userName, staffType, password);
	}

	/** full constructor */
	public Staff(Store store, String userName, String email, Integer staffType,
			String password, Set orderses) {
		super(store, userName, email, staffType, password, orderses);
	}

}
