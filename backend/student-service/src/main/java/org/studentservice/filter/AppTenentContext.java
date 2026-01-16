package org.studentservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.studentservice.utils.TenentUtil;

import java.io.IOException;
import java.util.Objects;

@Component
public class AppTenentContext implements Filter {
    public static final String HEADER_TENENT = "X-Tenent-ID";
    private static final String DEFAULT_TENENT = "public";
    private static final ThreadLocal<String> currentTenent = new ThreadLocal<>();

    private final TenentUtil tenentUtil;

    public AppTenentContext(TenentUtil tenentUtil) {
        this.tenentUtil = tenentUtil;
    }

    public static String getCurrentTenent () {
        String tenent = currentTenent.get();
        return Objects.requireNonNullElse( tenent, DEFAULT_TENENT );
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
        String tenentHeader = req.getHeader( HEADER_TENENT );

        if ( tenentHeader != null ) {
            String tenentName = tenentUtil.nameTenent( tenentHeader );

            System.out.println("TENENT NAME IS " + tenentName);
            currentTenent.set( tenentName );
        }

        filterChain.doFilter(request, response);
        clear();

    }
}