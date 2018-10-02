package test.java.service

import static org.junit.Assert.*

import java.util.Map

import code.java.model.Account
import code.java.model.Instrument
import code.java.model.Transaction
import code.java.service.LedgerService
import spock.lang.Specification

class LedgerServiceSpec extends Specification{

	LedgerService ledgerService
	
	def setup() {
		ledgerService = new LedgerService()
	}
	
	def "Test count delta method"(){
		
		given: "position object is given"
			positions
		when: "count delta perfoem on positions"
			def result = ledgerService.countDelta(positions)
		then: "if operation complete then it will count delta value"
			result.get(0).getDelta() == delta
		where:
			positions			 								||	delta
			getPositions(getInstrument('IBM',getAccount())) 	||	0
	}
	
	private Transaction getTransaction(id,stockSymbol,type,quantity){
		Transaction transaction = new Transaction()
		transaction.setId(id)
		transaction.setStockSymbol(stockSymbol)
		transaction.setType(type)
		transaction.setQuantity(quantity)
		return transaction
	}
	
	private Map<String, Instrument> getPositions(instrument){
		Map<String, Instrument> positions = new HashMap<>()
		positions.put("IBM", instrument)
		return positions
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
