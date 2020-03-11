package sample.function;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenFile {
    //读取文件 IP 地址
    public List readFile(String filePath){
        List<String> ipList = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String ip;
            while ((ip = bufferedReader.readLine()) != null){
//                System.out.println(ip);
                ipList.add(ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipList;
    }

    //读取 config.ini 配置文件
    public void readConfigFile(){
        String filePath = "config.ini";
        File file = new File(filePath);
        if (!file.exists()){
            return;
        }
//        BufferedInputStream bufferedInputStream = null;
//        InputStreamReader inputStreamReader = null;
//        FileInputStream fileInputStream = null;
        try {
//            fileInputStream = new FileInputStream(filePath);
//            inputStreamReader = new InputStreamReader(fileInputStream);
//            bufferedInputStream = new BufferedInputStream(inputStreamReader);
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            String str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
