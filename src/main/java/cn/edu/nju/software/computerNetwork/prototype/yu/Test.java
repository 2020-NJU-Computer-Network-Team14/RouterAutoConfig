package cn.edu.nju.software.computerNetwork.prototype.yu;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.apache.commons.net.telnet.TelnetClient;

/**  
 * @ClassName: Test  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月16日  
 *  
 */
public class Test {

    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;

    public void init(String ip, int port) {
        try {
            telnet.connect(ip, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            StringBuilder sb = new StringBuilder();
            char ch = (char)in.read();
            while (true) {
                // if (ch == '>' || ch == '#' || ch == ':') {
                // sb.append(ch);
                // System.out.println(sb.toString());
                // }
                // sb.append(ch);
                // ch = (char)in.read();
                System.out.print((char)in.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String command) {
        try {
            write(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("启动Telnet...");
        String ip = "192.168.10.3";
        int port = 23;
        String password = "CISCO";
        Test telnet = new Test();

        telnet.init(ip, port);

        new Thread(() -> {
            telnet.read();
        }).start();

        // telnet.sendCommand("enable\n");
        // enable

        // telnet.sendCommand(password);

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String str = scan.nextLine();
            telnet.sendCommand(str);
        }
        scan.close();
    }

}
