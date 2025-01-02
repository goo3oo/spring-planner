package com.example.planner.util.filter;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.util.AuthSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/auth/signup", "/auth/login", "/auth/logout"};
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI) && !isGetMethod(httpRequest)){
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(AuthSession.SESSION_KEY) == null ){
                throw new AuthenticationException(ErrorMessage.LOGIN_REQUIRED);
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
    private boolean isGetMethod(HttpServletRequest request){
        return "GET".equals(request.getMethod());
    }
}
