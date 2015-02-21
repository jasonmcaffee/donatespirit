package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.dao.VoteTopicDAO;
import com.donatespirit.mvc.model.Message;
import com.donatespirit.mvc.model.SessionContext;
import com.donatespirit.mvc.model.VoteTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jason on 2/16/15.
 */
@Controller
public class VoteController {
    @Autowired
    private SessionContext sessionContext;

    @Autowired
    private VoteTopicDAO voteTopicDAO;

    @RequestMapping( value="/vote", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("user", sessionContext.getUser());

        List<VoteTopic> voteTopics = voteTopicDAO.findAll();
        model.addAttribute("voteTopics", voteTopics);

        return "vote";
    }

}
