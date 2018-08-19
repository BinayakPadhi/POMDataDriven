package excelInputAndOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String getCellData(String filePath,String fileName,String sheetName,int rowno,int colno) throws IOException{
		Workbook workbook = null;
		File file = new File(filePath+"\\"+fileName);
		FileInputStream inputstream = new FileInputStream(file);
		String fileExtentionName = fileName.substring(fileName.indexOf("."));
		if(fileExtentionName.equals(".xlsx")){
			workbook = new XSSFWorkbook(inputstream);
		}else if(fileExtentionName.equals(".xls")){
			workbook = new HSSFWorkbook(inputstream);
		}
		
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowno);
		
		return row.getCell(colno).getStringCellValue();
	}

}
