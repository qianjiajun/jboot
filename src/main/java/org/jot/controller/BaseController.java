package org.jot.controller;

import org.jot.entity.user.User;
import org.jot.util.Const;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User operator;
    protected Long operatorId;

    @ModelAttribute
    protected void init(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        this.request = httpServletRequest;
        this.response = httpServletResponse;
        this.session = httpSession;
        this.operator = (User) session.getAttribute(Const.SESSION_USER);
        this.operatorId = (Long) session.getAttribute(Const.SESSION_USER_ID);
    }


}
