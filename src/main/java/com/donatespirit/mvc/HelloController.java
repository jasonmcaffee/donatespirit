package com.donatespirit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import com.donatespirit.mvc.model.User;
import com.donatespirit.mvc.dao.UserDAO;
import com.donatespirit.mvc.dao.UserInfoDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;

    @RequestMapping( value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "/user/list", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap ajaxTest(){
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