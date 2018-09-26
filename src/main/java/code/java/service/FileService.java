package code.java.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import code.java.model.InputPosition;
import code.java.model.OutputPosition;
import code.java.model.Transaction;

public class FileService {

	public List<InputPosition> readPositionFile() throws Exception {
		String FILE_NAME = "Input_StartOfDay_Positions.csv";
		// load file from resource
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(FILE_NAME).getFile());

		// configure the schema we want to read
		CsvSchema schema = CsvSchema.builder().addColumn("instrument").addColumn("account").addColumn("accountType")
				.addColumn("quantity").build();
		CsvMapper mapper = new CsvMapper();

		// configure the reader on what bean to read and how we want to write
		// that bean
		ObjectReader oReader = mapper.readerFor(InputPosition.class).with(schema);

		// read from file
		try (Reader reader = new FileReader(file)) {
			MappingIterator<InputPosition> mi = oReader.readValues(reader);
			if (mi.hasNext()) {
				mi.next();
			}
			List<InputPosition> inputPositions = new ArrayList<>();
			while (mi.hasNext()) {
				inputPositions.add(mi.next());
			}
			return inputPositions;
		}
	}

	private void writePositionFile(List<OutputPosition> list) throws Exception {
		// initialize and configure the mapper
		CsvMapper mapper = new CsvMapper();

		// initialize the schema
		CsvSchema schema = CsvSchema.builder().addColumn("instrument").addColumn("account").addColumn("accountType")
				.addColumn("quantity").addColumn("delta").build();

		// map the bean with our schema for the writer
		ObjectWriter writer = mapper.writerFor(OutputPosition.class).with(schema);

		File tempFile = new File("Output_EndOfDay_Positions.csv");
		// we write the list of objects
		writer.writeValues(tempFile).writeAll(list);
	}

}
