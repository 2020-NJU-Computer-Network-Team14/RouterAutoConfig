package cn.edu.nju.software.computerNetwork.telnet;

import static org.junit.Assert.*;

import org.junit.Test;

/**  
 * @ClassName: TestTelnetService  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月23日  
 *  
 */
public class TestTelnetService {

    @Test
    public void test() throws Exception {
        try (TelnetService telnet = new TelnetService()) {
            telnet.connect("192.168.10.3", 23, "123", "CISCO");
        }
    }

    @Test
    public void testPasswordIncorrect() throws Exception {
        try {
            try (TelnetService telnet = new TelnetService()) {
                telnet.connect("192.168.10.3", 23, "122", "CISCO");
            }
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testPasswordIncorrect2() throws Exception {
        try {
            try (TelnetService telnet = new TelnetService()) {
                telnet.connect("192.168.10.3", 23, "123", "CISC");
            }
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

}
