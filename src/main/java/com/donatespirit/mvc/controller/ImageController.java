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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping( value="/images/upload", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("image") MultipartFile file, ModelMap model) {
        long userId = sessionContext.getUser().getId();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        String parts[] = file.getContentType().split("/");
        if(parts.length <= 1 || parts[0].equalsIgnoreCase("image")){
            model.addAttribute("errorMessage", "invalid file type");
            return "images";
        }
        String fileName = userId +"_"+ strDate + "." + parts[1];

        if(!file.isEmpty()){
            try{
                File dir = new File(uploadDir);
                File serverFile = new File(dir.getAbsolutePath() + "/" + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
            }catch(Exception e){
                System.out.println("error uploading imaga:" + e.getMessage());
            }
        }
        return "images";
    }
}
