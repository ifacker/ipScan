package sample.test;

import java.io.*;

public class Test {

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
}
