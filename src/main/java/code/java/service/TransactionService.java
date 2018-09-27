package code.java.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.java.constant.AppConstant;
import code.java.model.Instrument;
import code.java.model.Transaction;

public class TransactionService {
	
	InstrumentService instrumentService;
	
	public List<Transaction> fetchTransactions() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(AppConstant.FileNames.TRANSACTION.getName()).getFile());
		List<Transaction> transactions = mapper.readValue(file, new TypeReference<List<Transaction>>(){});
		return transactions;
	}
	
	public boolean processTransaction(Transaction transaction, Instrument instrument){
		instrumentService = new InstrumentService();
		if(AppConstant.TransactionType.BUY.getType().equals(transaction.getType())){
			return instrumentService.buyInstrument(transaction, instrument);
		}
		
		if(AppConstant.TransactionType.SELL.getType().equals(transaction.getType())){
			return instrumentService.sellInstrument(transaction, instrument);
		}
		return false;
	}

}
