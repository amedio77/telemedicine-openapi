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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/login/index.html";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "/login/signup.html";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void createUser(@Valid User user, BindingResult bindingResult, Model model, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=EUC-KR");
        PrintWriter writer= response.getWriter();

        if (bindingResult.hasErrors()) {
            writer.println("<script>location.href='/error';</script>");
        }

        List<UserInfo> userinfo = userInfoRepository.findByUserId(user.getUserId());
        for( UserInfo m : userinfo) {
            writer.println("<script>alert('존재하는 아이디 입니다.');location.href='/signup';</script>");
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


        writer.println("<script>alert('회원가입이 완료 되었습니다. 로그인 화면으로 이동합니다');location.href='/login';</script>");
        writer.flush();

       // return "redirect:login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userinfo(Principal principal, Model model) {

        List<UserInfo> userinfo = userInfoRepository.findByUserId(principal.getName());

        for( UserInfo m : userinfo) {
            model.addAttribute("userinfo", m);
        }

        return "/user/userinfo.html";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void updateUser(@Valid User user, BindingResult bindingResult, Model model , HttpServletResponse response) throws IOException {
        //if (bindingResult.hasErrors()) {
        //    return "redirect:user";
        //}

        List<UserInfo> userinfo = userInfoRepository.findByUserId(user.getUserId());
        String pw = "";
        for( UserInfo m : userinfo) {
            pw = m.getPass();
        }

        if(user.getPass() == null || "".equals(user.getPass())){
            user.setPass(pw);
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

        response.setContentType("text/html;charset=EUC-KR");
        PrintWriter writer= response.getWriter();
        writer.println("<script>alert('회원정보 수정이 완료 되었습니다.');location.href='/user';</script>");
        writer.flush();

    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testinfo(Principal principal, Model model) {

        List<UserInfo> userinfo = userInfoRepository.findByUserId(principal.getName());

        for( UserInfo m : userinfo) {
            model.addAttribute("userinfo", m);
        }

        return "/test/index.html";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public void logoutPage (HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        response.setContentType("text/html;charset=EUC-KR");
        PrintWriter writer= response.getWriter();
        writer.println("<script>alert('logout 되었습니다.');location.href='/login';</script>");
        writer.flush();

    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public void error(@RequestParam(value = "msg", defaultValue = "") String msg, HttpServletResponse response) throws IOException {
        System.out.println(" error action !!!");

        response.setContentType("text/html;charset=EUC-KR");
        PrintWriter writer= response.getWriter();

        if(msg.equals("loginFail")) {
            writer.println("<script>alert('로그인에 실패하였습니다.');</script>");
        }else if(msg.equals("accessDenied")) {
            writer.println("<script>alert('접근 원한이 없습니다.');</script>");
        }

        writer.println("<script>location.href='/login';</script>");

        writer.flush();
    }
}