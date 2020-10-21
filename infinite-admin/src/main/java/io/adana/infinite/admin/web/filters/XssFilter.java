package io.adana.infinite.admin.web.filters;

import io.adana.infinite.common.filters.XssHttpServletRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author simon
 * @Email merin@outlook.com
 * @Date 2019/2/27
 * @Description xssFilter
 */
@WebFilter(filterName = "xssFilter", urlPatterns = {"/*"})
@Component
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("enable xssFilter.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
        System.out.println("close the filter");
    }

}