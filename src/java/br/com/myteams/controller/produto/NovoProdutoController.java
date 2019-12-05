/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.controller.produto;

import br.com.myteams.dao.ProdutoDAO;
import br.com.myteams.model.usuario.Produto;
import br.com.myteams.util.CustomConnection;
import br.com.myteams.util.DBUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/novo-produto")
public class NovoProdutoController extends HttpServlet {

    /**
     * Adiciona um novo produto ao sistema
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomConnection connection = null;
        try {
            connection = DBUtil.getConnection();

            Produto produto = new Produto();

            produto.setNome(req.getParameter("nome"));
            produto.setId(toURL(produto.getNome()));
            produto.setDescricao(req.getParameter("descricao"));
            produto.setImagem(req.getParameter("imagem"));
            produto.setPreco(new BigDecimal(req.getParameter("preco")));

            new ProdutoDAO(connection).insere(produto);

            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (SQLException ex) {

            req.setAttribute("exception", ex);

            req.getRequestDispatcher("/WEB-INF/novo-produto.jsp").forward(req, resp);

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Verifica se posssui uma rota ja definida, caso contrario redireciona para a pagina de adição de produto
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!resp.isCommitted()) {
            req.getRequestDispatcher("/WEB-INF/novo-produto.jsp").forward(req, resp);
        }
    }

    /**
     * Deixa todas as letras minusculas e subtitui todos os espaços por hifem
     * @param value
     * @return 
     */
    private String toURL(String value) {
        return value.toLowerCase().replaceAll(" ", "-");
    }

}
