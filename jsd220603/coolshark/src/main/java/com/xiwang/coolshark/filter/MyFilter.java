package com.xiwang.coolshark.filter;

import com.xiwang.coolshark.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = {"/admin.html", "/insertBanner.html", "/insertProduct.html","/insertNews.html"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rt = (HttpServletRequest) request;
        HttpServletResponse re = (HttpServletResponse) response;
        HttpSession session = rt.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            chain.doFilter(request, response);

        } else {

            re.sendRedirect("/login.html");
        }

    }
}
