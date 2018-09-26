package code.java.service;

import java.util.Map;

import code.java.model.Account;
import code.java.model.Instrument;
import code.java.model.Transaction;

public class InstrumentService {
	
	public boolean buyInstrument(Transaction transaction, Instrument instrument){
		Map<String, Account> accounts = instrument.getAccounts();
		
		Account internalAccnt = accounts.get("I");
		internalAccnt.setQuantity(internalAccnt.getQuantity() - transaction.getQuantity());
		
		Account externalAccnt = accounts.get("E");
		internalAccnt.setQuantity(externalAccnt.getQuantity() + transaction.getQuantity());

		return true;
	}
	
	public boolean sellInstrument(Transaction transaction, Instrument instrument){
		Map<String, Account> accounts = instrument.getAccounts();
		
		Account internalAccnt = accounts.get("I");
		internalAccnt.setQuantity(internalAccnt.getQuantity() + transaction.getQuantity());
		
		Account externalAccnt = accounts.get("E");
		internalAccnt.setQuantity(externalAccnt.getQuantity() - transaction.getQuantity());

		return true;
	}

}
