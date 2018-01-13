package com.hdu.train.controller;

import com.hdu.train.pojo.User;
import com.hdu.train.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: JianengZhang
 * @Description
 * @Date: Create in 12:35 18-1-12
 * @Modified By:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //调用Service
        User existUser = userService.checkLogin(user);
        if (null != existUser) {
            if (existUser.getRole() == 1) {
                modelAndView.setViewName("redirect:/station/showAll");
            } else {
                modelAndView.setViewName("success");
            }
        } else {
            modelAndView.addObject("errorMessage", "用户名或密码错误");
            modelAndView.setViewName("../login");
        }
        return modelAndView;
    }
}
