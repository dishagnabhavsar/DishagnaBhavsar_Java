package code.java.model;

public class InputPosition {
	private String instrument;
	private String account;
	private String accountType;
	private String quantity;
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "InputPosition [instrument=" + instrument + ", account=" + account + ", accountType=" + accountType
				+ ", quantity=" + quantity + "]";
	}
}
