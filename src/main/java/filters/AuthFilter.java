package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,ServletException{
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        servletRequest.setCharacterEncoding("UTF-8");

        try {
            Boolean isAuthorized = (Boolean)httpSession.getAttribute("isAuthorized");
            String role = (String)httpSession.getAttribute("role");

            if (isAuthorized == null || !isAuthorized) {
                httpServletResponse.sendRedirect("login.jsp");
            } else if(isAuthorized && role.equals("admin")) {
                servletRequest.setAttribute("role", "admin");
                RequestDispatcher dispatcher=servletRequest.getRequestDispatcher( "/BuffServlet" );
                dispatcher.forward( servletRequest, servletResponse );
            } else if (isAuthorized && role.equals("user"))
            {
                servletRequest.setAttribute("role", "user");
                RequestDispatcher dispatcher=servletRequest.getRequestDispatcher( "/BuffServlet" );
                dispatcher.forward( servletRequest, servletResponse );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy(){
        this.filterConfig=null;
    }
}
