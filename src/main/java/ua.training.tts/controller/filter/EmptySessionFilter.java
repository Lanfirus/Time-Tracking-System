package ua.training.tts.controller.filter;

import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.FilterParameters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(FilterParameters.URL_PATTERN_ALL)
public class EmptySessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //ToDO implement below code
        /* HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        System.out.println("referrer = " + request.getHeader(FilterParameters.REFERER));
        System.out.println("session = " + session.getAttribute(ReqSesParameters.DTO));
        System.out.println(request.getRequestURI());
        System.out.println("login yes? = " + request.getRequestURI().contains(FilterParameters.LOGIN));
        System.out.println("registration? = " + request.getRequestURI().contains(FilterParameters.REGISTRATION));
        System.out.println("main? = " + request.getRequestURI().contains(FilterParameters.MAIN));

        if (session.getAttribute(ReqSesParameters.DTO) == null &&
                    (!request.getRequestURI().contains(FilterParameters.LOGIN) &&
                    (!request.getRequestURI().contains(FilterParameters.REGISTRATION)) &&
                    (!request.getRequestURI().contains(FilterParameters.MAIN)))) {
            response.sendRedirect(request.getHeader(Pages.INDEX_PAGE));
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
    }

    @Override
    public void destroy() {

    }
}
