package code.java.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import code.java.constant.AppConstant;
import code.java.model.Account;
import code.java.model.InputPosition;
import code.java.model.Instrument;
import code.java.model.OutputPosition;
import code.java.model.ResponseObj;
import code.java.model.Transaction;

public class LedgerService {
	
	public ResponseObj calculateProcessPosition() throws JsonParseException, JsonMappingException, IOException{
		try{
			Map<String, Instrument> positions = initPositions();
			if(positions.isEmpty()){
				return new ResponseObj("Failure", "Error occured while reading startOfDay positions.", null );
			}
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
					return new ResponseObj("Failure", "Error occured while processing transactions.", null );
				}
				if(!outputPositions(positions)){
					return new ResponseObj("Failure", "Error occured while processing the EndOfDay postions file.", null );
				}
			}
			return new ResponseObj("Success", "Calculate process position successfully completed.", null );
		}catch(JsonParseException ex){
			return new ResponseObj("Failure", "Error occured while parsing the file.", ex );
		}catch(JsonMappingException ex){
			return new ResponseObj("Failure", "Error occured while mapping with JSON.", ex );
		}catch(IOException ex){
			return new ResponseObj("Failure", "File operation/ IO error occured.", ex );
		}catch(Exception ex){
			return new ResponseObj("Failure", ex.getMessage(), ex );
		}
	
	}
	
	private Map<String, Instrument> initPositions(){
		try{
			FileService fileService =new FileService();
			Map<String, Instrument> positions = new HashMap<>();
			List<InputPosition> inputPositions = fileService.readPositionFile();
			if(inputPositions.isEmpty()){
				return Collections.emptyMap();
			}
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
		}catch(NumberFormatException  e){
			return Collections.emptyMap();
		}
	}
	
	private boolean outputPositions(Map<String,Instrument> positions){
		FileService fileService = new FileService();
		List<OutputPosition> outList = countDelta(positions);
		return fileService.writePositionFile(outList);
	}
	
	private List<OutputPosition> countDelta(Map<String, Instrument> positions){
		List<OutputPosition> output = new ArrayList<>(positions.size()*2);
		OutputPosition internalOutput = null;
		OutputPosition externalOutput = null;
		Instrument instrument = null;
		Account account = null;
		
		for(Entry<String,Instrument> entry : positions.entrySet()){
			instrument = entry.getValue();
			internalOutput = new OutputPosition();
			internalOutput.setInstrument(instrument.getStockSymbol());
			account = instrument.getAccounts().get(AppConstant.AccountType.INTERNAL.getType());
			internalOutput.setAccount(account.getAccntNo());
			internalOutput.setAccountType(account.getAccntType());
			internalOutput.setQuantity(account.getQuantity());
			internalOutput.setDelta(account.getQuantity() - account.getDelta());
			output.add(internalOutput);
			
			externalOutput = new OutputPosition();
			externalOutput.setInstrument(instrument.getStockSymbol());
			account = instrument.getAccounts().get(AppConstant.AccountType.EXTERNAL.getType());
			externalOutput.setAccount(account.getAccntNo());
			externalOutput.setAccountType(account.getAccntType());
			externalOutput.setQuantity(account.getQuantity());
			externalOutput.setDelta(account.getQuantity() - account.getDelta());
			output.add(externalOutput);
		}
		return output;
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
