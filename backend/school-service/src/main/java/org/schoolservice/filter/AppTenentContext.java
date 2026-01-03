package org.schoolservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class AppTenentContext implements Filter {

    private static final String LOGGER_TENENT_ID = "tenent_id";
    public static final String HEADER_TENENT = "X-Tenent-ID";
    private static final String DEFAULT_TENENT = "public";
    private static final ThreadLocal<String> currentTenent = new ThreadLocal<>();

    public static String getCurrentTenent () {
        String tenent = currentTenent.get();
        return Objects.requireNonNullElse( tenent, DEFAULT_TENENT );
    }

    public static void setCurrentTenent (String tenent) {
        MDC.put(LOGGER_TENENT_ID, tenent );
        currentTenent.set( tenent );
    }

    public static void clear () {
        MDC.clear();
        currentTenent.remove();
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String tenent = req.getHeader( HEADER_TENENT );

        if ( tenent != null ) {
            currentTenent.set( tenent );
        }

        filterChain.doFilter(request, response);
        clear();

    }
}
