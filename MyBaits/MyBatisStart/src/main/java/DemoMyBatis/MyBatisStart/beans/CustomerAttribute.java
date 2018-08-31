package DemoMyBatis.MyBatisStart.beans;

import java.util.List;

public class CustomerAttribute {
	private long id;
	private String attrName;
	private String attrValue;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
}
