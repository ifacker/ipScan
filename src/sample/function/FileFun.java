package sample.function;

import sample.dataType.ConfigType;

import java.io.*;

public class FileFun {

//    private ConfigType configType;
//
//    public SaveFile(){
//
//    }
//
//    public SaveFile(ConfigType configType){
//        this.configType = configType;
//    }

//    public void saveConfigPath(String nmapConfigPath){
//
//
//        FileWriter fileWriter = null;
//
//        try {
//            fileWriter = new FileWriter(filePath);
//            fileWriter.write(nmapConfig);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fileWriter.flush();
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //新保存文件方法
//        BufferedWriter bufferedWriter = null;
//        try {
//            bufferedWriter = new BufferedWriter(new FileWriter(filePath));
//            bufferedWriter.write(nmapConfig);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            bufferedWriter.flush();
//            bufferedWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    }

    //保存文件
    public Integer saveFile(String filePath, String body){
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //打开文件
    public BufferedReader readFile(String filePath){
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    //删除文件
    public void deleteFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }

}
