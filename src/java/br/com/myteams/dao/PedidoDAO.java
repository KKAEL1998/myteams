/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.myteams.dao;

import br.com.myteams.model.pedido.Pedido;
import br.com.myteams.model.pedido.StatusPedido;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author welso
 */
public class PedidoDAO extends DAO<Pedido, Long> {

    private static final String INSERT = "insert into pedido (id, usuario, valor_total, status) VALUES (?, ?, ?, ?)";

    private static final String SELECT_PEDIDOS_PENDENTES = "select pedido.id as pedido_id, usuario, valor_total, status, cpf, nome,"
            + " sobrenome, email, senha, telefone, data_nascimento, tipo, endereco, endereco.id as endereco_id, rua, numero, complemento,"
            + " cidade, estado, cep from pedido inner join usuario on pedido.usuario = usuario.cpf inner join endereco"
            + " on usuario.endereco = endereco.id where status != 5 order by pedido.id";
    
    private static final String SELECT_POR_ID = "select id as pedido_id, usuario, valor_total, status from pedido where id = ?";
    
    private static final String UPDATE_STATUS_PEDIDO = "update pedido set status = ? where id = ?";
    
    private static final String SELECT_PEDIDOS_USUARIO = "select id as pedido_id, usuario, valor_total, status from pedido where usuario = ?";

    public PedidoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(Pedido entidade) throws SQLException {
        entidade.setId(getProximoId());
        criaPreparedStatement(INSERT, entidade.getId(), entidade.getUsuario().getCpf(), entidade.getValorTotal(), entidade.getStatus().getValor()).execute();
    }

    @Override
    public void remove(Pedido entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualiza(Pedido entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido procuraPorId(Long id) throws SQLException {
        ResultSet resultSet = criaPreparedStatement(SELECT_POR_ID, id).executeQuery();
        if (resultSet.next()) {
            return criaPedido(resultSet);
        }
        return null;
    }

    @Override
    public List<Pedido> listaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Pedido> buscaPedidosPendentes() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        ResultSet resultSet = criaPreparedStatement(SELECT_PEDIDOS_PENDENTES).executeQuery();
        while (resultSet.next()) {
            Pedido pedido = criaPedido(resultSet);
            pedido.setUsuario(UsuarioDAO.criaUsuario(resultSet));
            pedido.getUsuario().setEndereco(EnderecoDAO.criaEndereco(resultSet));
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public static Pedido criaPedido(ResultSet resultSet) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(resultSet.getInt("pedido_id"));
        pedido.setValorTotal(new BigDecimal(resultSet.getString("valor_total")));
        pedido.setStatus(StatusPedido.get(resultSet.getInt("status")));
        return pedido;
    }

    public void atualizaStatus(Pedido pedido) throws SQLException {
        criaPreparedStatement(UPDATE_STATUS_PEDIDO, pedido.getStatus().getValor(), pedido.getId()).execute();
    }

    public List<Pedido> buscaPedidosPorCliente(String cpf) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        ResultSet resultSet = criaPreparedStatement(SELECT_PEDIDOS_USUARIO, cpf).executeQuery();
        while (resultSet.next()) {
            lista.add(criaPedido(resultSet));
        }
        return lista;
    }

}
