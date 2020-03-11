package sample.function;

import java.io.*;

public class SaveFile {
    public void saveConfigPath(String nmapConfigPath){
        String nmapConfig = "nmapPath:" + nmapConfigPath + ";\r\n";
        String filePath = "config.ini";

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.write(nmapConfig);
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

//        PrintWriter printWriter = null;
//        try {
//            printWriter = new PrintWriter(filePath);
//            printWriter.println(nmapConfig);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        printWriter.close();
    }
}
