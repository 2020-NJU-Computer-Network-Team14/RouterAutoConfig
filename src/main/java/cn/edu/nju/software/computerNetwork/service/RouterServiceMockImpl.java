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
        return "Success";
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
    public List<List<List<String>>> showIpAccessList(String routerName) {
        System.out.println(routerName);
        List<List<List<String>>> sss = new ArrayList<>();;
        List<List<String>> ss = new ArrayList<>();;
        List<String> s = new ArrayList<>();
        s.add("Standard");
        s.add("10");
        ss.add(s);

        s = new ArrayList<>();
        s.add("deny");
        s.add("1.1.1.1");
        s.add("0.0.0.1");
        ss.add(s);

        s = new ArrayList<>();
        s.add("permit");
        s.add("any");
        ss.add(s);
        sss.add(ss);

        ss = new ArrayList<>();
        s = new ArrayList<>();
        s.add("Extended");
        s.add("aaa");
        ss.add(s);
        sss.add(ss);

        ss = new ArrayList<>();
        s = new ArrayList<>();
        s.add("Extended");
        s.add("110");
        ss.add(s);

        s = new ArrayList<>();
        s.add("deny");
        s.add("icmp");
        s.add("host");
        s.add("2.2.2.2");
        s.add("host");
        s.add("3.3.3.3");
        ss.add(s);

        s = new ArrayList<>();
        s.add("deny");
        s.add("icmp");
        s.add("1.1.1.1");
        s.add("0.0.0.255");
        s.add("any");
        s.add("eq");
        s.add("telnet");
        ss.add(s);
        sss.add(ss);

        ss = new ArrayList<>();
        s = new ArrayList<>();
        s.add("Standard");
        s.add("test");
        ss.add(s);

        s = new ArrayList<>();
        s.add("deny");
        s.add("1.1.1.1");
        s.add("0.0.0.1");
        ss.add(s);

        s = new ArrayList<>();
        s.add("permit");
        s.add("any");
        ss.add(s);
        sss.add(ss);
        return sss;
    }

    @Override
    public String showIpInterface(String routerName) {
        System.out.println(routerName);
        return "Success";
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String stdOrext, String number) {
        return "Success";
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String numberOrName, String permitOrDeny, String ipOrAny, String mask) {
        return "Success";
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String numberOrName, String permitOrDeny,
               String protocol, String sourceIp, String sourceMask, String aimIp, String aimMask, String relation, String port) {
        return "Success";
    }

    @Override
    public String execute(String routerName, String command) {
        return "aaa";
    }

    @Override
    public String configCancelAccessList(String routerName, String interfaceName, String number, String inOrout) {
        System.out.println(routerName + " " + interfaceName + " " + number + " " + inOrout);
        return "Success";
    }

}
