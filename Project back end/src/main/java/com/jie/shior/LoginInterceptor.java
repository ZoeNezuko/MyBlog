package com.jie.shior;

import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        /**
         * Allow the options request, otherwise the front end cannot be brought with request header information,
         * which will cause the sessionID to change and shiro verification fails
         */
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // Use shiro to verify whether the user can access the aip that needs to be logged in
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            log.info("This AIP requires login to access");
            return false;
        }
        return true;
    }

}
