package cn.luvletter.main;

import cn.luvletter.base.BaseController;
import cn.luvletter.sys.api.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/9
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    public String index(){
        return "index";
    }

}
