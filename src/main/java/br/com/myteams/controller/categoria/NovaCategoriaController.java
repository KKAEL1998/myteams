package br.com.myteams.controller.categoria;

import br.com.myteams.dao.CategoriaDAO;
import br.com.myteams.model.categoria.Categoria;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/nova-categoria")
public class NovaCategoriaController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CustomConnection connection = null;
        try
        {
            connection = DBUtil.getConnection();

            Categoria categoria = new Categoria();
            categoria.setNome(req.getParameter("nome"));

            new CategoriaDAO(connection).insere(categoria);

            resp.sendRedirect(req.getContextPath() + "/home");
        }
        catch (SQLException ex)
        {

            req.setAttribute("exception", ex);

            req.getRequestDispatcher("/WEB-INF/nova-categoria.jsp").forward(req, resp);

        }
        finally
        {
            if (connection != null)
            {
                connection.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("/WEB-INF/nova-categoria.jsp").forward(req, resp);
    }
}
