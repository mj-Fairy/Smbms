package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import cn.smbms.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @Resource
    private UserService userService;

    //跳转到登录页面
    @RequestMapping("/login.html")
    public String Login() {
        return "/login";
    }

    //判断登录
    @RequestMapping(value = "/doLogin.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam("userCode") String userCode,
                          @RequestParam("userPassword") String userPassword,
                          HttpSession session,
                          HttpServletRequest request) {
        User user = userService.login(userCode, userPassword);
        if (user != null) {
            session.setAttribute(Constants.USER_SESSION, user);
            return "redirect:/sys/main.html";
        } else {
            request.setAttribute("error", "用户名或密码不正确");
            return "login";
        }
    }

    //跳转到主页
    @RequestMapping(value = "/sys/main.html")
    public String main() {
        return "frame";
    }

    //注销
    @RequestMapping(value = "/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }
}
