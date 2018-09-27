package code.java.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import code.java.constant.AppConstant;
import code.java.model.InputPosition;
import code.java.model.OutputPosition;

public class FileService {

	public List<InputPosition> readPositionFile(){
		try{
			// load file from resource
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(AppConstant.FileNames.INPUT_POSITION.getName()).getFile());
	
			// configure the schema we want to read
			CsvSchema schema = CsvSchema.builder().addColumn("instrument").addColumn("account").addColumn("accountType")
					.addColumn("quantity").build();
			CsvMapper mapper = new CsvMapper();
	
			// configure the reader on what bean to read and how we want to write
			// that bean
			ObjectReader oReader = mapper.readerFor(InputPosition.class).with(schema);
	
			// read from file
			Reader reader = new FileReader(file);
			MappingIterator<InputPosition> mi = oReader.readValues(reader);
			if (mi.hasNext()) {
				mi.next();
			}
			List<InputPosition> inputPositions = new ArrayList<>();
			while (mi.hasNext()) {
				inputPositions.add(mi.next());
			}
			return inputPositions;
		}catch(IOException e){
			return Collections.emptyList();
		}
	}

	public boolean writePositionFile(List<OutputPosition> list){
		try {
		// initialize and configure the mapper
		CsvMapper mapper = new CsvMapper();

		// initialize the schema
		CsvSchema schema = CsvSchema.builder().addColumn("instrument").addColumn("account").addColumn("accountType")
				.addColumn("quantity").addColumn("delta").build();

		// map the bean with our schema for the writer
		ClassLoader classLoader = getClass().getClassLoader();
		ObjectWriter writer = mapper.writerFor(OutputPosition.class).with(schema);
		File file = new File(classLoader.getResource(AppConstant.FileNames.OUTPUT_POSITION.getName()).getFile());
		// we write the list of objects
			writer.writeValues(file).writeAll(list);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
