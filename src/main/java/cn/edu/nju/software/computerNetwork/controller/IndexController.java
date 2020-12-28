package cn.edu.nju.software.computerNetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * @ClassName: IndexController  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月28日  
 *  
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/inter")
    public String inter() {
        return "interface";
    }

    @RequestMapping("/ed")
    public String ed() {
        return "edit";
    }

    @RequestMapping("/conf")
    public String conf() {
        return "config";
    }

    @RequestMapping("/pi")
    public String pi() {
        return "ping";
    }

    @RequestMapping("/rou")
    public String rou() {
        return "route";
    }

    @RequestMapping("/cmd")
    public String cmd() {
        return "command";
    }

}
