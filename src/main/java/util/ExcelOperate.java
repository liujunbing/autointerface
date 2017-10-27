package main.java.util;

import org.testng.log4testng.Logger;
import org.apache.poi.ss.usermodel.*;
import org.testng.Assert;

import java.io.*;


public class ExcelOperate {
    private String filename;
    protected Workbook book;
    protected Sheet sheet;
    private InputStream inputStream = null;
    public static Logger logger = Logger.getLogger(ExcelOperate.class);

    public ExcelOperate(String filename,String sheetname){
        try{
            this.filename=filename;
            inputStream=new FileInputStream(filename);
            book=WorkbookFactory.create(inputStream);
            sheet=book.getSheet(sheetname);

        }catch (FileNotFoundException e){
            logger.error("没有找到指定的文件：" + "[" + filename + "]");
            Assert.fail("没有找到指定的文件：" + "[" + filename + "]");
        }catch (Exception e){
            logger.error("无法读取指定的文件：" + "[" + filename + "]");
            Assert.fail("无法读取指定的文件：" + "[" + filename + "]");
            }
    }



    public String getCellValue(String scenario, String name){

        String cellValue=getCellValue(getrow(scenario),getcolumn(name));
        return cellValue;

    }

    public void setCellValue(String scenario, String name,String data ){
        sheet.getRow(getrow(scenario)).createCell(getcolumn(name)).setCellValue(data);
    }

    protected int getcolumn(String name){
        int rnum =sheet.getRow(0).getLastCellNum();
        int col=-1;
        for (int i=0;i<rnum;i++){
            if(getCellValue(0,i).equals(name)){
                col=i;
            }

        }
        if (col==-1){
            Assert.fail("没有找到相应的字段"+"["+name+"]");
            logger.error("没有找到相应的字段"+"["+name+"]");
        }
        return col;
    }

    protected int getrow(String scenrio){
        int cnum=sheet.getPhysicalNumberOfRows();
        int rown=-1;
        for (int i = 1; i <cnum ; i++) {
            if (getCellValue(i,getcolumn("场景名")).equals(scenrio)){
                rown=i;
            }
        }
        if (rown==-1){
            Assert.fail("没有找到相应的字段"+"["+scenrio+"]");
            logger.error("没有找到相应的字段"+"["+scenrio+"]");
        }
        return rown;
    }

    protected String getCellValue(int x,int y){
        Cell cell =sheet.getRow(x).getCell(y);
        return Common.getCellValue(cell);
    }

    public void saveAndClose(){
        try {
            book.write(new FileOutputStream(filename));
            this.inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
