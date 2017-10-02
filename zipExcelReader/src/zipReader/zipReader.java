package zipReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class zipReader {
	
	public static void zipReaderFunk(String zipFileNamery){
		try{
        	ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileNamery));
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){
            	  name = entry.getName(); // получим название файла
                  size=entry.getSize();  // получим его размер в байтах
               
                  if(size>0){
                	  if(name.endsWith(".xls")){
                		  System.out.print("It contains!!!");
                  			ZipFile zipFile = new ZipFile(zipFileNamery);
                  			InputStream inputStream =zipFile.getInputStream(entry);
                  		
                  			HSSFWorkbook myExcelBook = new HSSFWorkbook(inputStream);
                  			HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
                  			HSSFRow row = myExcelSheet.getRow(0);
                        
                  			if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING){
                  	            String str = row.getCell(0).getStringCellValue();
                  	            System.out.println("str : " + str);
                  	        }
                  	        
                  	        if(row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                  	            Date birthdate = row.getCell(1).getDateCellValue();
                  	            System.out.println("num :" + birthdate);
                  	        }
                  			
                  		}
                  }            	
            }
            
		}catch(Exception e){
			System.out.print("Error : "+e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		zipReaderFunk("/home/sergey/sergey.zip");
	}

}
