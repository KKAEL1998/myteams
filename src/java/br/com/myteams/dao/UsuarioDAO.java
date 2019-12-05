package br.com.myteams.dao;

import br.com.myteams.model.usuario.TipoUsuario;
import br.com.myteams.model.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO extends DAO<Usuario, String> {

    private static final String INSERT = "INSERT INTO usuario (cpf, nome, sobrenome, email, senha, telefone, data_nascimento, tipo, endereco)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String BUSCA_EMAIL_E_SENHA = "select u.cpf, u.nome, u.sobrenome, u.email, u.senha, u.telefone, u.data_nascimento, "
            + "u.tipo, e.id as endereco_id, e.rua, e.numero, e.complemento, e.cidade, e.estado, e.cep from usuario u inner join endereco e "
            + "on u.endereco = e.id where u.email = ? and u.senha = ?";
    
    private static final String SELECT_POR_CPF = "select cpf, nome, sobrenome, email, senha, telefone, data_nascimento, tipo, endereco"
            + " from usuario where cpf = ?";
    
    private static final String UPDATE_TIPO = "update usuario set tipo = ? where cpf = ?";
    
    private static final String UPDATE_USUARIO = "update usuario set data_nascimento=?, nome=?, sobrenome=?, telefone=? where cpf=?";

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insere(Usuario entidade) throws SQLException {
        PreparedStatement preparedStatement = criaPreparedStatement(INSERT, entidade.getCpf(), entidade.getNome(), entidade.getSobrenome(),
                entidade.getEmail(), entidade.getSenha(), entidade.getTelefone(), entidade.getDataNascimento(), entidade.getTipoUsuario().getValor(),
                entidade.getEndereco().getId());
        preparedStatement.execute();
    }

    public Usuario buscaPorEmailESenha(String email, String senha) throws SQLException {
        PreparedStatement preparedStatement = criaPreparedStatement(BUSCA_EMAIL_E_SENHA, email, senha);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Usuario usuario = criaUsuario(resultSet);
            usuario.setEndereco(EnderecoDAO.criaEndereco(resultSet));
            return usuario;
        }
        return null;
    }

    public static Usuario criaUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setCpf(resultSet.getString("cpf"));
        usuario.setDataNascimento(resultSet.getDate("data_nascimento"));
        usuario.setEmail(resultSet.getString("email"));
        usuario.setNome(resultSet.getString("nome"));
        usuario.setSobrenome(resultSet.getString("sobrenome"));
        usuario.setTelefone(resultSet.getString("telefone"));
        usuario.setTipoUsuario(TipoUsuario.get(resultSet.getInt("tipo")));
        return usuario;
    }

    @Override
    public void remove(Usuario entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualiza(Usuario entidade) throws SQLException {
        criaPreparedStatement(UPDATE_USUARIO, entidade.getDataNascimento(), entidade.getNome(), entidade.getSobrenome(),
                entidade.getTelefone(), entidade.getCpf()).execute();
    }
    

    @Override
    public Usuario procuraPorId(String id) throws SQLException {
        ResultSet resultSet = criaPreparedStatement(SELECT_POR_CPF, id).executeQuery();
        if (resultSet.next()) {
            return criaUsuario(resultSet);
        }
        return null;
    }

    @Override
    public List<Usuario> listaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void atualizaTipo(Usuario usuario) throws SQLException {
        criaPreparedStatement(UPDATE_TIPO, usuario.getTipoUsuario().getValor(), usuario.getCpf()).execute();
    }

}
