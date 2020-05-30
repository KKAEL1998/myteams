package br.com.myteams.controller.produto;

import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.model.produto.Produto;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/produto/*")
public class DetalheProdutoController extends HttpServlet
{

    /**
     * Procura o produto e aprensenta ao usuario
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CustomConnection connection = null;

        try
        {
            connection = DBUtil.getConnection();

            ProdutoDAO produtoDAO = new ProdutoDAO(connection);

            Produto produto = produtoDAO.procuraPorId(req.getPathInfo().substring(1));

            if (produto != null)
            {
                req.setAttribute("produto", produto);
                req.setAttribute("title", produto.getNome() + " - MyTeams");
                req.getRequestDispatcher("/WEB-INF/detalhe-produto.jsp").forward(req, resp);
            }
            else
            {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        catch (SQLException ex)
        {
            req.setAttribute("exception", ex);
        }
        finally
        {
            if (connection != null)
            {
                connection.close();
            }
        }
    }

}
