package com.ilender.micro.controller;

import com.ilender.micro.model.LoginInfo;
import com.ilender.micro.model.User;
import com.ilender.micro.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import com.ilender.micro.common.LogUtil;

import java.security.Principal;

@CrossOrigin
@RestController
public class LoginController {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService service;

    @PostMapping("user/public/login")
    @ResponseBody
    private Object doLogin(@RequestBody LoginInfo loginInfo) {
        LogUtil.logDebug("LoginController doLogin 1---> " + loginInfo);

        Object result = service.processLogin(loginInfo);
        LogUtil.logDebug("LoginController doLogin 2---> " + result);

        return result;
    }


    @GetMapping(produces = "application/json")
    @RequestMapping({ "/validateLogin" })
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    @GetMapping("/userName")
    @ResponseBody
    private User getLoginUserName() {
        String username = "";
        try {
            Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User("User login success");
        user.setName(username);
        return user;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        //Principal holds the logged in user information.
        // Spring automatically populates this principal object after login.
        return principal;
    }

    @RequestMapping("/userInfo")
    public String userInfo(Principal principal){
        final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        final Authentication authentication = oAuth2Authentication.getUserAuthentication();
        //Manually getting the details from the authentication, and returning them as String.
        return authentication.getDetails().toString();
    }

}
