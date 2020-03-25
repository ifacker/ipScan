package sample.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import sample.bean.HostStatus;
import sample.function.IpScan;
import sample.function.tab.tabTwo.TabTwoSshThreadPool;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test{

    private static java.lang.Object Object;
    String filePath = "//Users//jiangmengwei//IdeaProjects//ipScan//src//sample//test//ip.txt";

    //打开文件测试：
    private void openFile(){
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

    //cmd命令测试
    private void command(String cmdStr){
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

    //反射案例：
    private void fanShe() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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


    //smb链接测试：
    private void smbLink() {
        String url = "smb://192.168.31.1/";
        SmbFile smbFile = null;
        try {
            smbFile = new SmbFile(url);
            for(SmbFile str: smbFile.listFiles()) {
                System.out.println(str);
            }
        } catch (MalformedURLException | SmbException e) {
            e.printStackTrace();
        }
    }

    //线程池测试
    private void threadPoolText(){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        for (int i = 0; i < 50; i++) {
            TestThreadPool testThreadPool = new TestThreadPool(i);
            threadPoolExecutor.execute(testThreadPool);

        }

    }

    //测试ssh连接
    private void connectSshText(){
        String ip = "localhost";
        String uName = "ifacker";
        String passwd = "13445";

        TabTwoSshThreadPool tabTwoSshThreadPool = new TabTwoSshThreadPool(ip, uName, passwd);
        tabTwoSshThreadPool.run();

    }


    //主函数
    public static void main(String args[])  {
        Test test = new Test();
//        test.smbLink();
        test.connectSshText();


    }
}
