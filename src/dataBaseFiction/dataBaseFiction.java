package dataBaseFiction;

import java.io.*;

import static openAStage.openNewWarningStage.openNewWarningStage;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/9/6  10:56
 * @package: dataBaseFiction
 * @project: Dictionary
 */
public class dataBaseFiction {

    public static void backup() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c D:\\mysql-5.7.21-winx64\\bin\\mysqldump -hlocalhost -uroot -p1605103328 --set-charset=UTF8 --databases newdictionary>C:\\Users\\16051\\Diction\\source\\recover.sql");
        openNewWarningStage("备份成功！",400);
        System.out.println("备份成功！！！");
    }
    public static void recover() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c D:\\mysql-5.7.21-winx64\\bin\\mysql -hlocalhost -uroot -p1605103328 --default-character-set=utf8 newdictionary<C:\\Users\\16051\\Diction\\source\\recover.sql");
        openNewWarningStage("恢复成功！",400);
        System.out.println("恢复成功！！！");
    }
}
