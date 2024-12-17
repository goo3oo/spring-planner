package com.example.planner.common.util;

import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.common.exception.AuthenticationException;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthSession {

    public static final String SESSION_KEY = "USER_ID";

    public static void setSession(HttpSession session, Long id) {
        if(session.getAttribute(SESSION_KEY) != null){
            invalidSession(session);
        }
        session.setAttribute(SESSION_KEY, id); // 오 자동으로 쿠키쿠키?
    }

    public static Long getSession(HttpSession session) {
        if (session != null) {
            Object sessionKey = session.getAttribute(SESSION_KEY);
            if (sessionKey == null) {
                throw new AuthenticationException(AuthFailMessage.NO_LOGIN_INFO);
            }
            return (Long) sessionKey;
        }
        throw new AuthenticationException(AuthFailMessage.LOGIN_REQUIRED);
    }

    public static void invalidSession(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
    }
}
