package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.dao.VoteDAO;
import com.donatespirit.mvc.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jason on 2/16/15.
 */
@Controller
public class VoteController {

    @Autowired
    private VoteDAO voteDAO;

    @Autowired private SessionContext sessionContext;

    /**
     * View the vote page, which contains all vote topics
     * @param model
     * @return
     */
    @RequestMapping( value="/vote", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("user", sessionContext.getUser());

        List<VoteTopic> voteTopics = voteDAO.findAll();
        model.addAttribute("voteTopics", voteTopics);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String voteTopicsJson = mapper.writeValueAsString(voteTopics);
            model.addAttribute("voteTopicsJson", voteTopicsJson);
        }catch (Exception e){
            model.addAttribute("success", "false");

        }

        return "vote";
    }

    @RequestMapping(value = "/vote/castvote", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody
    ModelMap castVote(@RequestBody UserVote userVote){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        User user = sessionContext.getUser();
        userVote.setUserId(user.getId());

        //check if vote topic is expired yet.
        VoteTopic voteTopic = voteDAO.getVoteTopicById(userVote.getVoteTopicId());

        UserVote fromDbUserVote = voteDAO.getUserVoteById(userVote.getVoteTopicId(), user.getId());
        if(fromDbUserVote != null){
            userVote.setId(fromDbUserVote.getId());
        }
        try{
            voteDAO.castVote(userVote);
        }catch(ConstraintViolationException cve){

        }catch(Exception e){

        }
        return map;
    }

    @RequestMapping(value = "/vote/createvotetopic", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody
    ModelMap createVoteTopic(@RequestBody CreateVoteTopic voteTopic){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        User user = sessionContext.getUser();
        voteTopic.setCreatorUserId(user.getId());

        voteDAO.createVoteTopic(voteTopic);

        return map;
    }

    @RequestMapping(value = "/vote/list/votetopics", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody
    ModelMap listVoteTopicsJSON(){
        ModelMap map = new ModelMap();
        map.addAttribute("success", true);

        User user = sessionContext.getUser();

        List<VoteTopic> voteTopics = voteDAO.findAll();
        map.addAttribute("voteTopics", voteTopics);

        return map;
    }

}


