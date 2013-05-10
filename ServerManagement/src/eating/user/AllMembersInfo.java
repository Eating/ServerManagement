package eating.user;

public class AllMembersInfo {
	private int userId;
	private String userName;
	private String email;
	private String storeName;
	private int storeId;
	private String userType;
	private int userTypeInt;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoretId(int storeId) {
		this.storeId = storeId;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getUserTypeInt() {
		return userTypeInt;
	}
	public void setUserTypeInt(int userTypeInt) {
		this.userTypeInt = userTypeInt;
	}
	
}
