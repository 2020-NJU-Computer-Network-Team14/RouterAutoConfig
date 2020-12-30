package cn.edu.nju.software.computerNetwork.router;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

	private String name = "Router";

	private TelnetService telnetService;

	private List<String> promptInEnable() {
		return Arrays.asList(name + "#");
	}

	private List<String> promptInConfigureTerminal() {
		return Arrays.asList(name + "(config)#");
	}

	private List<String> promptInConfigureInterface() {
		return Arrays.asList(name + "(config-if)#");
	}

	private List<String> promptInAcl() {
		return Arrays.asList(name + "(config-std-nacl)#", name + "(config-ext-nacl)#");
	}

	public void initName() throws IOException {
		String command = "conf t";
		telnetService.executeWithoutRemove(command, Arrays.asList("#"));

		command = "hostname " + name;
		telnetService.executeWithoutRemove(command, Arrays.asList("#"));

		end();
	}

	private void end() throws IOException {
		String command;
		command = "end";
		telnetService.executeWithoutRemove(command, promptInEnable());
	}

	public void setRouterName(String oldName, String n) throws IOException {

		inConfigureTerminal();

		String command = "hostname " + name;
		telnetService.executeWithoutRemove(command, promptInConfigureTerminal());

		end();
	}

	public String connect(String ip, int port, String telnetPassword, String enablePassword) {
		if (telnetService != null) {
			return "路由器telnet已经处于连接状态，无需重新连接";
		}
		try {
			telnetService = new TelnetService();
			telnetService.connect(ip, port, telnetPassword, enablePassword);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
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
		String message = telnetService.execute(command, promptInEnable());
		if (message.contains("!!!!!"))
			return "Success";
		else if (message.contains("....."))
			return "Overtime";
		else if (message.replaceAll("[\r\n]", "").matches(".*[\\.U]{5}.*"))
			return "Not accessible";
		return "";
	}

	public void configInterface(String interfaceName, String ip, String mask) throws IOException {

		inConfigureTerminal();

		String command = "int " + interfaceName;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		command = "ip address " + ip + " " + mask;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		command = "clock rate 56000";
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		command = "no shutdown";
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		end();
	}

	public void configStaticRouting(String aimIp, String mask, String nextHopIp) throws IOException {
		String command;

		inConfigureTerminal();

		command = "ip route " + aimIp + " " + mask + " " + nextHopIp;
		telnetService.executeWithoutRemove(command, promptInConfigureTerminal());

		end();
	}

	private void inConfigureTerminal() throws IOException {
		String command = "conf t";
		telnetService.executeWithoutRemove(command, promptInConfigureTerminal());
	}

	// public void configCreateAccessListStandard(String number, String
	// permitOrDeny, String ipOrAny, String mask)
	// throws IOException {
	//
	// inConfigureTerminal();
	//
	// String command = "access-list " + number + " " + permitOrDeny + " " +
	// ipOrAny + " " + mask;
	// telnetService.executeWithoutRemove(command, promptInConfigureTerminal());
	//
	// end();
	// }

	public void configCreateAccessListStandard(String numberOrName, String permitOrDeny, String ipOrAny, String mask)
			throws IOException {

		inConfigureTerminal();

		String command = "ip access-list standard" + numberOrName;
		telnetService.executeWithoutRemove(command, promptInAcl());

		command = permitOrDeny + " " + ipOrAny + " " + mask;
		telnetService.executeWithoutRemove(command, promptInAcl());

		end();
	}

	public void configCreateAccessListExtend(String numberOrName, String permitOrDeny, String protocol, String sourceIp,
			String sourceMask, String aimIp, String aimMask, String relation, String port) throws IOException {

		inConfigureTerminal();

		StringBuilder sb = new StringBuilder();
		sb.append("ip access-list extend");
		sb.append(" ");
		sb.append(numberOrName);
		telnetService.executeWithoutRemove(sb.toString(), promptInAcl());

		sb = new StringBuilder();
		sb.append(permitOrDeny);
		sb.append(" ");
		sb.append(protocol);
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
		if (!"".equals(port)) {
			sb.append(" ");
			sb.append(port);
		}

		telnetService.executeWithoutRemove(sb.toString(), promptInAcl());

		end();
	}

	public void configApplyAccessList(String interfaceName, String number, String inOrout) throws IOException {

		String command;

		inConfigureTerminal();

		command = "int " + interfaceName;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		command = "ip access-group " + " " + number + " " + inOrout;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		end();
	}

	/**
	 * 取消某一整条
	 */
	public void configCancelAccessListGlobal(String stdOrext, String number, String inOrout) throws IOException {

		String command;

		inConfigureTerminal();

		command = "no ip access-list " + stdOrext + " " + number + " " + inOrout;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		end();
	}

	/**
	 * 取消某一小条
	 */
	public void configCancelAccessListGlobal(String numberOrName, String stdOrext, String permitOrDeny, String ipOrAny,
			String mask) throws IOException {

		inConfigureTerminal();

		String command = "ip access-list " + stdOrext + " " + numberOrName;
		telnetService.executeWithoutRemove(command, promptInAcl());

		command = "no " + permitOrDeny + " " + ipOrAny + " " + mask;
		telnetService.executeWithoutRemove(command, promptInAcl());

		end();
	}

	public void configCancelAccessList(String interfaceName, String number, String inOrout) throws IOException {

		String command;

		inConfigureTerminal();

		command = "int " + interfaceName;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		command = "no ip access-group " + " " + number + " " + inOrout;
		telnetService.executeWithoutRemove(command, promptInConfigureInterface());

		end();
	}

	public List<List<List<String>>> showIpAccessList() throws IOException {
		String command = "show ip access-list";
		String msg = telnetService.execute(command, promptInEnable());
		String[] lines = msg.split("\n");
		List<List<List<String>>> ret = new LinkedList<>();
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("access list")) {
				ret.add(new LinkedList<List<String>>());
				LinkedList<String> temp = new LinkedList<>();
				if (lines[i].contains("Standard"))
					temp.add("Standard");
				else
					temp.add("Extended");
				int pos = lines[i].indexOf("access list");
				temp.add(lines[i].substring(pos + "access list ".length(), lines[i].length()));
				ret.get(ret.size() - 1).add(temp);
			} else {
				String[] words = lines[i].split(" ");
				LinkedList<String> temp = new LinkedList<>();
				for (String s : words) {
					if (s.length() > 0)
						temp.add(s);
				}
				ret.get(ret.size() - 1).add(temp);
			}
		}
		return ret;
	}

	public String showIpInterface() throws IOException {
		String command = "show ip int";
		String list = telnetService.execute(command, promptInEnable());
		return list;
	}

}
