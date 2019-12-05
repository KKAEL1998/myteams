/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.dao;

import br.com.myteams.model.pedido.ProdutoPedido;
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
public class ProdutoPedidoDAO extends DAO<ProdutoPedido, Object> {

    private static final String INSERT = "insert into produto_pedido (pedido, produto, quantidade, valorTotal) VALUES (?, ?, ?, ?)";

    private static final String SELECT_POR_PEDIDO = "select pedido, produto, quantidade, valortotal, id, nome, descricao, preco, imagem"
            + " from produto_pedido inner join produto on produto_pedido.produto = produto.id where pedido = ?;";

    public ProdutoPedidoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(ProdutoPedido entidade) throws SQLException {
        criaPreparedStatement(INSERT, entidade.getPedido().getId(), entidade.getProduto().getId(), entidade.getQuantidade(), entidade.getValorTotal()).execute();
    }

    public void insereVarios(List<ProdutoPedido> produtoPedidoList) throws SQLException {
        for (ProdutoPedido produtoPedido : produtoPedidoList) {
            insere(produtoPedido);
        }
    }

    @Override
    public void remove(ProdutoPedido entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualiza(ProdutoPedido entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProdutoPedido procuraPorId(Object id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProdutoPedido> listaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ProdutoPedido> buscaPorPedido(long id) throws SQLException {
        List<ProdutoPedido> produtoPedidos = new ArrayList<>();
        ResultSet resultSet = criaPreparedStatement(SELECT_POR_PEDIDO, id).executeQuery();
        while (resultSet.next()) {
            ProdutoPedido produtoPedido = criaProdutoPedido(resultSet);
            produtoPedido.setProduto(ProdutoDAO.criaProduto(resultSet));
            produtoPedidos.add(produtoPedido);
        }
        return produtoPedidos;
    }

    private ProdutoPedido criaProdutoPedido(ResultSet resultSet) throws SQLException {
        ProdutoPedido produtoPedido = new ProdutoPedido();
        produtoPedido.setQuantidade(resultSet.getInt("quantidade"));
        produtoPedido.setValorTotal(new BigDecimal(resultSet.getString("valortotal")));
        return produtoPedido;
    }

}
