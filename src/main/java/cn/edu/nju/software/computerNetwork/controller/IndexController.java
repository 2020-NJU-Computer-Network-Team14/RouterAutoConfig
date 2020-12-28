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

    @RequestMapping({"/", "index.html"})
    public String index() {
        return "index";
    }

}
