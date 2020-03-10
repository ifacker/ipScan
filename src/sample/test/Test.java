package sample.test;

import java.io.*;

public class Test {

    String filePath = "//Users//jiangmengwei//IdeaProjects//ipScan//src//sample//test//ip.txt";

    public void openFile(){
        BufferedReader bfReader = null;
        try {
            bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
//            System.out.println(bfReader);
            while (bfReader.readLine() != null) {
                String ip = bfReader.readLine();
                System.out.println(ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
