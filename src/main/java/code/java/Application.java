package code.java;

import code.java.service.LedgerService;

public class Application {
	
	public static void main(String args[]){
		LedgerService fileService = new LedgerService();
		try {
			fileService.calculateProcessPosition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
