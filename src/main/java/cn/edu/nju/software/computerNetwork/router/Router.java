package cn.edu.nju.software.computerNetwork.router;

import java.io.IOException;

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

	private TelnetService telnetService;

	public String connect(String ip, int port, String telnetPassword, String enablePassword) {
		if (telnetService != null) {
			return "路由器telnet已经处于连接状态，无需重新连接";
		}
		try {
			telnetService = new TelnetService();
			telnetService.connect(ip, port, telnetPassword, enablePassword);
			return "连接成功";
		} catch (Exception e) {
			return "连接失败：原因为" + e.getMessage();
		}
	}
//@formatter:off
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
	//@formatter:on

	public String ping(String ip) throws IOException {
		String command = "ping " + ip;
		String message = telnetService.execute(command);
		if (message.contains("!!!!!"))
			return "Success";
		else if (message.contains("UUUUU"))
			return "Not accessible";
		else if (message.contains("!!!!!"))
			return "Overtime";
		return "";
	}

	public void configInterface(String interfaceName, String ip, String mask) throws IOException {

		String command = "conf t";
		telnetService.execute(command);
		command = "int " + interfaceName;
		telnetService.execute(command);
		command = "ip address " + ip + " " + mask;
		telnetService.execute(command);

		command = "clock rate 56000";
		telnetService.execute(command);

		command = "no shutdown";
		telnetService.execute(command);
		command = "end";
		telnetService.execute(command);

	}

	public void configStaticRouting(String aimIp, String mask, String nextHopIp) throws IOException {
		String command = "conf t";
		telnetService.execute(command);
		command = "ip route-static " + aimIp + " " + mask + " " + nextHopIp;
		telnetService.execute(command);
		command = "end";
		telnetService.execute(command);
	}

	public void configCreateAccessListStandard(String number, String permitOrDeny, String ipOrAny, String mask)
			throws IOException {
		String command = "conf t";
		telnetService.execute(command);
		command = "access-list " + number + " " + permitOrDeny + " " + ipOrAny + " " + mask;
		telnetService.execute(command);
		command = "end";
		telnetService.execute(command);
	}

	public void configCreateAccessListExtend(String number, String permitOrDeny, String protocolOrPort, String sourceIp,
			String sourceMask, String aimIp, String aimMask, String relation, String protocol) throws IOException {

		String command = "conf t";
		telnetService.execute(command);
		StringBuilder sb = new StringBuilder();
		sb.append("access-list ");
		sb.append(" ");
		sb.append(number);
		sb.append(" ");
		sb.append(permitOrDeny);
		sb.append(" ");
		sb.append(protocolOrPort);
		sb.append(" ");
		sb.append(sourceIp);
		if (!"".equals(sourceMask)) {
			sb.append(" ");
			sb.append(sourceMask);
		}
		sb.append(" ");
		sb.append(aimIp);
		if (!"".equals(aimMask)) {
			sb.append(" ");
			sb.append(aimMask);
		}
		if (!"".equals(relation)) {
			sb.append(" ");
			sb.append(relation);
		}
		if (!"".equals(protocol)) {
			sb.append(" ");
			sb.append(protocol);
		}

		telnetService.execute(sb.toString());
	}

	public void configApplyAccessList(String interfaceName, String protocol, String number, String inOrout)
			throws IOException {

		String command = "conf t";
		telnetService.execute(command);
		command = "int " + interfaceName;
		telnetService.execute(command);
		command = protocol + " access-group " + " " + number + " " + inOrout;
		telnetService.execute(command);
		telnetService.execute("end");
	}

	public String showIpAccessList() throws IOException {
		String command = "show ip access-list";
		String list = telnetService.execute(command);
		return list;
	}

	public String showIpInterface() throws IOException {
		String command = "show ip int";
		String list = telnetService.execute(command);
		return list;
	}

}
