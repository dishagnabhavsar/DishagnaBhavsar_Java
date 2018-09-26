package code.java.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4230830368013889610L;
	
	@JsonProperty("TransactionId")
	private int id;
	
	@JsonProperty("Instrument")
	private String stockSymbol;
	
	@JsonProperty("TransactionType")
	private String type;
	
	@JsonProperty("TransactionQuantity")
	private Long quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", stockSymbol=" + stockSymbol + ", type=" + type + ", quantity=" + quantity
				+ "]";
	}
}
