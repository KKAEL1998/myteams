package br.com.myteams.controller.usuario;

import br.com.myteams.dao.UsuarioDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.usuario.Usuario;
import br.com.myteams.util.CriptografiaUtil;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/*")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CustomConnection connection = null;

        try {
            connection = DBUtil.getConnection();

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            Usuario usuario = realizaLogin(usuarioDAO, req);

            String path = req.getPathInfo();

            boolean jaTemRedirecionamento = path != null;

            if (jaTemRedirecionamento) {
                resp.sendRedirect(extraiUri(req));
            } else {
                redirecionaUsuario(usuario, resp, req);
            }

        } catch (Exception ex) {
            req.setAttribute("exception", ex);
            req.getRequestDispatcher("/WEB-INF/login/index.jsp").forward(req, resp);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    private String extraiUri(HttpServletRequest req) {
        return new String(Base64.getDecoder().decode(req.getPathInfo().substring(1)));
    }

    private void redirecionaUsuario(Usuario usuario, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        switch (usuario.getTipoUsuario()) {
            case CLIENTE:
                resp.sendRedirect(req.getContextPath());
                break;
            case ADMINISTRADOR:
                resp.sendRedirect(req.getContextPath() + "/admin");
                break;
        }
    }

    private Usuario realizaLogin(UsuarioDAO usuarioDAO, HttpServletRequest req) throws SQLException, NegocioException {
        Usuario usuario = usuarioDAO.buscaPorEmailESenha(req.getParameter("email"),
                CriptografiaUtil.criptografa(req.getParameter("email") + req.getParameter("senha")));
        if (usuario == null) {
            throw new NegocioException("Usuario e senha invalido!");
        }
        req.getSession().setAttribute("usuario", usuario);
        return usuario;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login/index.jsp").forward(req, resp);
    }

}
