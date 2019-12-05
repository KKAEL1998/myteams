package br.com.myteams.controller.usuario;

import br.com.myteams.dao.EnderecoDAO;
import br.com.myteams.dao.UsuarioDAO;
import br.com.myteams.exception.InfraestruturaException;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.endereco.Endereco;
import br.com.myteams.model.usuario.TipoUsuario;
import br.com.myteams.model.usuario.Usuario;
import br.com.myteams.util.CriptografiaUtil;
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

@WebServlet("/novo-usuario")
public class NovoClienteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomConnection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

            Endereco endereco = new Endereco();

            endereco.setBairro(req.getParameter("bairro"));
            endereco.setCep(req.getParameter("cep"));
            endereco.setComplemento(req.getParameter("complemento"));
            endereco.setEstado(req.getParameter("estado"));
            endereco.setCidade(req.getParameter("cidade"));
            endereco.setNumero(NumeroUtil.parseInt(req.getParameter("numero")));
            endereco.setRua(req.getParameter("rua"));

            enderecoDAO.insere(endereco);

            Usuario usuario = new Usuario();
            usuario.setEndereco(endereco);
            usuario.setCpf(req.getParameter("cpf"));
            usuario.setDataNascimento(DataUtil.toDate(req.getParameter("dataNascimento")));
            usuario.setEmail(req.getParameter("email"));
            usuario.setNome(req.getParameter("nome"));
            usuario.setSobrenome(req.getParameter("sobrenome"));
            usuario.setTelefone(req.getParameter("telefone"));
            usuario.setSenha(CriptografiaUtil.criptografa(usuario.getEmail() + req.getParameter("senha")));
            usuario.setTipoUsuario(TipoUsuario.CLIENTE);

            try {
                usuarioDAO.insere(usuario);
            } catch (SQLException e) {
                if (e.getMessage().contains("duplicate key value violates unique constraint")) {
                    if (e.getMessage().contains("(cpf)")) {
                        throw new NegocioException("CPF já cadastrado, por favor, realize o login");
                    }
                    if (e.getMessage().contains("(email)")) {
                        throw new NegocioException("E-mail já cadastrado, por favor, realize o login");
                    }
                }
                throw e;
            }

            connection.commit();

            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (SQLException | InfraestruturaException | IOException | NegocioException e) {
            Logger.getLogger(NovoClienteController.class.getName()).log(Level.SEVERE, null, e);

            if (connection != null) {
                connection.rollback();
            }

            req.setAttribute("exception", e);

            req.getRequestDispatcher("/WEB-INF/cadastro.jsp").forward(req, resp);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/cadastro.jsp").forward(req, resp);
    }

}
