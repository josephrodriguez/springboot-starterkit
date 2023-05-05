package com.josephrodriguez.springboot.starterkit.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class ServerHeaderFilter implements Filter {

    private static final String XServerHeaderIP = "X-Server-IP";
    private static final String XServerHeaderName = "X-Server-Name";

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        var response = (HttpServletResponse) servletResponse;
        response.addHeader(XServerHeaderIP, InetAddress.getLocalHost().getHostAddress());
        response.addHeader(XServerHeaderName, InetAddress.getLocalHost().getHostName());

        filterChain. doFilter(servletRequest, servletResponse);
    }
}
