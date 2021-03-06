package cn.edu.nju.software.computerNetwork.service;

import java.util.List;

/**
 * @ClassName: IRouterService  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月25日  
 *  
 */
public interface RouterService {

    String connect(String routerName, String ip, int port, String telnetPassword, String enablePassword);

    String configInterface(String routerName, String interfaceName, String ip, String mask);

    String ping(String routerName, String ip);

    String configStaticRouting(String routerName, String aimIp, String mask, String nextHopIp);

    String configCreateAccessListStandard(String routerName, String number, String permitOrDeny, String ipOrAny,
        String mask);

    String configCreateAccessListExtend(String routerName, String number, String permitOrDeny, String protocolOrPort,
        String sourceIp, String sourceMask, String aimIp, String aimMask, String relation, String protocol);

    String configApplyAccessList(String routerName, String interfaceName, String number, String inOrout);

    String configCancelAccessList(String routerName, String interfaceName, String number, String inOrout);

    List<List<String>> showIpAccessList(String routerName);

    List<String> showIpInterface(String routerName,String intf);

    String configCancelAccessListGlobal(String routerName, String stdOrext, String number);

    String configCancelAccessListGlobal(String routerName, String id, String std, String term);

//    String configCancelAccessListGlobal(String routerName, String numberOrName, String permitOrDeny, String protocol,
//        String sourceIp, String sourceMask, String aimIp, String aimMask, String relation, String port);

    String execute(String routerName, String command);
}