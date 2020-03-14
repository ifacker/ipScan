package sample.function;

import sample.dataType.ConfigType;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Description: 加载配置文件$
 * @Author: ifacker
 * @Date: $
 */
public class LoadConfig {
    public ConfigType loadConfig(String filePath) {
        ConfigType configType = new ConfigType();
        FileFun fileFun = new FileFun();
        BufferedReader bufferedReader = fileFun.readFile(filePath);
        String str = null;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nmapPath = str.split(";")[0];
        configType.setConfigTitle(nmapPath.split(":")[0]);
        configType.setConfigBody(nmapPath.split(":")[1]);
        return configType;
    }
}
