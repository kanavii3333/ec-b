package com.fullness.ec.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomExceptionHundler implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        String requestURI = request.getRequestURI();

        if(requestURI.contains("/admin")) {
            return new ModelAndView("error/adminError");
        }
        
        return new ModelAndView("error/customerError");
    }
    
}
