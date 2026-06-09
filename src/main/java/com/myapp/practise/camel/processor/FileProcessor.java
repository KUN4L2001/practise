package com.myapp.practise.camel.processor;

import java.io.File;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileProcessor implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);
		log.info("Path: {}", file.getAbsolutePath());
		log.info("Size: {}", file.length());
		try(Workbook workbook = WorkbookFactory.create(file)){
			Sheet sheet = workbook.getSheetAt(0);
			for(Row row : sheet){
				if(isRowEmpty(row)){
					continue;
				}
				for(Cell cell : row){
					if (cell.getCellType() == CellType.BLANK ||
							cell.toString().trim().isEmpty()) {
						continue;
					}
					log.info("Row={}, Cell={}, Value={}",row.getRowNum(), cell.getColumnIndex(), cell.toString());
				}
			}
		}
	}

	private boolean isRowEmpty(Row row){
		if(row == null){
			return true;
		}

		for(Cell cell : row){
			if(cell.getCellType() != CellType.BLANK && !cell.toString().trim().isEmpty()){
				return false;
			}
		}
		return true;
	}
}
