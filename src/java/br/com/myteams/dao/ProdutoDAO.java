/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.dao;

import br.com.myteams.model.usuario.Produto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author welso
 */
public class ProdutoDAO extends DAO<Produto, String> {

    public ProdutoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(Produto entidade) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("INSERT INTO produto (id, nome, descricao, preco, imagem) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, entidade.getId());
        preparedStatement.setString(2, entidade.getNome());
        preparedStatement.setString(3, entidade.getDescricao());
        preparedStatement.setString(4, entidade.getPreco().toString());
        preparedStatement.setString(5, entidade.getImagem());

        preparedStatement.executeUpdate();

    }

    @Override
    public void remove(Produto entidade) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("delete from produto where id=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void atualiza(Produto entidade) throws SQLException {
        PreparedStatement preparedStatement = getConnection()
                .prepareStatement("update produto set nome=?, descricao=?, preco=?, imagem=?"
                        + "where id=?");
        // Parameters start with 1
        preparedStatement.setString(1, entidade.getNome());
        preparedStatement.setString(2, entidade.getDescricao());
        preparedStatement.setString(3, entidade.getPreco().toString());
        preparedStatement.setString(4, entidade.getImagem());
        preparedStatement.setString(5, entidade.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public Produto procuraPorId(String id) throws SQLException {
        Produto produto = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produto where id = ?");
        preparedStatement.setString(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
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
    public List<Produto> listaTodos() throws SQLException {
        List<Produto> produtos = new ArrayList();
        PreparedStatement preparedStatement = getConnection().prepareStatement("select * from produto");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
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

    public static Produto criaProduto(ResultSet resultSet) throws SQLException {
        Produto produto = new Produto();
        produto.setNome(resultSet.getString("nome"));
        produto.setDescricao(resultSet.getString("descricao"));
        produto.setId(resultSet.getString("id"));
        produto.setImagem(resultSet.getString("imagem"));
        produto.setPreco(new BigDecimal(resultSet.getString("preco")));
        return produto;

    }
}
