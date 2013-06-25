package edu.cgu.ist380b.dailyexpense.db;

public class Category {
	private int id;
	private String categoryName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String toString()
	{
		return "(ID: "+this.getId()+ ")  "
				+ this.getCategoryName()
				;
	}	
}
