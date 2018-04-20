package com.telemedicine.user.controller;


import com.telemedicine.user.entity.UserInfo;
import com.telemedicine.user.form.User;
import com.telemedicine.user.repositoty.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        System.out.println(" main action 1 !!!");
        return "redirect:/test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        System.out.println(" login action 1 !!!");
        return "/login/index.html";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        logger.info("signup save !!!");
        return "/login/signup.html";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult, Model model) {
        logger.info("signup save ok !!!");
        if (bindingResult.hasErrors()) {
            return "redirect:login";
        }
        logger.info("signup save ok2!!!");
        userInfoRepository.save(
                new UserInfo( user.getUserId(),
                        user.getPass(),
                        user.getName(),
                        user.getEmail(),
                        "N","","",
                        user.getRoleType(),
                        "", ""
                ) );

        return "redirect:login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userinfo(Principal principal, Model model) {
        logger.info("userinfo save !!!");
        List<UserInfo> userinfo = userInfoRepository.findByUserId(principal.getName());
        if( userinfo.size() > 0 ){
            model.addAttribute("userinfo", userinfo.get(0));
        }
        return "/user/userinfo.html";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:user";
        }

        userInfoRepository.save(
                new UserInfo( user.getUserId(),
                        user.getPass(),
                        user.getName(),
                        user.getEmail(),
                        "N","","",
                        user.getRoleType(),
                        "", ""
                ) );

        return "redirect:user";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testinfo() {
        System.out.println(" testinfo action !!!");
        return "/test/index.html";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/login/index.html";
    }
}