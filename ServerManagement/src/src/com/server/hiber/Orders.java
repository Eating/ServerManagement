package src.com.server.hiber;

import java.util.Date;
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
	public Orders(Store store, Staff staff, Date time, float totalPrice) {
		super(store, staff, time, totalPrice);
	}

	/** full constructor */
	public Orders(Store store, Staff staff, Date time, float totalPrice,
			Set orderdetailses) {
		super(store, staff, time, totalPrice, orderdetailses);
	}

}
