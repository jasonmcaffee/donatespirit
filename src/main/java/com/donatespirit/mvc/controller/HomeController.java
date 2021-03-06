package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.model.SessionContext;
import com.donatespirit.mvc.service.Emailer;
import org.springframework.context.annotation.Scope;
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
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;
    @Autowired private SessionContext sessionContext;

    @RequestMapping( value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);

        if(sessionContext.getUser() != null && sessionContext.getUser().isSignedIn()){
            model.addAttribute("user", sessionContext.getUser());
        }
		return "home";
	}

    @RequestMapping( value="/spa", method = RequestMethod.GET)
    public String spa(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);

        if(sessionContext.getUser() != null && sessionContext.getUser().isSignedIn()){
            model.addAttribute("user", sessionContext.getUser());
        }
        return "spa";
    }

    @RequestMapping( value="/error", method = RequestMethod.GET)
    public String error(HttpServletRequest request, ModelMap model) {
        boolean notSignedIn = false;
        if(request != null && request.getAttribute("notSignedIn") != null){
            notSignedIn =(Boolean)request.getAttribute("notSignedIn");
        }
        String errorMessage = "general error.";
        if(notSignedIn){
            errorMessage = "You must be signed in to view this page.";
        }else{
            errorMessage = "Your account has not been approved yet. Ask a coleader to approve so you can view member pages.";
        }
        model.addAttribute("errorMessage", errorMessage);

        return "error";
    }

    @RequestMapping( value="/mail", method = RequestMethod.GET)
    public String mail(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        Emailer email = new Emailer();
        email.sendEmail("jasonlmcaffee@gmail.com");

        if(sessionContext.getUser() != null && sessionContext.getUser().isSignedIn()){
            model.addAttribute("user", sessionContext.getUser());
        }
        return "home";
    }

}