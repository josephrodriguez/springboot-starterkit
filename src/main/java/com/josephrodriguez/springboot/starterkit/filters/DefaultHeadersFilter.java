package com.josephrodriguez.springboot.starterkit.filters;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

//@Component
//public class DefaultHeadersFilter implements Filter {
//
//    private static final String XServerHeaderIP = "X-Server-IP";
//    private static final String XServerHeaderName = "X-Server-Name";
//
//    @Override
//    public void doFilter(
//            ServletRequest servletRequest,
//            ServletResponse servletResponse,
//            FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.addHeader(XServerHeaderIP, InetAddress.getLocalHost().getHostAddress());
//        response.addHeader(XServerHeaderName, InetAddress.getLocalHost().getHostName());
//
//        filterChain. doFilter(servletRequest, servletResponse);
//    }
//}
