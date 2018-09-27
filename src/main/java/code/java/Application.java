package code.java;

import code.java.model.ResponseObj;
import code.java.service.LedgerService;

public class Application {
	
	public static void main(String args[]) throws Exception{
		LedgerService fileService = new LedgerService();
		ResponseObj response = fileService.calculateProcessPosition();
		System.out.println("Operation Status : "+response.getStatus());
		System.out.println("Message : "+response.getMessage());
		if(response.getEx() != null){
			response.getEx().printStackTrace();
		}
		
	}

}
