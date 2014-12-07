package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.dao.UserDAO;
import com.donatespirit.mvc.dao.UserInfoDAO;
import com.donatespirit.mvc.model.User;
import com.donatespirit.mvc.model.SessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;
    @Autowired private SessionContext sessionContext;


    //@ModelAttribute("sessionContext") SessionContext sessionContext
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap signIn(@RequestBody User attemptedUser, @RequestHeader(value="x-forwarded-for") String ipAddress){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        User foundUser = userDAO.getUserByUserName(attemptedUser.getUserName());
        if(foundUser == null){
            map.addAttribute("success", false);
            map.addAttribute("errorMessage", "either the user doesn't exist or incorrect password");
        }else{
            if(foundUser.getPassword().equals(attemptedUser.getPassword())){
                map.addAttribute("message", "welcome!");
                //set isSigned in.
                foundUser.setSignedIn(true);
                sessionContext.setUser(foundUser);
            }else{
                map.addAttribute("errorMessage", "either the user doesn't exist or incorrect password.");
            }
        }

        return map;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap listUser(){
        List<User> users = userDAO.findAll();

        ModelMap map = new ModelMap();
        map.addAttribute("data", users);
        map.addAttribute("success", true);
        return map;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap createUser(@RequestBody User user, @RequestHeader(value="x-forwarded-for") String ipAddress){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        try{
            userDAO.addUser(user);
        }catch(Exception e){
            map.addAttribute("success", false);
            map.addAttribute("errorMessage", "unable to create user");
            map.addAttribute("error", e.getMessage());
            return map;
        }

        user.getUserInfo().setUserId(user.getId());
        user.getUserInfo().setCreationIp(ipAddress);

        try{
            userInfoDAO.addUserInfo(user.getUserInfo());
        }catch(Exception e){
            map.addAttribute("success", false);
            map.addAttribute("errorMessage", "unable to create userInfo");
            map.addAttribute("error", e.getMessage());
            return map;
        }

        user.setSignedIn(true);
        sessionContext.setUser(user);

        return map;
    }


}
