package sample.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sample.bean.HostStatus;
import sample.function.IpScan;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    private static java.lang.Object Object;
    String filePath = "//Users//jiangmengwei//IdeaProjects//ipScan//src//sample//test//ip.txt";

    public void openFile(){
        BufferedReader bfReader = null;
        try {
            bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            System.out.println(bfReader);
            String ip;
            while ((ip = bfReader.readLine()) != null) {

                //这样写会出现读取数据丢失的情况
//                ip = bfReader.readLine();

                System.out.println(ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void command(String cmdStr){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdStr);
//            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader( process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }

    public static void main(String args[]) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        Class<HostStatus> cla = HostStatus.class;
        HostStatus hostStatus = new HostStatus();
        Class<?> cla = Class.forName("sample.bean.HostStatus");

//        HostStatus hostStatus = cla.newInstance();
//        Method methodStatus = cla.getMethod("getStatus", Boolean.class);
        Method methodHost = cla.getMethod("setHost", String.class);
        methodHost.invoke( hostStatus, "10.0.0.1");
//        Object objectStatus = methodStatus.invoke(cla.newInstance(), true);
//        Object objectHost = methodHost.invoke(cla.newInstance(), "10.0.0.1");
        System.out.println(hostStatus.getHost());
    }
}
