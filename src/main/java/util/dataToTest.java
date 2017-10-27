package main.java.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.testng.log4testng.Logger;


public class dataToTest implements Iterator<Object[]> {
    public String fileName;
    public String sheetName;
    public Sheet sheet;
    private int currentnum;
    public Workbook book;
    Logger logger =Logger.getLogger(dataToTest.class);

    public dataToTest(String fileName,String sheetName){
            this.currentnum=1;
        try {
            this.book= WorkbookFactory.create(new FileInputStream(fileName));
            this.sheet=book.getSheet(sheetName);
            if(book==null){
                Assert.fail("没有成功解析excl文件");
                logger.error("没有成功解析excl文件");
            }
            if(sheet==null){
                Assert.fail("没有"+"["+sheetName+"]"+"这个表格，请检查");
                logger.error("没有"+"["+sheetName+"]"+"这个表格，请检查");
            }
        }catch (FileNotFoundException E){
            Assert.fail("没有找到指定的文件：" + "[" + fileName + "]");
            logger.error("没有找到指定的文件：" + "[" + fileName + "]");
        }
        catch (IOException e) {
            logger.error("无法读取指定的文件：" + "[" + fileName + "]");
            Assert.fail("无法读取指定的文件：" + "[" + fileName + "]");
        } catch (InvalidFormatException e) {
            logger.error("无法读取指定的文件：" + "[" + fileName + "]");
            Assert.fail("无法读取指定的文件：" + "[" + fileName + "]");
        }

    }
    public dataToTest(){
        book=null;
        sheet=null;
    }

    @Override
    public boolean hasNext() {
        int rnum;
        if (sheet==null){
            rnum=0;
        }else {
            rnum=sheet.getPhysicalNumberOfRows();
        }
        if(rnum==0||rnum==1 )
            return false;
        else if(currentnum>=rnum)
            return false;
        else if(Common.getCellValue(sheet.getRow(currentnum).getCell(0)).equals(""))
            return false;
        return true;
    }

    @Override
    public Object[] next() {
        Object[] scenario=new Object[1];
        Cell cell=sheet.getRow(currentnum).getCell(0);
        scenario[0]= Common.getCellValue(cell);
        this.currentnum++;
        return scenario;
    }

}
