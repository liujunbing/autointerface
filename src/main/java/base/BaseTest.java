package main.java.base;

import main.java.util.Common;
import main.java.util.dataToTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class BaseTest {
    String clazz = Thread.currentThread() .getStackTrace()[2].getClassName();
    @DataProvider(name= "data" )
    public Iterator<Object[]> giveScenario(Method method){
        String[] a=clazz.split("\\.");
        String path="E:\\workspace\\autointerface\\src\\main\\resources\\"+a[2]+".xlsx";
        if (Common.rundata.size()==0){
            return new dataToTest(path,method.getName());
        }
        List runinterface=Common.rundata.get(a[2]);

        if (runinterface.contains(method.getName())){
            return new dataToTest(path,method.getName());
        }
             return new dataToTest();
    }
}
