
package mcarbon1;


import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.io.InputStream;
import java.util.Iterator;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


 
public class exceltotext {
 
    public static void main( String [] args ) throws IOException {
      Writer writer = null;
        try {
    
            InputStream in = new BufferedInputStream(new FileInputStream("D:\\data\\dump1.xlsx"));
            Workbook hw = new XSSFWorkbook(in);
            XSSFSheet sheet = (XSSFSheet) hw.getSheetAt(0); 
            File file = new File("D:\\data\\dump1.txt");  
            writer = new BufferedWriter(new FileWriter(file));
            Iterator row1 = sheet.iterator(); 
            while( row1.hasNext() ) 
             {   
                XSSFRow row2 = (XSSFRow) row1.next();
                System.out.println("\n");
                writer.write("\n");
                Iterator cell = row2.cellIterator();
               
                   while( cell.hasNext() )  
                    {
                      XSSFCell cell2 = (XSSFCell) cell.next();
                      if(XSSFCell.CELL_TYPE_NUMERIC==cell2.getCellType()) 
                       {
                         System.out.print(cell2.getNumericCellValue());
                         if(cell2.getColumnIndex()==3)
                          {
                            writer.write("\n");
                            writer.write(String.valueOf(cell2.getNumericCellValue()));
                          }
                         else
                          {
                            writer.write("\n");
                            writer.write(String.valueOf(cell2.getNumericCellValue())+",");
                          }
                       }
                     else
                        if(XSSFCell.CELL_TYPE_STRING==cell2.getCellType()) 
                          {
                            System.out.print( cell2.getStringCellValue()+"     " );
                            writer.write(cell2.getStringCellValue()+"      ");
                          }
                        else
                            if(XSSFCell.CELL_TYPE_BOOLEAN==cell2.getCellType()) 
                             {
                                System.out.print( cell2.getBooleanCellValue()+"     " );
                                writer.write(String.valueOf(cell2.getBooleanCellValue()));
                             }
                            else
                                if(XSSFCell.CELL_TYPE_BLANK==cell2.getCellType())
                                    System.out.print( "BLANK" );
                                else
                                    System.out.print("Unknown cell");
                 }
                 
           }
            
       } catch ( IOException ex )   
           {
           } 
         finally 
         {             
                            
            if (writer != null) 
            {                   
                writer.close();                 
            }             
         }
    }
}
