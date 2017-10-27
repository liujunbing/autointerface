//package main.java.plugins;
//
//import org.apache.log4j.PropertyConfigurator;
//
//import java.util.Properties;
//
///**配置了log的输出等级，以及如何显示 */
//public class LogConfiguration {
//    public static void initLog(String fileName){
//        //获取到模块名字
//        String founctionName = getFunctionName(fileName);
//        //声明日志文件存储路径以及文件名、格式
//        final String logFilePath = "./result/log/"+founctionName+"/"+fileName+".log";
//        Properties prop = new Properties();
//        //配置日志输出的格式
//        prop.setProperty("log4j.rootLogger","info,toConsole,toFile");
//        prop.setProperty("log4j.appender.file.encoding","UTF-8" );
//        prop.setProperty("log4j.appender.toConsole","org.apache.log4j.ConsoleAppender");
//        prop.setProperty("log4j.appender.toConsole.Target","System.out");
//        prop.setProperty("log4j.appender.toConsole.layout","org.apache.log4j.PatternLayout ");
//        prop.setProperty("log4j.appender.toConsole.layout.ConversionPattern","[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
//        prop.setProperty("log4j.appender.toFile", "org.apache.log4j.DailyRollingFileAppender");
//        prop.setProperty("log4j.appender.toFile.file", logFilePath);
//        prop.setProperty("log4j.appender.toFile.append", "false");
//        prop.setProperty("log4j.appender.toFile.Threshold", "info");
//        prop.setProperty("log4j.appender.toFile.layout", "org.apache.log4j.PatternLayout");
//        prop.setProperty("log4j.appender.toFile.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
//        //使配置生效
//        PropertyConfigurator.configure(prop);
//
//    }
//    /**取得模块名字 */
//    public static String getFunctionName(String fileName){
//        String functionName = null;
//        int firstUndelineIndex = fileName.indexOf("_");
//        functionName = fileName.substring(0, firstUndelineIndex-4);
//        return functionName;
//
//    }
//}
