package com.example.planner.common.filter;

import com.example.planner.auth.constant.LoginFailMessage;
import com.example.planner.auth.exception.LoginException;
import com.example.planner.util.AuthSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        //HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isWhiteList(requestURI) && !isGetMethod(httpRequest)){
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute(AuthSession.SESSION_KEY) == null ){
                throw new LoginException(LoginFailMessage.LOGIN_REQUIRED);
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
