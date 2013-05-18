package src.com.server.hiber;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractOrders entity provides the base persistence definition of the Orders
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrders implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date time;
	private float totalPrice;
	private String staffName;
	private String storeName;
	private float profit;
	private Set orderdetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOrders() {
	}

	/** minimal constructor */
	public AbstractOrders(Date time, float totalPrice, String staffName,
			String storeName, float profit) {
		this.time = time;
		this.totalPrice = totalPrice;
		this.staffName = staffName;
		this.storeName = storeName;
		this.profit = profit;
	}

	/** full constructor */
	public AbstractOrders(Date time, float totalPrice, String staffName,
			String storeName, float profit, Set orderdetailses) {
		this.time = time;
		this.totalPrice = totalPrice;
		this.staffName = staffName;
		this.storeName = storeName;
		this.profit = profit;
		this.orderdetailses = orderdetailses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public float getProfit() {
		return this.profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public Set getOrderdetailses() {
		return this.orderdetailses;
	}

	public void setOrderdetailses(Set orderdetailses) {
		this.orderdetailses = orderdetailses;
	}

}