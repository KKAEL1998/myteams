package br.com.myteams.dao;

import br.com.myteams.util.DataUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class DAO<T, ID>
{

    private final Connection connection;

    public DAO(Connection connection)
    {
        this.connection = connection;
    }

    protected Connection getConnection()
    {
        return connection;
    }

    protected PreparedStatement criaPreparedStatement(String sql, Object... objects) throws SQLException
    {
        PreparedStatement prepareStatement = connection.prepareStatement(sql);

        for (int i = 0; i < objects.length; i++)
        {
            if (objects[i] instanceof Date)
            {
                prepareStatement.setDate(i + 1, DataUtil.toSQLDate((Date) objects[i]));
            }
            else
            {
                prepareStatement.setObject(i + 1, objects[i]);
            }
        }

        return prepareStatement;
    }

    protected Long getProximoId() throws SQLException
    {
        ResultSet resultSet = criaPreparedStatement("SELECT nextval('loja_seq') as seq").executeQuery();
        if (resultSet.next())
        {
            return resultSet.getLong("seq");
        }
        return null;
    }

    public abstract void insere(T entidade) throws SQLException;

    public abstract void remove(T entidade) throws SQLException;

    public abstract void atualiza(T entidade) throws SQLException;

    public abstract T procuraPorId(ID id) throws SQLException;

    public abstract List<T> listaTodos() throws SQLException;
}
