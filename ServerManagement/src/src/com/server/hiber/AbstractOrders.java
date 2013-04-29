package src.com.server.hiber;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractOrders entity provides the base persistence definition of the Orders
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrders implements java.io.Serializable {

	// Fields

	private Integer id;
	private Store store;
	private Staff staff;
	private Timestamp time;
	private Float totalPrice;
	private Set orderdetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOrders() {
	}

	/** minimal constructor */
	public AbstractOrders(Store store, Staff staff, Timestamp time,
			Float totalPrice) {
		this.store = store;
		this.staff = staff;
		this.time = time;
		this.totalPrice = totalPrice;
	}

	/** full constructor */
	public AbstractOrders(Store store, Staff staff, Timestamp time,
			Float totalPrice, Set orderdetailses) {
		this.store = store;
		this.staff = staff;
		this.time = time;
		this.totalPrice = totalPrice;
		this.orderdetailses = orderdetailses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set getOrderdetailses() {
		return this.orderdetailses;
	}

	public void setOrderdetailses(Set orderdetailses) {
		this.orderdetailses = orderdetailses;
	}

}