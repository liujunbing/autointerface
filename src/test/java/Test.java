package test.java;

public class Test {
    @org.testng.annotations.Test(dataProvider = "haha")
    public void test(String a){
        System.out.println(a);
    }


}
