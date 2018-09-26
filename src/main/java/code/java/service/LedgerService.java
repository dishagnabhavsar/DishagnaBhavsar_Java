package code.java.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.java.model.Account;
import code.java.model.InputPosition;
import code.java.model.Instrument;
import code.java.model.Transaction;

public class LedgerService {
	
	public void calculateProcessPosition() throws Exception{
		Map<String, Instrument> positions = initPositions();
		 
		TransactionService transactionService = new TransactionService();
		List<Transaction> transactions = transactionService.fetchTransactions();
		Instrument instrument = null;
		Map<String, Account> accounts = null;
		for(Transaction transaction: transactions){
			instrument = positions.get(transaction.getStockSymbol());
			if(instrument == null){
				instrument = new Instrument();
				accounts = new HashMap<>();
				accounts.put(transaction.getType(),createNewAccount(0, transaction.getType(), 0L));
				instrument.setAccounts(accounts);
				positions.put(transaction.getStockSymbol(), instrument);
			}
			if(!transactionService.processTransaction(transaction, instrument)){
				
			}
			System.out.println(positions);
		}
	}
	
	private Map<String, Instrument> initPositions() throws Exception{
		FileService fileService =new FileService();
		Map<String, Instrument> positions = new HashMap<>();
		List<InputPosition> inputPositions = fileService.readPositionFile();
		Instrument instrument = null;
		Account account = null;
		Map<String, Account> accounts = null;
		for(InputPosition inputPosition : inputPositions ){
			instrument = positions.get(inputPosition.getInstrument());
			if(instrument == null){
				instrument = new Instrument();
				instrument.setStockSymbol(inputPosition.getInstrument());
				accounts = new HashMap<>();
				accounts.put(inputPosition.getAccountType(),createNewAccount(Integer.parseInt(inputPosition.getAccount()), inputPosition.getAccountType(), Long.parseLong(inputPosition.getQuantity())));
				instrument.setAccounts(accounts);
				positions.put(inputPosition.getInstrument(), instrument);
			}
			
			accounts = instrument.getAccounts();
			account = accounts.get(inputPosition.getAccountType());
			
			if(account == null){
				accounts.put(inputPosition.getAccountType(),createNewAccount(Integer.parseInt(inputPosition.getAccount()), inputPosition.getAccountType(), Long.parseLong(inputPosition.getQuantity())));
			}
		}
		return positions;
	}
	
	private Account createNewAccount(int accntNo, String accntType, Long qty){
		Account account = new Account();
		account.setAccntNo(accntNo);
		account.setAccntType(accntType);
		account.setQuantity(qty);
		account.setDelta(qty);
		return account;
	}
}
