package br.com.myteams.dao;

import br.com.myteams.model.endereco.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAO extends DAO<Endereco, Long> {
    
    private static final String INSERT = "INSERT INTO endereco (id, rua, numero, complemento, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE_ENDERECO = "update endereco set cep=?, complemento=?, estado=?, cidade=?, numero=?, rua=? where id=?";

    public EnderecoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(Endereco entidade) throws SQLException {
        entidade.setId(getProximoId());
        PreparedStatement preparedStatement = criaPreparedStatement(INSERT, entidade.getId(), entidade.getRua(), entidade.getNumero(),
                entidade.getComplemento(), entidade.getCidade(), entidade.getEstado(), entidade.getCep());
        preparedStatement.execute();
    }

    @Override
    public void remove(Endereco entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualiza(Endereco entidade) throws SQLException {
        criaPreparedStatement(UPDATE_ENDERECO, entidade.getCep(), entidade.getComplemento(), entidade.getEstado(), entidade.getCidade(),
                entidade.getNumero(), entidade.getRua(), entidade.getId()).execute();
    }
    
    @Override
    public Endereco procuraPorId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> listaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Endereco criaEndereco(ResultSet resultSet) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(resultSet.getLong("endereco_id"));
        endereco.setRua(resultSet.getString("rua"));
        endereco.setNumero(resultSet.getInt("numero"));
        endereco.setComplemento(resultSet.getString("complemento"));
        endereco.setCidade(resultSet.getString("cidade"));
        endereco.setEstado(resultSet.getString("estado"));
        endereco.setCep(resultSet.getString("cep"));
        return endereco;
    }
    
}
