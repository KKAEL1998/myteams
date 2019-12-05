package br.com.myteams.controller.carrinho;

import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.carrinho.Carrinho;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/carrinho/adiciona/*")
public class AdicionaProdutoCarrinhoController extends HttpServlet {
    
    /**
     * Cria um carrinho na sessão do usuario caso não exista, adiciona o produto e redireciona para o carrinho.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Carrinho carrinho = recuperaCarrinho(req);

        try (Connection connection = DBUtil.getConnection()) {
            carrinho.adicionaProduto(new ProdutoDAO(connection).procuraPorId(req.getPathInfo().substring(1)));
            resp.sendRedirect(req.getContextPath() + "/carrinho");
        } catch (SQLException | NegocioException ex) {
            Logger.getLogger(AdicionaProdutoCarrinhoController.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect(req.getContextPath());
        }

    }

    /**
     * Metodo que recupera carrinho ou cria um na sessão
     * @param req
     * @return 
     */
    private Carrinho recuperaCarrinho(HttpServletRequest req) {
        Carrinho carrinho = (Carrinho) req.getSession().getAttribute("carrinho");
        if (carrinho == null) {
            carrinho = new Carrinho();
            req.getSession().setAttribute("carrinho", carrinho);
        }
        return carrinho;
    }

}
