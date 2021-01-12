package cn.edu.nju.software.computerNetwork.telnet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import cn.edu.nju.software.computerNetwork.service.RouterServiceImpl;

/**
 * @ClassName: TestRouterServiceImpl
 *
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author 余定邦
 *
 * @date 2020年12月25日
 * 
 */
public class TestRouterServiceImpl {

	private RouterServiceImpl routerServiceImpl = new RouterServiceImpl();

	// @Test
	// public void test() {
	// String code = routerServiceImpl.connect("RTA", "192.168.10.3", 23, "",
	// "CISCO");
	// testSuccess(code);
	// code = routerServiceImpl.connect("RTB", "192.168.10.4", 23, "", "CISCO");
	// testSuccess(code);
	// code = routerServiceImpl.connect("RTC", "192.168.10.5", 23, "", "CISCO");
	// testSuccess(code);
	// }

	private void testSuccess(String code) {
		assertEquals("Success", code);
	}

	// @Test
	// public void testConfigInterface() {
	// login();
	// String code = routerServiceImpl.configInterface("RTA", "s0/0/0",
	// "192.168.8.3", "255.255.255.0");
	// testSuccess(code);
	// code = routerServiceImpl.configInterface("RTB", "s0/0/0", "192.168.8.4",
	// "255.255.255.0");
	// testSuccess(code);
	// code = routerServiceImpl.configInterface("RTB", "s0/0/1", "192.168.9.4",
	// "255.255.255.0");
	// testSuccess(code);
	// code = routerServiceImpl.configInterface("RTC", "s0/0/1", "192.168.9.3",
	// "255.255.255.0");
	// testSuccess(code);
	// }

	// @Test
	// public void testDirectConnect() {
	// login();
	// String code = routerServiceImpl.ping("RTA", "192.168.8.4");
	// testSuccess(code);
	// code = routerServiceImpl.ping("RTC", "192.168.9.4");
	// testSuccess(code);
	// }

	// @Test
	// public void testConfigStaticRouting() {
	// login();
	// String code = routerServiceImpl.configStaticRouting("RTA", "192.168.9.0",
	// "255.255.255.0", "192.168.8.4");
	// testSuccess(code);
	// code = routerServiceImpl.configStaticRouting("RTC", "192.168.8.0",
	// "255.255.255.0", "192.168.9.4");
	// testSuccess(code);
	// }

	// @Test
	// public void testStaticRouting() {
	// login();
	// String code = routerServiceImpl.ping("RTA", "192.168.9.3");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLStandrad() {
	// login();
	// String code = routerServiceImpl.configCreateAccessListStandard("RTB",
	// "10", "deny", "192.168.8.0", "0.0.0.255");
	// testSuccess(code);
	// code = routerServiceImpl.configCreateAccessListStandard("RTB", "10",
	// "permit", "any", null);
	// testSuccess(code);
	// code = routerServiceImpl.configApplyAccessList("RTB", "s0/0/1", "10",
	// "out");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLStandradPing() {
	// login();
	// String code = routerServiceImpl.ping("RTA", "192.168.9.3");
	// Assert.assertEquals("Not accessible", code);
	// code = routerServiceImpl.ping("RTA", "192.168.9.4");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLPing() {
	// login();
	// String code = routerServiceImpl.configCancelAccessList("RTB", "s0/0/1",
	// "10", "out");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLStandradPing() {
	// login();
	// String code = routerServiceImpl.ping("RTA", "192.168.9.3");
	// testSuccess(code);
	// code = routerServiceImpl.ping("RTA", "192.168.9.4");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLStandradIn() {
	// login();
	// String code = routerServiceImpl.configApplyAccessList("RTB", "s0/0/0",
	// "10", "in");
	// testSuccess(code);
	// }

	// @Test
	// public void testACLStandradPingIn() {
	// login();
	// String code = routerServiceImpl.ping("RTA", "192.168.9.3");
	// Assert.assertEquals("Not accessible", code);
	// code = routerServiceImpl.ping("RTA", "192.168.9.4");
	// Assert.assertEquals("Not accessible", code);
	//
	// code = routerServiceImpl.configCancelAccessList("RTB", "s0/0/0", "10",
	// "in");
	// testSuccess(code);
	// }

	// @Test
	// public void testShowIpAccessList() {
	// login();
	// String code = routerServiceImpl.showIpAccessList("RTC");
	// System.out.println(code);
	// }

	@Test
	// public void testShowIpAccessList() {
	// login();
	// String code = routerServiceImpl.showIpInterface("RTB",);
	// System.out.println(code);
	// }

	private void login() {
		routerServiceImpl.connect("RTA", "192.168.10.3", 23, "", "CISCO");
		routerServiceImpl.connect("RTB", "192.168.10.4", 23, "", "CISCO");
		routerServiceImpl.connect("RTC", "192.168.10.5", 23, "", "CISCO");
	}

}
