package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.model.SessionContext;
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
//@SessionAttributes({"sessionContext"})
//@Scope("session")
//@Scope("request")
public class HomeController {

    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;
    @Autowired private SessionContext sessionContext;

    @RequestMapping( value="/", method = RequestMethod.GET)          //, HttpSession session
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
//        SessionContext sessionContext = (SessionContext)session.getAttribute("sessionContext");
//        if(sessionContext == null){
//            sessionContext = new SessionContext();
//            session.setAttribute("sessionContext", sessionContext);
//        }
        if(sessionContext.getUser() != null && sessionContext.getUser().isSignedIn()){
            model.addAttribute("user", sessionContext.getUser());
        }
		return "home";
	}



}