package br.com.myteams.controller.carrinho;

import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.carrinho.Carrinho;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/carrinho/remove/*")
public class RemoveProdutoCarrinhoController extends HttpServlet
{

    /**
     * Recupera o carrinho da sessão e remove o produto
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Carrinho carrinho = (Carrinho) req.getSession().getAttribute("carrinho");

        if (carrinho != null)
        {
            try (Connection connection = DBUtil.getConnection())
            {
                carrinho.removeProduto(new ProdutoDAO(connection).procuraPorId(req.getPathInfo().substring(1)));
                if (carrinho.getProdutos().isEmpty())
                {
                    req.getSession().removeAttribute("carrinho");
                }
            }
            catch (NegocioException | SQLException ex)
            {
                Logger.getLogger(RemoveProdutoCarrinhoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/carrinho");
    }

}
