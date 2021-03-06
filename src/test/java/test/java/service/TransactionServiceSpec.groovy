package test.java.service

import static org.junit.Assert.*

import code.java.model.Account
import code.java.model.Instrument
import code.java.model.Transaction
import code.java.service.TransactionService
import spock.lang.Specification

class TransactionServiceSpec extends Specification{

	TransactionService transctionService
	
	def setup() {
		transctionService = new TransactionService()
	}
	
	def "Test process transaction method"(){
		
		given: "Transaction object and instrument object"
			transaction
			instrument
		when: "process transaction based on type for transaction service"
			def result = transctionService.processTransaction(transaction, instrument)
		then: "if operation complete then it will returm true status"
			result == true
		where:
			transaction							|	instrument			 				||	status
			getTransaction(1, 'IBM', 'B', 1000)	|	getInstrument('IBM',getAccount()) 	||	true
			getTransaction(1, 'IBM', 'S', 1000)	|	getInstrument('IBM',getAccount()) 	||	true
	}
	
	
	
	private Transaction getTransaction(id,stockSymbol,type,quantity){
		Transaction transaction = new Transaction()
		transaction.setId(id)
		transaction.setStockSymbol(stockSymbol)
		transaction.setType(type)
		transaction.setQuantity(quantity)
		return transaction
	}
	
	private Instrument getInstrument(stockSymbol,accounts){
		Instrument instrument = new Instrument()
		instrument.setStockSymbol(stockSymbol)
		instrument.setAccounts(accounts)
		return instrument
	}
	
	private Map<String, Account> getAccount(){
		Map<String, Account> accounts = new HashMap<>()
		accounts.put("I", new Account(101, 'I', -100000, -100000))
		accounts.put("E", new Account(102, 'E', 100000, 100000))
		return accounts
	}

}
