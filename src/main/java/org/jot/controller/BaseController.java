package org.jot.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    protected void init(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        this.request = httpServletRequest;
        this.response = httpServletResponse;
        this.session = httpSession;
    }


}
