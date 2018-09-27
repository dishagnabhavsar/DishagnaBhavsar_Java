package code.java.constant;

public final class AppConstant {
	
	
	public enum AccountType {
		INTERNAL("I"),
	    EXTERNAL("E");
		
	    private String type;
		
		AccountType(String type){
			this.type = type;
		}
		
		public String getType() {
	        return type;
	    }
	}
	
	public enum TransactionType {
		BUY("B"),
	    SELL("S");
		
	    private String type;
		
	    TransactionType(String type){
			this.type = type;
		}
		
		public String getType() {
	        return type;
	    }
	}
	
	public enum FileNames {
		TRANSACTION("1537277231233_Input_Transactions.json"),
		INPUT_POSITION("Input_StartOfDay_Positions.csv"),
	    OUTPUT_POSITION("Output_EndOfDay_Positions.csv");
		
	    private String name;
		
	    FileNames(String name){
			this.name = name;
		}
		
		public String getName() {
	        return name;
	    }
	}

}
