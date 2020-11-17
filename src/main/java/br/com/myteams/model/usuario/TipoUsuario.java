package br.com.myteams.model.usuario;

public enum TipoUsuario
{

    CLIENTE(1), ADMINISTRADOR(2);

    private final int valor;

    TipoUsuario(int valor)
    {
        this.valor = valor;
    }

    public static TipoUsuario get(int valor)
    {
        for (TipoUsuario tipoUsuario : TipoUsuario.values())
        {
            if (tipoUsuario.getValor() == valor)
            {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Valor Invalido para " + TipoUsuario.class.getName());
    }

    public int getValor()
    {
        return valor;
    }
}
