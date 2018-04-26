package com.telemedicine.user.controller;

import com.telemedicine.user.entity.UserInfo;
import com.telemedicine.user.form.User;
import com.telemedicine.user.repositoty.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String main(@RequestParam(value = "userId", defaultValue = "") String userId, Model model) {

        List<UserInfo> userInfoList = (List<UserInfo>) userInfoRepository.findAll(new Sort(Sort.Direction.ASC, "userId"));
        model.addAttribute("userInfoList", userInfoList);

        if(!"".equals(userId)){
            List<UserInfo> userinfo = userInfoRepository.findByUserId(userId);

            for (UserInfo m : userinfo) {
                model.addAttribute("userinfo", m);
            }
        }else{
            if(userInfoList.size() > 0) {
                List<UserInfo> userinfo = userInfoRepository.findByUserId(userInfoList.get(1).getUserId());

                for (UserInfo m : userinfo) {
                    model.addAttribute("userinfo", m);
                }
            }
        }


        return "/admin/userinfo.html";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public void updateAdminUser(@Valid User user, BindingResult bindingResult, Model model , HttpServletResponse response) throws IOException {

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
        writer.println("<script>alert('회원정보가 수정 되었습니다.');location.href='/admin?userId="+user.getUserId()+"';</script>");
        writer.flush();

        //return "redirect:admin?userId="+user.getUserId();
    }

}