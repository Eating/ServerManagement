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
	public Orders(Date time, float totalPrice, String staffName,
			String storeName, float profit) {
		super(time, totalPrice, staffName, storeName, profit);
	}

	/** full constructor */
	public Orders(Date time, float totalPrice, String staffName,
			String storeName, float profit, Set orderdetailses) {
		super(time, totalPrice, staffName, storeName, profit, orderdetailses);
	}

}
