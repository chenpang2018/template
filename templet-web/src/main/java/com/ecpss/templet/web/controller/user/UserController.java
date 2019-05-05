package com.ecpss.templet.web.controller.user;

import com.ecpss.templet.domain.user.TempletUser;
import com.ecpss.templet.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chenpang on 2018/5/16 21:21.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/getUserList.action", produces = "application/json;charset=UTF-8")
    public ModelAndView getUserList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        List<TempletUser> list = userService.getUserList();
        view.addObject("list", list);
        view.setViewName("user/userList");
        return view;
    }
    @RequestMapping(value = "/getUserByUserNo.action", produces = "application/json;charset=UTF-8")
    public ModelAndView getUserByUserNo(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String userNo = request.getParameter("userNo");
        TempletUser user = userService.getUserByUserNo(userNo);
        view.addObject("user", user);
        view.setViewName("user/userDetail");
        return view;
    }
    @RequestMapping(value = "/getUserByUserName.action", produces = "application/json;charset=UTF-8")
    public ModelAndView getUserByUserName(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String userName = request.getParameter("userName");
        TempletUser user = userService.getUserByUserName(userName);
        view.addObject("user", user);
        view.setViewName("user/userDetail");
        return view;
    }
    @RequestMapping(value = "/toAddUser.action", produces = "application/json;charset=UTF-8")
    public ModelAndView toAddUser(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        view.setViewName("user/userAdd");
        return view;
    }
    @RequestMapping(value = "/saveUser.action", produces = "application/json;charset=UTF-8")
    public ModelAndView saveUser(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        String userName = request.getParameter("userName");
        String userNo = request.getParameter("userNo");
        userService.put(userNo, userName);
        view.setViewName("redirect:/user/getUserList.action");
        return view;
    }
}
