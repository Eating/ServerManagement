package src.com.server.hiber;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders entity. @author MyEclipse Persistence Tools
 */

public class Orders implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date time;
	private Float totalPrice;
	private String staffName;
	private String storeName;
	private Float profit;
	private Set orderdetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Orders() {
	}

	/** minimal constructor */
	public Orders(Date time, Float totalPrice, String staffName,
			String storeName, Float profit) {
		this.time = time;
		this.totalPrice = totalPrice;
		this.staffName = staffName;
		this.storeName = storeName;
		this.profit = profit;
	}

	/** full constructor */
	public Orders(Date time, Float totalPrice, String staffName,
			String storeName, Float profit, Set orderdetailses) {
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

	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
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

	public Float getProfit() {
		return this.profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	public Set getOrderdetailses() {
		return this.orderdetailses;
	}

	public void setOrderdetailses(Set orderdetailses) {
		this.orderdetailses = orderdetailses;
	}

}