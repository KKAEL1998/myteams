package br.com.myteams.controller.usuario;

import br.com.myteams.dao.EnderecoDAO;
import br.com.myteams.dao.UsuarioDAO;
import br.com.myteams.exception.InfraestruturaException;
import br.com.myteams.model.endereco.Endereco;
import br.com.myteams.model.usuario.Usuario;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import br.com.myteams.util.DataUtil;
import br.com.myteams.util.NumeroUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/conta/alterar")
public class AlteraUsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomConnection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

            Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
            usuario.setDataNascimento(DataUtil.toDate(req.getParameter("dataNascimento")));
            usuario.setNome(req.getParameter("nome"));
            usuario.setSobrenome(req.getParameter("sobrenome"));
            usuario.setTelefone(req.getParameter("telefone"));

            usuarioDAO.atualiza(usuario);
            
            Endereco endereco = usuario.getEndereco();
            
            endereco.setBairro(req.getParameter("bairro"));
            endereco.setCep(req.getParameter("cep"));
            endereco.setComplemento(req.getParameter("complemento"));
            endereco.setEstado(req.getParameter("estado"));
            endereco.setCidade(req.getParameter("cidade"));
            endereco.setNumero(NumeroUtil.parseInt(req.getParameter("numero")));
            endereco.setRua(req.getParameter("rua"));

            enderecoDAO.atualiza(endereco);

            connection.commit();

            req.setAttribute("success", "Usuário alterado com sucesso");
            
        } catch (SQLException | InfraestruturaException e) {
            Logger.getLogger(NovoClienteController.class.getName()).log(Level.SEVERE, null, e);

            if (connection != null) {
                connection.rollback();
            }

            req.setAttribute("exception", e);

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        req.getRequestDispatcher("/WEB-INF/altera-cadastro.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!resp.isCommitted()) {
            req.getRequestDispatcher("/WEB-INF/altera-cadastro.jsp").forward(req, resp);
        }
    }

}
