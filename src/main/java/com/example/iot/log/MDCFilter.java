package com.example.iot.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put("traceId", traceId);

            if (servletRequest instanceof HttpServletRequest) {
                MDC.put("requestURI", ((HttpServletRequest) servletRequest).getRequestURI());
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.clear();
        }
    }
}
