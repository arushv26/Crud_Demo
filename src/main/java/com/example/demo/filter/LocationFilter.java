package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getRequestURI().startsWith("/h2-console")) {
            filterChain.doFilter(servletRequest,servletResponse); // Pass through without filtering
            return;
        }

        String location = httpRequest.getHeader("User-Location");
        System.out.println(
                "Request: " + httpRequest.getRequestURI()
                        + " | Location: " + location
        );
        if ("India".equalsIgnoreCase(location)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        else {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("text/plain");

            httpResponse.getWriter().write(
                    "Location is not authenticated"
            );
        }
    }
}
