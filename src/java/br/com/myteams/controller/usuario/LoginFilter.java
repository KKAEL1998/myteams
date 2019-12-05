package br.com.myteams.controller.usuario;

import br.com.myteams.model.usuario.TipoUsuario;
import br.com.myteams.model.usuario.Usuario;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/admin/*", "/conta/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        Usuario usuario = (Usuario) httpServletRequest.getSession().getAttribute("usuario");
        if (usuario == null) {
            redirectToLogin(response, httpServletRequest);
        } else if (httpServletRequest.getRequestURI().startsWith(httpServletRequest.getContextPath() + "/admin") && usuario.getTipoUsuario() != TipoUsuario.ADMINISTRADOR) {
            redirectToLogin(response, httpServletRequest);
        }
        chain.doFilter(request, response);
    }

    private void redirectToLogin(ServletResponse response, HttpServletRequest httpServletRequest) throws IOException {
        ((HttpServletResponse) response).sendRedirect(httpServletRequest.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }

}
