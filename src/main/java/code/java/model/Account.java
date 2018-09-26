package code.java.model;

public class Account {

	private int accntNo;
	private String accntType;
	private Long quantity;
	private Long delta;
	
	public int getAccntNo() {
		return accntNo;
	}
	public void setAccntNo(int accntNo) {
		this.accntNo = accntNo;
	}
	public String getAccntType() {
		return accntType;
	}
	public void setAccntType(String accntType) {
		this.accntType = accntType;
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
		return "Account [accntNo=" + accntNo + ", accntType=" + accntType + ", quantity=" + quantity + ", delta="
				+ delta + "]";
	}
}
