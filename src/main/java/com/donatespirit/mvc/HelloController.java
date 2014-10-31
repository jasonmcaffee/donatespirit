package com.donatespirit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @RequestMapping( value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "/ajaxTest", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)        //, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    public @ResponseBody List<String> ajaxTest(){
        List<String> result = new ArrayList<String>();

        result.add("hello");
        result.add("world");
        return result;
    }


}