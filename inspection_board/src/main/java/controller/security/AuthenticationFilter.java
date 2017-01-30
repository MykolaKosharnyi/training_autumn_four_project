package controller.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HttpUtils;
import model.entity.User;

import static controller.ApplicationResources.*;
import static java.util.Objects.isNull;

/**
 * Makes sure that this request comes from a signed in user and the session has not expired.
 */
public class AuthenticationFilter/* implements Filter*/ {
//    private List<String> unProtectedUris = Arrays.asList("/backend/signIn", "/backend/signUp",
//            "/backend/validation");
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
////        if (requestNotRequiresAuthentication(request)) {
////            chain.doFilter(servletRequest, servletResponse);
////            return;
////        }
////
////        String requestUri = request.getRequestURI();
////        User currentUser = HttpUtils.getCurrentUserFromFromDb(request);
////
////        if (isNull(currentUser)) {
////            request.getSession().setAttribute(ORIGINAL_URI_ATTR_NAME, requestUri);
////            response.sendRedirect(LOGIN_PAGE);
////
////        } else if (isUserActive(currentUser)) {
////            response.sendRedirect(SIGN_OUT_URI);
////
////        } else {
//            chain.doFilter(servletRequest, servletResponse);
////        }
//    }
//
////    private boolean requestNotRequiresAuthentication(HttpServletRequest request) {
////        return unProtectedUris.contains(request.getRequestURI());
////    }
////
////    private boolean isUserActive(User currentUser) {
////        return !User.Status.ACTIVE.equals(currentUser.getStatus());
////    }
//
//    @Override
//    public void destroy() {
//    }
}
