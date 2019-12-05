package br.com.myteams.controller.pedido;

import br.com.myteams.dao.PedidoDAO;
import br.com.myteams.dao.ProdutoPedidoDAO;
import br.com.myteams.model.carrinho.Carrinho;
import br.com.myteams.model.pedido.Pedido;
import br.com.myteams.model.usuario.Usuario;
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

@WebServlet("/pedido")
public class FinalizaPedidoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!redirecionaSeNaoTemUsuarioOuCarrinho(req, resp)) {
            CustomConnection connection = DBUtil.getConnection();
            try {
                Pedido pedido = new Pedido(getUsuario(req), getCarrinho(req).getProdutos());
                connection.setAutoCommit(false);
                new PedidoDAO(connection).insere(pedido);
                new ProdutoPedidoDAO(connection).insereVarios(pedido.getProdutos());
                connection.commit();
                
                req.getSession().removeAttribute("carrinho");
                
                resp.sendRedirect(req.getContextPath() + "/pedido-realizado");
            } catch (SQLException ex) {
                connection.rollback();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!redirecionaSeNaoTemUsuarioOuCarrinho(req, resp)) {
            req.getRequestDispatcher("/WEB-INF/confirma-pedido.jsp").forward(req, resp);
        }
    }

    private boolean redirecionaSeNaoTemUsuarioOuCarrinho(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Usuario usuario = getUsuario(req);
        Carrinho carrinho = getCarrinho(req);
        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/login/" + new String(Base64.getEncoder().encode((req.getContextPath() + "/pedido").getBytes())));
            return true;
        } else if (carrinho == null || carrinho.getQuantidadeItens() == 0) {
            resp.sendRedirect(req.getContextPath() + "/carrinho");
            return true;
        }
        
        return false;
    }

    private Usuario getUsuario(HttpServletRequest req) {
        return (Usuario) req.getSession().getAttribute("usuario");
    }

    private Carrinho getCarrinho(HttpServletRequest req) {
        return (Carrinho) req.getSession().getAttribute("carrinho");
    }

}
