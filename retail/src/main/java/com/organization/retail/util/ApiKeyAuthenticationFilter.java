package com.organization.retail.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    private final SecretsManagerService secretsManagerService;

    // Paths that don't require API key authentication
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/products",           // JSP endpoints - list
            "/products/add",       // JSP endpoints - add form & POST
            "/products/edit",      // JSP endpoints - edit form & POST
            "/products/view",      // JSP endpoints - view details
            "/products/delete",    // JSP endpoints - delete
            "/WEB-INF",           // JSP resources
            "/error"              // Error pages
    );

    public ApiKeyAuthenticationFilter(SecretsManagerService secretsManagerService) {
        this.secretsManagerService = secretsManagerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();

        // Skip API key check for excluded paths
        if (isExcludedPath(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        // For REST API endpoints (/api/*), enforce API key authentication
        String requestApiKey = request.getHeader("apiKey");

        if (requestApiKey == null || requestApiKey.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"API Key is missing\"}");
            return;
        }

        String validApiKey = secretsManagerService.getApiKey();

        if (!requestApiKey.equals(validApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid API Key\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Check if the request path should be excluded from API key authentication
     */
    private boolean isExcludedPath(String requestPath) {
        for (String excludedPath : EXCLUDED_PATHS) {
            if (requestPath.startsWith(excludedPath)) {
                return true;
            }
        }
        return false;
    }
}
