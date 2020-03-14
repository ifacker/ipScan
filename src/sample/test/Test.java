package sample.test;

import sample.function.IpScan;

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

    public static void main(String args[]){
        String Str = new String("Host: 10.54.18.236 ()\tStatus: Up");

        System.out.print("Return Value :" );
        System.out.println(Str.startsWith("Welcome") );

        System.out.print("Return Value :" );
        System.out.println(Str.startsWith("Tutorials") );

        System.out.print("Return Value :" );
        System.out.println(Str.startsWith("Yiibai") );


        Integer a = Str.indexOf("\t");
        System.out.println(a);
    }

}
