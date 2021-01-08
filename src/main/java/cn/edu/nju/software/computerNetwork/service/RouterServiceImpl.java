package cn.edu.nju.software.computerNetwork.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.edu.nju.software.computerNetwork.router.Router;

/**  
 * @ClassName: RouterService  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月24日  
 *  
 */

@Service
public class RouterServiceImpl implements RouterService {

    private Map<String, Router> routers = new HashMap<>();

    @Override
    public String connect(String routerName, String ip, int port, String telnetPassword, String enablePassword) {
        routers.putIfAbsent(routerName, new Router());
        return routers.get(routerName).connect(ip, port, telnetPassword, enablePassword);
    }

    @Override
    public String configInterface(String routerName, String interfaceName, String ip, String mask) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configInterface(interfaceName, ip, mask);
        } catch (Exception e) {
            e.printStackTrace();
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String ping(String routerName, String ip) {
        if (!routers.containsKey(routerName)) {
            return "Router is not connected";
        }
        try {
            return routers.get(routerName).ping(ip);
        } catch (Exception e) {
            return "IO error";
        }
    }

    @Override
    public String configStaticRouting(String routerName, String aimIp, String mask, String nextHopIp) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configStaticRouting(aimIp, mask, nextHopIp);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String configCreateAccessListStandard(String routerName, String number, String permitOrDeny, String ipOrAny,
        String mask) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configCreateAccessListStandard(number, permitOrDeny, ipOrAny, mask);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String configCreateAccessListExtend(String routerName, String number, String permitOrDeny,
        String protocolOrPort, String sourceIp, String sourceMask, String aimIp, String aimMask, String relation,
        String protocol) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configCreateAccessListExtend(number, permitOrDeny, protocolOrPort, sourceIp,
                sourceMask, aimIp, aimMask, relation, protocol);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String configApplyAccessList(String routerName, String interfaceName, String number, String inOrout) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configApplyAccessList(interfaceName, number, inOrout);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String configCancelAccessList(String routerName, String interfaceName, String number, String inOrout) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configCancelAccessList(interfaceName, number, inOrout);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }


    @Override
    public List<List<String>> showIpAccessList(String routerName) {
        if (!routers.containsKey(routerName)) {
            return null;
        }
        try {
            return routers.get(routerName).showIpAccessList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String showIpInterface(String routerName) {
        if (!routers.containsKey(routerName)) {
            return "Router is not connected";
        }
        try {
            return routers.get(routerName).showIpInterface();
        } catch (Exception e) {
            return "IO error";
        }
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String stdOrext, String number) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configCancelAccessListGlobal(stdOrext, number);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

    @Override
    public String configCancelAccessListGlobal(String routerName, String id, String std, String term) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            routers.get(routerName).configCancelAccessListGlobal(id, std, term);
        } catch (Exception e) {
            return "未知IO异常……";
        }
        return "Success";
    }

//    @Override
//    public String configCancelAccessListGlobal(String routerName, String numberOrName, String permitOrDeny,
//                String protocol, String sourceIp, String sourceMask, String aimIp, String aimMask, String relation, String port) {
//        if (!routers.containsKey(routerName)) {
//            return "路由器未连接！";
//        }
//        try {
//            routers.get(routerName).configCancelAccessListGlobal(numberOrName, permitOrDeny, protocol, sourceIp,
//                sourceMask, aimIp, aimMask, relation, port);
//        } catch (Exception e) {
//            return "未知IO异常……";
//        }
//        return "Success";
//    }

    @Override
    public String execute(String routerName, String command) {
        if (!routers.containsKey(routerName)) {
            return "路由器未连接！";
        }
        try {
            return routers.get(routerName).execute(command);
        } catch (Exception e) {
            return "未知IO异常……";
        }
    }
}
