package sample.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

    public Command(){

    }

    public Command(String cmd){
        inputCmd(cmd);
    }

    public BufferedReader inputCmd(String cmdStr) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdStr);
//            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//        System.out.println(str);
        return bufferedReader;
    }

}
