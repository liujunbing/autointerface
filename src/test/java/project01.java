package test.java;

import main.java.base.BaseTest;
import org.testng.annotations.Test;


import main.java.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

    public class project01 extends BaseTest {

        @Test(dataProvider="data")
        public void interface03(String scenario){
            System.out.println(scenario);
        }

        @Test(dataProvider = "data")
        public void interface02(String scenario){
            System.out.println(scenario);
        }
    }


