package com.example.fantasy.shared.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for security operations
 */
public final class SecurityUtils {
    
    private SecurityUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get the current user ID from the security context
     * @return the current user ID or null if not authenticated
     */
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || 
                "anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }
        
        // This assumes that the principal is a UserDetails with the ID as the username
        // In a real application, you would need to cast to your custom UserDetails implementation
        try {
            return Long.parseLong(authentication.getName());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Check if the current user has the specified role
     * @param role the role to check
     * @return true if the user has the role, false otherwise
     */
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        String roleWithPrefix = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(roleWithPrefix));
    }
}
