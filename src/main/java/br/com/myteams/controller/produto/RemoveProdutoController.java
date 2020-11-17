package br.com.myteams.controller.produto;

import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.model.produto.Produto;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/produtos/remover/*")
public class RemoveProdutoController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            Produto produto = new Produto();
            produto.setId(req.getPathInfo().substring(1));
            new ProdutoDAO(DBUtil.getConnection()).remove(produto);
            resp.sendRedirect(req.getContextPath() + "/admin/produtos");
        }
        catch (SQLException ex)
        {
            resp.sendRedirect(req.getContextPath() + "/admin/produtos?erro=fk");
        }

    }

}
