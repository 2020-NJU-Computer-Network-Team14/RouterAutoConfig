package cn.edu.nju.software.computerNetwork.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.software.computerNetwork.service.RouterService;

/**  
 * @ClassName: ConfigController  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月24日  
 *  
 */

@RestController
@RequestMapping()
public class ConfigController {

    @Resource(name = "test-route-service")
    private RouterService routerService;

    @RequestMapping(value = "telnet")
    public String telnet(HttpServletRequest user) {
        String routerName = user.getParameter("router");
        String ip = user.getParameter("ip");
        String telnetPassword = user.getParameter("password");
        String enablePassword = user.getParameter("enable");

        return routerService.connect(routerName, ip, 23, telnetPassword, enablePassword);
    }

    @RequestMapping(value = "interface")
    public String configInterface(HttpServletRequest user) {

        String routerName = user.getParameter("router");
        String interfaceName = user.getParameter("interface");
        String ip = user.getParameter("ip");
        String mask = user.getParameter("mask");

        return routerService.configInterface(routerName, interfaceName, ip, mask);
    }

    @RequestMapping(value = "route")
    public String route(HttpServletRequest user) {

        String routerName = user.getParameter("router");
        String ipPrefix = user.getParameter("prefix");
        String mask = user.getParameter("mask");
        String nextHopIp = user.getParameter("next");

        return routerService.configStaticRouting(routerName, ipPrefix, mask, nextHopIp);
    }

    // @RequestMapping(value = "binding")
    // public String binding(HttpServletRequest user) {
    //
    // String routerName = user.getParameter("router");
    // String interfaceName = user.getParameter("interface");
    // String inOrOut = user.getParameter("in");
    // String aclName = user.getParameter("name");
    //
    // return routerService.configApplyAccessList(routerName, interfaceName, protocol, number, inOrOut);
    // }

    @RequestMapping(value = "ping")
    public String ping(HttpServletRequest user) {

        String routerName = user.getParameter("router");
        String targetIp = user.getParameter("ip");

        return routerService.ping(routerName, targetIp);
    }

    // @RequestMapping(value = "command")
    // public String command(HttpServletRequest user) {
    //
    //
    // String router = user.getParameter("router");
    // String command = user.getParameter("command");
    //
    // return routerService.
    // }

    @RequestMapping(value = "show_acl")
    public String showAccessList(HttpServletRequest user) {

        String routerName = user.getParameter("router");

        return routerService.showIpAccessList(routerName);
    }

    @RequestMapping(value = "show_interface")
    public String showInterface(HttpServletRequest user) {

        String routerName = user.getParameter("router");

        return routerService.showIpInterface(routerName);
    }

}
