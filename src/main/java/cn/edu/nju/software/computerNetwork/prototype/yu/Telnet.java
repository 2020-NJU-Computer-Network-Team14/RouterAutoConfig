package cn.edu.nju.software.computerNetwork.prototype.yu;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

/**  
 * @ClassName: Telnet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月11日  
 *  
 */
public class Telnet {

    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private char prompt = '$';

    // 普通用户结束
    public Telnet(String ip, int port, String user, String password) {
        try {
            telnet.connect(ip, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            // 根据root用户设置结束符
            this.prompt = user.equals("root") ? '#' : '$';
            login(user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** * 登录 * * @param user * @param password */
    public void login(String user, String password) {
        readUntil("login:");
        write(user);
        readUntil("Password:");
        write(password);
        readUntil(prompt + " ");
    }

    /** * 读取分析结果 * * @param pattern * @return */
    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char)in.read();
            while (true) {
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char)in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** * 写操作 * * @param value */
    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** * 向目标发送命令字符串 * * @param command * @return */
    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** * 关闭连接 */
    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("启动Telnet...");
            String ip = "172.19.241.212";
            int port = 23;
            String user = "test_telnet";
            String password = "123";
            Telnet telnet = new Telnet(ip, port, user, password);
            telnet.sendCommand("export LANG=en");
            String r1 = telnet.sendCommand("cd /home/project/");
            String r2 = telnet.sendCommand("pwd");
            String r3 = telnet.sendCommand("sh a.sh");
            telnet.disconnect();
            System.out.println("显示结果");
            System.out.print(r1);
            System.out.print(r2);
            System.out.print(r3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
