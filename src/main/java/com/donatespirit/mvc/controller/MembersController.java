package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.dao.MessageDAO;
import com.donatespirit.mvc.model.Message;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MembersController {

    @Autowired private UserDAO userDAO;
    @Autowired private UserInfoDAO userInfoDAO;
    @Autowired private SessionContext sessionContext;
    @Autowired private MessageDAO messageDAO;

    @RequestMapping( value="/members", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("user", sessionContext.getUser());

        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);

        List<Message> messages = messageDAO.findAll();
        model.addAttribute("messages", messages);

        return "members";
    }

    @RequestMapping(value="/strategies", method = RequestMethod.GET)
    public String showStrategies(ModelMap model) {

        return "strategies";
    }

    @RequestMapping(value="/approvals", method=RequestMethod.GET)
    public String showApprovals(ModelMap model){
        List<User> unapprovedUsers = userDAO.findAllNotApproved();
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "accountApproval";
    }

    @RequestMapping(value="/approveUser", method=RequestMethod.POST)
    public String approve(@RequestParam String userId, ModelMap model){
        System.out.println("approve for userId: " + userId);
        long uId = Long.parseLong(userId);
        User approvedUser = userDAO.getUserByUserId(uId);
        if(approvedUser != null){
            approvedUser.setApproved(true);
            userDAO.updateUser(approvedUser);
        }else{
            model.addAttribute("error", "that user does not exist.");
        }


        List<User> unapprovedUsers = userDAO.findAllNotApproved();
        model.addAttribute("unapprovedUsers", unapprovedUsers);
        return "accountApproval";
    }

    @RequestMapping(value = "/message/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody ModelMap createUser(@RequestBody Message message, @RequestHeader(value="x-forwarded-for") String ipAddress){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        //get user id and add to message.
        message.setUserId(sessionContext.getUser().getId());
        message.setIp(ipAddress);

        try{
            messageDAO.addMessage(message);
        }catch(Exception e){
            map.addAttribute("success", false);
            map.addAttribute("errorMessage", "unable to create message");
            map.addAttribute("error", e.getMessage());
            return map;
        }

        return map;
    }

}