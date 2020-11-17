/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.dao;

import br.com.myteams.model.categoria.Categoria;
import br.com.myteams.model.produto.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author welso
 */
public class ProdutoDAO extends DAO<Produto, String>
{

    public ProdutoDAO(Connection connection)
    {
        super(connection);
    }

    public static Produto criaProduto(ResultSet resultSet) throws SQLException
    {
        Produto produto = new Produto();
        produto.setNome(resultSet.getString("nome"));
        produto.setDescricao(resultSet.getString("descricao"));
        produto.setId(resultSet.getString("id"));
        produto.setImagem(resultSet.getString("imagem"));
        produto.setPreco(new BigDecimal(resultSet.getString("preco")));
        return produto;

    }

    @Override
    public void insere(Produto entidade) throws SQLException
    {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("INSERT INTO produto (id, nome, descricao, preco, imagem, categoria_id) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, entidade.getId());
        preparedStatement.setString(2, entidade.getNome());
        preparedStatement.setString(3, entidade.getDescricao());
        preparedStatement.setString(4, entidade.getPreco().toString());
        preparedStatement.setString(5, entidade.getImagem());
        preparedStatement.setLong(6, entidade.getCategoria().getId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void remove(Produto entidade) throws SQLException
    {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("delete from produto where id=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void atualiza(Produto entidade) throws SQLException
    {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("update produto set nome=?, descricao=?, preco=?, imagem=?, categoria_id=?"
                        + "where id=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getNome());
        preparedStatement.setString(2, entidade.getDescricao());
        preparedStatement.setString(3, entidade.getPreco().toString());
        preparedStatement.setString(4, entidade.getImagem());
        preparedStatement.setLong(5, entidade.getCategoria().getId());
        preparedStatement.setString(6, entidade.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public Produto procuraPorId(String id) throws SQLException
    {
        Produto produto = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produto where id = ?");
        preparedStatement.setString(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next())
        {
            produto = new Produto();
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setImagem(rs.getString("imagem"));
            produto.setPreco(new BigDecimal(rs.getString("preco")));
            produto.setId(rs.getString("id"));
        }
        return produto;
    }

    @Override
    public List<Produto> listaTodos() throws SQLException
    {
        List<Produto> produtos = new ArrayList();
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produto");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
        {
            Produto produto = new Produto();
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setId(rs.getString("id"));
            produto.setImagem(rs.getString("imagem"));
            produto.setPreco(new BigDecimal(rs.getString("preco")));
            produtos.add(produto);
        }
        return produtos;
    }

    public List<Produto> listaTodosComFiltro(Categoria categoria) throws SQLException
    {
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from produto where 1 = 1";
        if (categoria != null) {
            sql = sql + " AND categoria_id = ?";
        }
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

        if (sql.contains("categoria_id")) {
            preparedStatement.setLong(1, categoria.getId());
        }

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
        {
            Produto produto = new Produto();
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setId(rs.getString("id"));
            produto.setImagem(rs.getString("imagem"));
            produto.setPreco(new BigDecimal(rs.getString("preco")));
            produtos.add(produto);
        }
        return produtos;
    }

    public Produto buscaProdutoMaisVendido() throws SQLException
    {
        PreparedStatement preparedStatement = criaPreparedStatement("select count(p) total, p.id, p.nome, p.descricao, p.preco, p.imagem, p.categoria_id from produto p inner join produto_pedido pp on p.id = pp.produto group by (p.id) order by total desc limit 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return criaProduto(resultSet);
        }
        return null;
    }
}
