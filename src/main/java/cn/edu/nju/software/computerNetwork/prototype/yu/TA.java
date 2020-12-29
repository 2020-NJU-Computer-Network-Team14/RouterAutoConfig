package cn.edu.nju.software.computerNetwork.prototype.yu;

/**  
 * @ClassName: TA  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月24日  
 *  
 */
public class TA {

    public static void main(String[] args) {
        String pattern = ".*[\\.U]{5}.*";
        System.out.println(" .U.U.  ".matches(pattern));
        System.out.println("  U.U.U    ".matches(pattern));
        System.out.println("U".matches(pattern));

        String str = "Type escape sequence to abort.\r\n"
            + "Sending 5, 100-byte ICMP Echos to 192.168.9.3, timeout is 2 seconds:\r\n" + "U.U.U\r\n"
            + "Success rate is 0 percent (0/5)";

        System.out.println(str.replaceAll("[\r\n]", "").matches(pattern));
    }

}
