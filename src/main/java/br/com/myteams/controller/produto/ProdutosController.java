package br.com.myteams.controller.produto;

import br.com.myteams.dao.CategoriaDAO;
import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/produtos")
public class ProdutosController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (!resp.isCommitted())
        {
            try
            {
                CustomConnection connection = DBUtil.getConnection();
                req.setAttribute("produtos", new ProdutoDAO(connection).listaTodos());
            }
            catch (SQLException ex)
            {
                req.setAttribute("exception", ex);
            }
            req.getRequestDispatcher("/WEB-INF/edicao-produtos.jsp").forward(req, resp);
        }
    }

}
