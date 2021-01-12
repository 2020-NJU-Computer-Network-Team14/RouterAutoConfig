package cn.edu.nju.software.computerNetwork.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
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

	@Resource(name = "route-service")
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

	@RequestMapping(value = "binding")
	public String binding(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String interfaceName = user.getParameter("interface");
		String inOrOut = user.getParameter("in");
		String aclName = user.getParameter("name");

		return routerService.configApplyAccessList(routerName, interfaceName, aclName, inOrOut);
	}

	@RequestMapping(value = "noBinding")
	public String noBinding(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String interfaceName = user.getParameter("interface");
		String inOrOut = user.getParameter("in");
		String aclName = user.getParameter("name");

		return routerService.configCancelAccessList(routerName, interfaceName, aclName, inOrOut);
	}

	@RequestMapping(value = "ping")
	public String ping(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String targetIp = user.getParameter("ip");

		return routerService.ping(routerName, targetIp);
	}

	@RequestMapping(value = "command")
	public String command(HttpServletRequest user) {

		String router = user.getParameter("router");
		String command = user.getParameter("command");

		return routerService.execute(router, command);
	}

	@RequestMapping(value = "show_acl")
	public List<List<String>> showAccessList(HttpServletRequest user) {

		String routerName = user.getParameter("router");

		return routerService.showIpAccessList(routerName);
	}

	@RequestMapping(value = "show_interface")
	public List<String> showInterface(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String interfaceName = user.getParameter("interface");

		return routerService.showIpInterface(routerName, interfaceName);
	}

	@RequestMapping(value = "standard")
	public String standard(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String id = user.getParameter("id");
		String permit = user.getParameter("permit");
		String ip = user.getParameter("ip");
		String mask = user.getParameter("mask");

		return routerService.configCreateAccessListStandard(routerName, id, permit, ip, mask);
	}

	@RequestMapping(value = "extended")
	public String extend(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String id = user.getParameter("id");
		String permit = user.getParameter("permit");
		String protocol = user.getParameter("protocol");

		String srcIp = user.getParameter("srcIp");
		String srcMask = user.getParameter("srcMask");

		String destIp = user.getParameter("destIp");
		String destMask = user.getParameter("destMask");

		String relation = user.getParameter("relation");
		String port = user.getParameter("port");

		return routerService.configCreateAccessListExtend(routerName, id, permit, protocol, srcIp, srcMask, destIp,
				destMask, relation, port);
	}

	@RequestMapping(value = "deleteList")
	public String deleteList(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String std = user.getParameter("std");
		String id = user.getParameter("id");

		return routerService.configCancelAccessListGlobal(routerName, std, id);
	}

	@RequestMapping(value = "deleteTerm")
	public String deleteTerm(HttpServletRequest user) {

		String routerName = user.getParameter("router");
		String id = user.getParameter("id");
		String std = user.getParameter("std");
		String term = user.getParameter("term");

		return routerService.configCancelAccessListGlobal(routerName, id, std, term);
	}

}
