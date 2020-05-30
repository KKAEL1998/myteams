package br.com.myteams.controller.produto;

import br.com.myteams.dao.CategoriaDAO;
import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.categoria.Categoria;
import br.com.myteams.model.produto.Produto;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/admin/produtos/editar/*")
public class AlteraProdutoController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        CustomConnection connection = null;
        try
        {
            connection = DBUtil.getConnection();

            Produto produto = new Produto();

            produto.setNome(req.getParameter("nome"));
            produto.setId(req.getParameter("id"));
            produto.setDescricao(req.getParameter("descricao"));
            produto.setImagem(req.getParameter("imagem"));
            produto.setPreco(new BigDecimal(req.getParameter("preco")));
            produto.setCategoria(new Categoria(Long.parseLong(req.getParameter("categoria"))));

            new ProdutoDAO(connection).atualiza(produto);

            resp.sendRedirect(req.getContextPath() + "/admin/produtos");
        }
        catch (SQLException ex)
        {

            req.setAttribute("exception", ex);

            req.getRequestDispatcher("/WEB-INF/novo-produto.jsp").forward(req, resp);

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
        if (!resp.isCommitted())
        {
            try
            {
                Produto produto = new ProdutoDAO(DBUtil.getConnection()).procuraPorId(req.getPathInfo().substring(1));

                if (produto == null)
                {
                    throw new NegocioException("Esse produto não existe");
                }

                req.setAttribute("produto", produto);
                req.setAttribute("categorias", new CategoriaDAO(DBUtil.getConnection()).listaTodos());
            }
            catch (SQLException | NegocioException ex)
            {
                req.setAttribute("exception", ex);
                Logger.getLogger(AlteraProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.getRequestDispatcher("/WEB-INF/novo-produto.jsp").forward(req, resp);
        }
    }

}
