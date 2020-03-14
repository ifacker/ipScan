package sample.function;

import sample.dataType.ConfigType;

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
    public ConfigType readConfigFile(){
        String filePath = "config.ini";
        File file = new File(filePath);
        ConfigType configType = new ConfigType();
        if (!file.exists()){
            return null;
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
//            System.out.println(str);

            String nmapPath = str.split(";")[0];
            configType.setConfigTitle(nmapPath.split(":")[0]);
            configType.setConfigBody(nmapPath.split(":")[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return configType;
    }


    //重写打开文件
//    public BufferedReader readFile(String filePath){
//        BufferedReader bufferedReader = null;
//        try {
//            FileInputStream fileInputStream = new FileInputStream(filePath);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//            bufferedReader = new BufferedReader(inputStreamReader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bufferedReader;
//    }
}
