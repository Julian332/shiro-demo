package org.julian.demo.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.julian.demo.shiro.shiro.UserRealm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: TODO
 * Project: shiro
 *
 * @author : Julian
 * Create Time:2019/12/5 11:10
 */
//@Slf4j
@Controller
public class LoginController {
    public static final Logger logger=LoggerFactory.getLogger(LoginController.class);



    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping(value = "/login")
    public String login(String username, String password, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
            return "/Hello";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户不存在");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "/login";
        }

    }

    @RequestMapping(value = "/add")
    public String add(String username,String password,Model model){
        String value = UserRealm.map.put(username, password);

        System.out.println(value);

        model.addAttribute("msg","添加成功");
        return "Hello";
    }
}
