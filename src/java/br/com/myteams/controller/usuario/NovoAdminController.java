package br.com.myteams.controller.usuario;

import br.com.myteams.dao.UsuarioDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.usuario.Usuario;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/novo-admin/*")
public class NovoAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        if (cpf != null && !cpf.isEmpty()) {
            try (CustomConnection connection = DBUtil.getConnection()) {
                Usuario usuario = new UsuarioDAO(connection).procuraPorId(cpf);
                if (usuario == null) {
                    throw new NegocioException("Cliente não encontrado");
                }
                req.setAttribute("usuario_consultado", usuario);
            } catch (SQLException | NegocioException ex) {
                req.setAttribute("exception", ex);
                Logger.getLogger(NovoAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        req.getRequestDispatcher("/WEB-INF/novo-admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpf = req.getParameter("cpf");
        if (cpf != null && !cpf.isEmpty()) {
            try (CustomConnection connection = DBUtil.getConnection()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                Usuario usuario = usuarioDAO.procuraPorId(cpf);
                if (usuario == null) {
                    throw new NegocioException("Cliente não encontrado");
                }
                usuario.elevarAAdmin();
                usuarioDAO.atualizaTipo(usuario);
                req.setAttribute("success", usuario.getNome() + " é administrador");
            } catch (SQLException | NegocioException ex) {
                req.setAttribute("exception", ex);
                Logger.getLogger(NovoAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        req.getRequestDispatcher("/WEB-INF/novo-admin.jsp").forward(req, resp);
    }
    
    

}
