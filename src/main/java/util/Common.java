package main.java.util;

import main.java.run.runtest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:liujunbing
 * time:2017/9/28
 */

public class Common{
        public static Map<String,List<String>> rundata=new HashMap<>();

    /**
     * 解析表格
     */
    public static void praseexcel(){//最后要到配置文件读取
            InputStream in=null;
            try {
                in=new FileInputStream("E:\\workspace\\autointerface\\src\\main\\resources\\执行用例.xlsx");

                Workbook book= WorkbookFactory.create(in);

                int sheetnum=book.getNumberOfSheets();

                for (int i = 0; i <sheetnum ; i++) {
                    Sheet sheet=book.getSheetAt(i);

                    if(Common.getCellValue(sheet.getRow(0).getCell(1)).toUpperCase().equals("Y")){
                        List<String> interfaces = new ArrayList<String>();

                        for(int j=2;j<sheet.getPhysicalNumberOfRows();j++){

                            if(Common.getCellValue(sheet.getRow(j).getCell(1)).toUpperCase().equals("Y")){
                                interfaces.add(Common.getCellValue(sheet.getRow(j).getCell(0)));
                            }
                        }
                        Common.rundata.put(book.getSheetName(i),interfaces);
                    }
                }
            } catch (IOException e) {
                Assert.fail("未找到执行用例.xlsx");
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }finally {
                if (in!=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    /**
     *
     * @param cell 单元格
     * @return String 将所有格式都转化为字符串
     */
    public static String getCellValue(Cell cell){
        String cellValue="";
//        Cell cell =sheet.getRow(x).getCell(y);
        DataFormatter formatter = new DataFormatter();
        if(cell!=null){
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)){
                        cellValue=formatter.formatCellValue(cell);
                    }else{
                        double value=cell.getNumericCellValue();
                        int intValue=(int)cell.getNumericCellValue();
                        cellValue=value-intValue==0?String.valueOf(intValue):String.valueOf(value);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellValue=cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue;
    }
}
