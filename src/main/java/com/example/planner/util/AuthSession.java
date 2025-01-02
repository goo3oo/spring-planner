package com.example.planner.util;

import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthSession {

    public static final String SESSION_KEY = "USER_ID";

    public static void setSession(HttpSession session, Long id) {
        session.setAttribute(SESSION_KEY, id);
    }

    public static Long getSession(HttpSession session) {
        if(session != null){
            return (Long) session.getAttribute(SESSION_KEY);
        }
        return null;
    }

    public static void invalidSession(HttpSession session) {
        session.invalidate();
    }
}
