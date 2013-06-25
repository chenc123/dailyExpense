package edu.cgu.ist380b.dailyexpense.db;


public class Transaction {
	private int id;
	private String item;
	private String ts;
	private Float amount;
	private int categoryId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String toString(){
		
		int id = this.getCategoryId();
		
		return  "( " + id + " )" 
				+ " Item: " + this.getItem()
				+ "   $ " + this.getAmount()
				; 
		
	}
}
