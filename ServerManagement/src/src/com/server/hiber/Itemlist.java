package src.com.server.hiber;

import java.util.Set;

/**
 * Itemlist entity. @author MyEclipse Persistence Tools
 */
public class Itemlist extends AbstractItemlist implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Itemlist() {
	}

	/** minimal constructor */
	public Itemlist(Store store, Items itemsByItemsId, Integer stock,
			Double discount, Boolean state) {
		super(store, itemsByItemsId, stock, discount, state);
	}

	/** full constructor */
	public Itemlist(Store store, Items itemsByItemsId, Items itemsByGiftId,
			Integer stock, Double discount, Integer giftNum, Boolean state,
			Set orderdetailses) {
		super(store, itemsByItemsId, itemsByGiftId, stock, discount, giftNum,
				state, orderdetailses);
	}

}
