package com.donatespirit.mvc.controller;

import com.donatespirit.mvc.model.SessionContext;
import com.donatespirit.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class ImageController {
    @Autowired private SessionContext sessionContext;

    private String uploadDir = "/Users/jason/dev/donate/src/main/webapp/resources/img/uploads";
    @RequestMapping( value="/images", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        if(sessionContext.getUser() != null && sessionContext.getUser().isSignedIn()){
            model.addAttribute("user", sessionContext.getUser());
        }
        return "images";
    }

    @RequestMapping( value="/images/upload", method = RequestMethod.GET)
    public String uploadImage(@RequestParam("image") MultipartFile file, ModelMap model) {
        if(sessionContext.getUser() == null || !sessionContext.getUser().isSignedIn()){
            //return error
        }

        if(!file.isEmpty()){
            try{
                File dir = new File(uploadDir);

                File serverFile = new File(uploadDir + "/" + file.getName());
            }catch(Exception e){

            }
        }


        return "images";
    }
}
