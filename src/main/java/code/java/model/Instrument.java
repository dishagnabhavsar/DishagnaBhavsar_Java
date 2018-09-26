package code.java.model;

import java.util.Map;

public class Instrument {

	private String stockSymbol;
	Map<String, Account> accounts;
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public Map<String, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Map<String, Account> accounts) {
		this.accounts = accounts;
	}
	
	@Override
	public String toString() {
		return "Instrument [stockSymbol=" + stockSymbol + ", accounts=" + accounts + "]";
	}
}
