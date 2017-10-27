package test.java;
import main.java.util.ExcelOperate;
import org.testng.annotations.Test;


public class test_ExcelOperate {
    @Test
    public void testgetcellvalue(){
        ExcelOperate e=new ExcelOperate("E:\\workspace\\autointerface\\src\\main\\resources\\project.xlsx","interface03");
        System.out.println(e.getCellValue("哈哈哈","参数1"));
        e.setCellValue("冰厚","参数1","i love you");
        e.setCellValue("冰厚","参数2","do you love me");
        System.out.println(e.getCellValue("冰厚","参数2"));
        System.out.println(e.getCellValue("冰厚","参数1"));
        e.saveAndClose();
    }
}
