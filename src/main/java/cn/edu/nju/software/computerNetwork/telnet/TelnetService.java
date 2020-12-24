package cn.edu.nju.software.computerNetwork.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

/**  
 * @ClassName: TelnetService  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月23日  
 *  
 */
public class TelnetService implements AutoCloseable {

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    public void connect(String ip, int port, String telnetPassword, String enablePassword) throws IOException {
        telnet = new TelnetClient();
        telnet.connect(ip, port);
        in = telnet.getInputStream();
        out = new PrintStream(telnet.getOutputStream());

        login(telnetPassword, enablePassword);
    }

    /*
     * 
    User Access Verification
    
    Password: 
     * 
     * 
    Router>enable
    enable
    % No password set
    Router>
    
    User Access Verification
    
    Password: 123
    
    Router>enable
    enable
    Password: 1
    
    Password: CISCO
    
    Router#
    
     */

    private void login(String telnetPassword, String enablePassword) throws IOException {
        String str = getOutput();
        if (str.endsWith(":")) {
            if (telnetPassword == null) {
                throw new IllegalArgumentException("Telnet密码为空！");
            }
            inputPassword(telnetPassword, "Telnet");
        }
        String enableOutput = execute("enable");
        if (enableOutput.contains("% No password set")) {
            throw new IllegalArgumentException("路由器未设置enable密码，请先设置路由器enable密码，再进行本操作！");
        }
        if (telnetPassword == null) {
            throw new IllegalArgumentException("Enable密码为空！");
        }
        inputPassword(enablePassword, "Enable");
    }

    private void inputPassword(String telnetPassword, String name) throws IOException {
        String passwordReturn = executeWithoutRemove(telnetPassword);
        if (passwordReturn.endsWith(":")) {
            throw new IllegalArgumentException(name + "密码不对！");
        }
    }

    public String executeWithoutRemove(String command) throws IOException {
        submitCommand(command);
        return getOutput().toString();
    }

    private String getOutput() throws IOException {
        StringBuilder buf = new StringBuilder();
        while (true) {
            char ch = (char)in.read();
            buf.append(ch);
            if (bufIsEndWithPrompt(buf)) {
                break;
            }
        }
        return buf.toString();
    }

    public String execute(String command) throws IOException {
        return removeInputAndPrompt(executeWithoutRemove(command));
    }

    public String executeAndGetPrompt(String command) throws IOException {
        return removeInput(executeWithoutRemove(command));
    }

    private String removeInputAndPrompt(String buf) {
        int low = firstLine(buf), high = lastLine(buf);
        return buf.substring(low + 1, high);
    }

    private String removeInput(String buf) {
        int high = lastLine(buf);
        return buf.substring(0, high);
    }

    private int lastLine(String buf) {
        int high = 0;
        for (int i = buf.length() - 1; i >= 0; --i) {
            if (buf.charAt(i) == '\n') {
                high = i;
            }
        }
        return high;
    }

    private int firstLine(String buf) {
        int low = 0;
        for (int i = 0; i < buf.length(); ++i) {
            if (buf.charAt(i) == '\n') {
                low = i;
            }
        }
        return low;
    }

    private boolean bufIsEndWithPrompt(StringBuilder buf) {
        char lastChar = buf.charAt(buf.length() - 1);
        return lastChar == ':' || lastChar == '>' || lastChar == '#';
    }

    public void submitCommand(String command) {
        out.println(command);
        out.flush();
    }

    @Override
    public void close() throws Exception {
        telnet.disconnect();
    }

    public static void main(String[] args) throws Exception {
        try (TelnetService telnet = new TelnetService()) {
            telnet.connect("192.168.10.3", 23, "123", "CISCO");
        }
    }
}