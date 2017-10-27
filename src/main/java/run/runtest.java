package main.java.run;

import main.java.util.Common;

import org.testng.Assert;
import org.testng.TestNG;


public class runtest {
    public static void main(String[] args) {
        Common.praseexcel();
        int projectnum=Common.rundata.keySet().size();
        Class[] testcase=new Class[projectnum];
        int i=0;
        for (String casename:Common.rundata.keySet()) {
            try {
                testcase[i]=Class.forName("test.java."+casename);
                i++;
            } catch (ClassNotFoundException e) {
                Assert.fail("请尽快完成用例再执行");
            }
        }
        TestNG tng=new TestNG();
        tng.setTestClasses(testcase);
        tng.run();
    }
}
