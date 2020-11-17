package br.com.myteams.dao;

import br.com.myteams.model.categoria.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends DAO<Categoria, Long>
{
    public CategoriaDAO(Connection connection)
    {
        super(connection);
    }

    @Override
    public void insere(Categoria entidade) throws SQLException
    {
        Long proximoId = getProximoId();
        PreparedStatement preparedStatement = criaPreparedStatement("INSERT INTO categoria (id, nome) values (?, ?)", proximoId, entidade.getNome());
        preparedStatement.execute();
        entidade.setId(proximoId);
    }

    @Override
    public void remove(Categoria entidade) throws SQLException
    {

    }

    @Override
    public void atualiza(Categoria entidade) throws SQLException
    {

    }

    @Override
    public Categoria procuraPorId(Long aLong) throws SQLException
    {
        PreparedStatement preparedStatement = criaPreparedStatement("select c.id, c.nome from categoria c where c.id = ?");
        preparedStatement.setLong(1, aLong);
        ResultSet resultSet = preparedStatement.executeQuery();
        Categoria categoria = null;
        if (resultSet.next()) {
            categoria = criaCategoria(resultSet);
        }
        return categoria;
    }

    @Override
    public List<Categoria> listaTodos() throws SQLException
    {
        PreparedStatement preparedStatement = criaPreparedStatement("select c.id, c.nome from categoria c");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Categoria> categorias = new ArrayList<>();
        while (resultSet.next()) {
            categorias.add(criaCategoria(resultSet));
        }
        return categorias;
    }

    public List<Categoria> listaTodosQuePossuiProdutosAssociadosOrdenada() throws SQLException
    {
        PreparedStatement preparedStatement = criaPreparedStatement("select distinct c.id, c.nome from categoria c inner join produto p on p.categoria_id = c.id order by c.nome");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Categoria> categorias = new ArrayList<>();
        while (resultSet.next()) {
            categorias.add(criaCategoria(resultSet));
        }
        return categorias;
    }

    private Categoria criaCategoria(ResultSet resultSet) throws SQLException
    {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("id"));
        categoria.setNome(resultSet.getString("nome"));
        return categoria;
    }
}
