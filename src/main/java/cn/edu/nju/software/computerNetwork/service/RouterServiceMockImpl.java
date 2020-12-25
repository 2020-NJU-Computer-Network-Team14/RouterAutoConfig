package cn.edu.nju.software.computerNetwork.service;

import org.springframework.stereotype.Service;

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
    public String configApplyAccessList(String routerName, String interfaceName, String protocol, String number,
        String inOrout) {
        System.out.println(routerName + " " + interfaceName + " " + protocol + " " + number + " " + inOrout);
        return "Success";
    }

    @Override
    public String showIpAccessList(String routerName) {
        System.out.println(routerName);
        return "Success";
    }

    @Override
    public String showIpInterface(String routerName) {
        System.out.println(routerName);
        return "Success";
    }

}
