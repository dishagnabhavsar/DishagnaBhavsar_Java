package code.java.model;

public class OutputPosition {
	private String instrument;
	private int account;
	private String accountType;
	private Long quantity;
	private Long delta;
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getDelta() {
		return delta;
	}
	public void setDelta(Long delta) {
		this.delta = delta;
	}
	@Override
	public String toString() {
		return "OutputPosition [instrument=" + instrument + ", account=" + account + ", accountType=" + accountType
				+ ", quantity=" + quantity + ", delta=" + delta + "]";
	}
	
	
}
