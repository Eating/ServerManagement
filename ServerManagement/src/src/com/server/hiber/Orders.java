package src.com.server.hiber;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
public class Orders extends AbstractOrders implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Store store, Staff staff, Timestamp time, Float totalPrice) {
		super(store, staff, time, totalPrice);
	}

	/** full constructor */
	public Orders(Store store, Staff staff, Timestamp time, Float totalPrice,
			Set orderdetailses) {
		super(store, staff, time, totalPrice, orderdetailses);
	}

}
