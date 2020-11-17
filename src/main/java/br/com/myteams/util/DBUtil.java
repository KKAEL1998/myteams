package br.com.myteams.util;

import br.com.myteams.exception.InfraestruturaException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil
{

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "postgres";

    private static final String URL = "jdbc:postgresql://localhost:5432/loja";

    public static CustomConnection getConnection()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            return new CustomConnection(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new InfraestruturaException("Erro ao fazer conexão com o banco de dados", ex);
        }
    }
}
