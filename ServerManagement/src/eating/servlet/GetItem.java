package eating.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import src.com.server.hiber.Category;
import src.com.server.hiber.Itemlist;
import src.com.server.hiber.Items;
import src.com.server.hiber.Staff;
import src.com.server.hiber.Store;
import src.com.server.json.JSONArray;
import src.com.server.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class GetItem extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String itemId;
	private String catoName;
	private int storeId;
	private Store store;

	public void getItem() throws IOException {
		itemId = request.getParameter("itemId");
		catoName = request.getParameter("catoName");
		storeId = Integer.valueOf(request.getParameter("storeId"));

		System.out.println("we get the itemid" + itemId
				+ "@@ we get the catoName" + catoName);
		System.out.println("we get the storeId" + storeId);
		if (itemId.equals("")) {
			// 通过cato获取
			System.out.println("to get data by cato");
			getItemByCato();
		} else if (catoName == null) {
			// 通过id获取

		}
	}

	public void getItemByCato() throws IOException {
		
		Session se = src.com.server.hiber.HibernateSessionFactory.getSession();
		Criteria critGetCatoId = se.createCriteria(Category.class);
		critGetCatoId.add(Restrictions.eq("name", catoName));
		// crit.add(Restrictions.eq("password", password));
		List<Category> list = critGetCatoId.list();
		JSONArray jsonArray = new JSONArray();
		if (list.size() != 0) {
			System.out.println("begin get now");
			
			
			store = (Store) se.get(Store.class, new Integer(storeId));
			
			System.out.println("catoid is:" + list.get(0).getId());
			Criteria critGetItemId = se.createCriteria(Items.class);
			critGetItemId.add(Restrictions.eq("category", list.get(0)));
			List<Items> listItems = critGetItemId.list();
			System.out.println("catoid is2:" + list.get(0).getId());
			int length = listItems.size();
			System.out.println("the length is" + length);
			
			for (int i = 0; i < length; i++) {
				Criteria critGetItemList = se.createCriteria(Itemlist.class);
				critGetItemList.add(Restrictions.eq("itemsByItemsId",
						listItems.get(i)));
				critGetItemList.add(Restrictions.eq("store", store));
				List<Itemlist> itemDetails = critGetItemList.list();
				
				
				if (itemDetails.size() != 0) { // 对应商店有商品，把信息写入json
					System.out.println(itemDetails.size());
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("stock", itemDetails.get(0).getStock());
					jsonObject.put("discount", itemDetails.get(0).getDiscount());
					jsonObject.put("giftnum", itemDetails.get(0).getGiftNum());
					if (itemDetails.get(0).getGiftNum() == 0)
						jsonObject.put("giftId", "");
					else{
						jsonObject.put("giftId",itemDetails.get(0).getItemsByGiftId().getName());
					}
					jsonObject.put("number", itemDetails.get(0).getNumber());

					jsonObject.put("itemname", listItems.get(i).getName());
					jsonObject.put("itemprice", listItems.get(i).getPrice());
					jsonArray.put(jsonObject);
				}
			}  // for 循环结束后，获得jsonarray对象
		}

		this.response.setContentType("text/html;charset=utf-8");
		this.response.setCharacterEncoding("UTF-8");
		this.response.getWriter().write(jsonArray.toString());
			

	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		// TODO Auto-generated method stub
		response = res;

	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		request = req;
	}

}
