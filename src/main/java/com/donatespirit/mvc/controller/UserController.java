package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.dao.UserDAO;
import com.donatespirit.mvc.dao.UserInfoDAO;
import com.donatespirit.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;

    @RequestMapping(value = "/user/list", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody
    ModelMap ajaxTest(){
        List<User> users = userDAO.findAll();

        ModelMap map = new ModelMap();
        map.addAttribute("data", users);
        map.addAttribute("success", true);
        return map;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap createUser(@RequestBody User user){
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

        try{
            userInfoDAO.addUserInfo(user.getUserInfo());
        }catch(Exception e){
            map.addAttribute("success", false);
            map.addAttribute("errorMessage", "unable to create userInfo");
            map.addAttribute("error", e.getMessage());
            return map;
        }

        return map;
    }
}