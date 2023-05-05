package com.josephrodriguez.springboot.starterkit.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ScopedResponseFilter implements Filter {

    private static final String XResponseHeaderId = "X-Response-Id";

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        var response = (HttpServletResponse) servletResponse;
        response.addHeader(XResponseHeaderId, UUID.randomUUID().toString());

        filterChain. doFilter(servletRequest, servletResponse);
    }
}
