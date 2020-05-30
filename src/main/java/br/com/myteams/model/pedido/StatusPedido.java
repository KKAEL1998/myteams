package br.com.myteams.model.pedido;

public enum StatusPedido
{

    SOLICITADO(1, "Solicitado"),
    PAGAMENTO_REALIZADO(2, "Pagamento Realizado"),
    EM_SEPARACAO(3, "Em Separação"),
    NOTA_FISCAL_EMITIDA(4, "Nota Fical Emitida"),
    ENVIADO(5, "Enviado");

    private final int valor;

    private final String descricao;

    StatusPedido(int valor, String descricao)
    {
        this.valor = valor;
        this.descricao = descricao;
    }

    public static StatusPedido get(int value)
    {
        for (StatusPedido statusPedido : StatusPedido.values())
        {
            if (statusPedido.getValor() == value)
            {
                return statusPedido;
            }
        }
        throw new IllegalArgumentException("Valor invalido para " + StatusPedido.class.getName());
    }

    public int getValor()
    {
        return valor;
    }

    public String getDescricao()
    {
        return descricao;
    }

}
