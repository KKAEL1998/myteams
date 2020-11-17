package br.com.myteams.model.pedido;

import br.com.myteams.model.produto.Produto;

import java.math.BigDecimal;

public class ProdutoPedido
{

    private Pedido pedido;

    private Produto produto;

    private int quantidade;

    private BigDecimal valorTotal;

    public ProdutoPedido()
    {
    }

    public ProdutoPedido(Pedido pedido, Produto produto, int quantidade, BigDecimal precoUnitario)
    {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }

    ProdutoPedido(Pedido pedido, Produto produto, Integer quantidade)
    {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = produto.getPreco().multiply(new BigDecimal(quantidade));
    }

    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal)
    {
        this.valorTotal = valorTotal;
    }

}
