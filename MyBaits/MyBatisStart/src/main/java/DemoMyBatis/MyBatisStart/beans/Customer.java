package DemoMyBatis.MyBatisStart.beans;

import java.util.Date;
import java.util.List;

public class Customer {
	private long id;
	private String userName;
	private String password;
	private String nickName;
	private String email;
	private boolean isAdmin;
	private Date createDate;
	private List<CustomerAttribute> attributes;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<CustomerAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<CustomerAttribute> attributes) {
		this.attributes = attributes;
	}
	
}
