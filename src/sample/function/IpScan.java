package sample.function;

import sample.dataType.HostStatus;

import java.io.BufferedReader;
import java.io.IOException;

//这里可以添加多个扫描类型
public class IpScan {

    private String path;

    public IpScan() {

    }

    public IpScan(String path) {
        this.path = path;
    }

    public void setIpScan(String path) {
        this.path = path;
    }

    public String getIpScan() {
        return path;
    }

//    public void ipScanner(String ip){
    //废弃nmap4j
//        Nmap4j nmap4j = new Nmap4j(path);
//        try {
//            nmap4j.excludeHosts(ip);
//            nmap4j.addFlags("-sV");
//            nmap4j.execute();
//        } catch (NMapInitializationException e) {
//            e.printStackTrace();
//        } catch (NMapExecutionException e) {
//            e.printStackTrace();
//        }
//        NMapRun nMapRun = nmap4j.getResult();
//        System.out.println(nMapRun.getStart());
//    }


    public HostStatus hostLife(String body) {
        FileFun fileFun = new FileFun();
        String tmpFilePath = ".iptmp.txt";
        String tmp2FilePath = ".iptmp2.txt";
        String strCmd = path + "/nmap -sn -v -iL " + tmpFilePath + " -oG " + tmp2FilePath;

        //生成缓存文件
        fileFun.saveFile(tmpFilePath, body);

        //执行命令
        Command command = new Command();
        BufferedReader bufferedReaderInput = command.inputCmd(strCmd);
        System.out.println(bufferedReaderInput);


        //读取缓存文件
        BufferedReader bufferedReaderReadFile = fileFun.readFile(tmp2FilePath);


        //解析反馈的结果
        HostStatus hostStatus = new HostStatus();

        String str = "";
        try {
            if (bufferedReaderReadFile != null) {
                while ((str = bufferedReaderReadFile.readLine()) != null) {
//                if (str.startsWith("Host:") && str.startsWith("Status:", str.indexOf("\t"))) {
                    if (str.startsWith("Host:")) {
//                    System.out.println(str);
//                    hostStatus = hostUp(str, tmpFilePath);

                        String ip = str.split(" \\(\\)")[0].split(": ")[1];

                        if (str.indexOf("Status: Up") != -1) {
                            hostStatus.setStatus(true);
                            hostStatus.setHost(ip);
                        } else if (str.indexOf("Status: Down") != -1) {
                            hostStatus.setStatus(false);
                            hostStatus.setHost(ip);
                        }
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //删除缓存文件
        fileFun.deleteFile(tmpFilePath);
        fileFun.deleteFile(tmp2FilePath);

        return hostStatus;
    }

    //判断IP是否存活
    private HostStatus hostUp(String ipStatus, String tmpFilePath) {
        FileFun fileFun = new FileFun();
        HostStatus hostStatus = new HostStatus();


        BufferedReader bufferedReader = fileFun.readFile(tmpFilePath);
        String str = "";
        try {
            while ((str = bufferedReader.readLine()) != null) {
                int result = ipStatus.indexOf(str);
                if (result != -1) {
                    hostStatus.setStatus(true);
                    hostStatus.setHost(str);
                } else {
                    hostStatus.setStatus(false);
                    hostStatus.setHost(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostStatus;
    }


}
