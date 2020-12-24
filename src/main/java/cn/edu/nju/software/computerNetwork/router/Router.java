package cn.edu.nju.software.computerNetwork.router;

import cn.edu.nju.software.computerNetwork.telnet.TelnetService;

/**  
 * @ClassName: Router  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月24日  
 *  
 */
public class Router {

    private String name;

    private TelnetService telnetConnection;

    public String connect(String ip, int port, String telnetPassword, String enablePassword) {
        if (telnetConnection != null) {
            return "路由器telnet已经处于连接状态，无需重新连接";
        }
        try {
            telnetConnection = new TelnetService();
            telnetConnection.connect(ip, port, telnetPassword, enablePassword);
            return "连接成功";
        } catch (Exception e) {
            return "连接失败：原因为" + e.getMessage();
        }
    }

    /*
     * 
     * 
    //成功
    RTC#ping 192.168.10.5
    
    Type escape sequence to abort.
    Sending 5, 100-byte ICMP Echos to 192.168.10.5, timeout is 2 seconds:
    !!!!!
    Success rate is 100 percent (5/5), round-trip min/avg/max = 6/7/10 ms
    
    //不可达
    RTC#ping 192.168.10.4
    
    Type escape sequence to abort.
    Sending 5, 100-byte ICMP Echos to 192.168.10.4, timeout is 2 seconds:
    UUUUU
    Success rate is 0 percent (0/5)
    
    //超时
    RTC#ping 192.168.9.6
    
    Type escape sequence to abort.
    Sending 5, 100-byte ICMP Echos to 192.168.9.6, timeout is 2 seconds:
    .....
    Success rate is 0 percent (0/5)
     * 
     */

    public boolean ping(String ip) {
        // TODO
        return false;
    }

    public void configInterface(String interfaceName, String subNetWork, String mask) {
        // TODO
    }

    public void configStaticRouting(String netWork, String mask, String nextHopIp) {
        // TODO
    }

    public void configAccessListStandard(String interfaceName) {

    }

}
