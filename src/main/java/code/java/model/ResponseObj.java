package code.java.model;

public class ResponseObj {
	
	private String status;
	private String message;
	private Exception ex;
	
	public ResponseObj(String status,String message, Exception ex){
		this.status = status;
		this.message = message;
		this.ex = ex;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
	@Override
	public String toString() {
		return "ResponseObj [status=" + status + ", message=" + message + ", ex=" + ex + "]";
	}
}
