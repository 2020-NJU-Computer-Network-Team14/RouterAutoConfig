package cn.edu.nju.software.computerNetwork.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**  
 * @ClassName: RouterServiceMockImpl  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月25日  
 *  
 */
@Service("test-route-service")
public class RouterServiceMockImpl implements RouterService {

    @Override
    public String connect(String routerName, String ip, int port, String telnetPassword, String enablePassword) {
        System.out.println(routerName + " " + ip + " " + port + " " + telnetPassword + " " + enablePassword);
        return "Success";
    }

    @Override
    public String configInterface(String routerName, String interfaceName, String ip, String mask) {
        System.out.println(routerName + " " + interfaceName + " " + ip + " " + mask);
        return "Success";
    }

    @Override
    public String ping(String routerName, String ip) {
        System.out.println(routerName + " " + ip);
        return "Overtime";
    }

    @Override
    public String configStaticRouting(String routerName, String aimIp, String mask, String nextHopIp) {
        System.out.println(routerName + " " + aimIp + " " + mask + " " + nextHopIp);
        return "Success";
    }

    @Override
    public String configCreateAccessListStandard(String routerName, String number, String permitOrDeny, String ipOrAny,
        String mask) {
        System.out.println(routerName + " " + number + " " + permitOrDeny + " " + ipOrAny + " " + mask);
        return "Success";
    }

    @Override
    public String configCreateAccessListExtend(String routerName, String number, String permitOrDeny,
        String protocolOrPort, String sourceIp, String sourceMask, String aimIp, String aimMask, String relation,
        String protocol) {
        System.out.println(routerName + " " + number + " " + permitOrDeny + " " + protocolOrPort + " " + sourceIp + " "
            + sourceMask + " " + aimIp + " " + aimMask + " " + relation + " " + protocol);
        return "Success";
    }

    @Override
    public String configApplyAccessList(String routerName, String interfaceName, String number, String inOrout) {
        System.out.println(routerName + " " + interfaceName + " " + number + " " + inOrout);
        return "Success";
    }

    @Override
    public List<List<String>> showIpAccessList(String routerName) {
        System.out.println(routerName);
        List<List<String>> ss = new ArrayList<>();;
        List<String> s = new ArrayList<>();
        s.add("Standard IP access list 10");
        s.add("deny 1.1.1.1 0.0.0.1");
        s.add("permit any");
        ss.add(s);

        s = new ArrayList<>();
        s.add("Extended IP access list aaa");
        ss.add(s);

        s = new ArrayList<>();
        s.add("Extended IP access list 110");
        s.add("deny icmp host 2.2.2.2 host 3.3.3.3");
        s.add("deny icmp 1.1.1.1 0.0.0.255 any eq telnet");
        ss.add(s);

        s = new ArrayList<>();
        s.add("Standard IP access list test");
        s.add("deny  1.1.1.1  0.0.0.1");
        s.add("permit  any");
        ss.add(s);
        return ss;
    }

    @Override
    public List<String> showIpInterface(String routerName,String intf) {
        System.out.println(routerName+" "+intf);
        List<String> s = new ArrayList<>();
        s.add("test");
        s.add("set");
        return s;
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String stdOrext, String number) {
        System.out.println(routerName + " " + stdOrext + " " + number);
        return "Success";
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String id, String std, String term) {
        System.out.println(routerName + " " + id + " " + std + " " + term);
        return "Success";
    }

//    @Override
//    public String configCancelAccessListGlobal(String routerName, String numberOrName, String permitOrDeny,
//               String protocol, String sourceIp, String sourceMask, String aimIp, String aimMask, String relation, String port) {
//        return "Success";
//    }

    @Override
    public String execute(String routerName, String command) {
        return "aaa\nbbb\nRouter#".replaceAll("\n", "<br>");
    }

    @Override
    public String configCancelAccessList(String routerName, String interfaceName, String number, String inOrout) {
        System.out.println(routerName + " " + interfaceName + " " + number + " " + inOrout);
        return "Success";
    }

}
