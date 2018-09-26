package code.java.model;

public class OutputPosition {
	private String instrument;
	private String account;
	private String accountType;
	private String quantity;
	private String delta;
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
	public String getDelta() {
		return delta;
	}
	public void setDelta(String delta) {
		this.delta = delta;
	}
	@Override
	public String toString() {
		return "OutputPosition [instrument=" + instrument + ", account=" + account + ", accountType=" + accountType
				+ ", quantity=" + quantity + ", delta=" + delta + "]";
	}
	
	
}
