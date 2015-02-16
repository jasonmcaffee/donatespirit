package com.donatespirit.mvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donatespirit.mvc.model.SessionContext;
import com.donatespirit.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.ArrayList;


@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Autowired private SessionContext sessionContext;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //determine if the request is an ajax request.
        String ipAddress = request.getHeader("x-forwarded-for");
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjax = ajaxHeader != null && ajaxHeader.length() > 0 && ajaxHeader.equalsIgnoreCase("XMLHttpRequest");

        //checked for blocked ip addresses
        System.out.println("user is trying to get to"+ request.getRequestURI() + " with ip:" + ipAddress);
        if(isIpBlocked(ipAddress)){
            response.sendRedirect("http://lemonparty.org");
            return false;
        }
        User user = sessionContext.getUser();
        if(user == null || !user.isSignedIn()){
            System.out.println("user is not signed in");
            //response.sendRedirect("http://donatespirit.com/error");//
            request.getRequestDispatcher("/error").forward(request, response);
            return false;
        }else{
            if( !user.isApproved()){
                System.out.println("user is not approved");
                request.getSession().invalidate();
                //response.sendRedirect("http://donatespirit.com/error");//
                request.getRequestDispatcher("/error").forward(request, response);
                return false;
            }
            System.out.println("user is signed in as:" + user.getUserName());
        }

        return true;
    }

    private boolean isIpBlocked(String ip){
        ArrayList<String> blocked = new ArrayList<String>();
        blocked.add("172.56.5.139");

        if(blocked.contains(ip)){
            System.out.println("ip is blocked: " + ip);
            return true;
        }else{
            return false;
        }
    }
}
