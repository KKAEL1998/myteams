package br.com.myteams.model.carrinho;

import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.produto.Produto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho
{

    private final Map<Produto, Integer> produtos;

    private BigDecimal valorTotal;

    public Carrinho()
    {
        produtos = new HashMap<>();
        valorTotal = BigDecimal.ZERO;
    }

    /**
     * Metodo para adicionar um produto ao carrinho, caso ainda não tenha, apenas coloca, caso já tenha incrementa a quantidade
     *
     * @param produto Produto a ser adicionado
     * @throws NegocioException Caso passe um valor nulo
     */
    public void adicionaProduto(Produto produto) throws NegocioException
    {
        if (produto == null)
        {
            throw new NegocioException("Produto invalido");
        }

        if (produtos.containsKey(produto))
        {
            produtos.put(produto, produtos.get(produto) + 1);
        }
        else
        {
            produtos.put(produto, 1);
        }

        valorTotal = valorTotal.add(produto.getPreco());
    }

    /**
     * Metodo para remover um produto do carrinho, caso tenha apenas um produto, ele remove, caso contrario decrementa a quantidade
     *
     * @param produto Produto a ser removido
     * @throws NegocioException Caso passe um valor null
     */
    public void removeProduto(Produto produto) throws NegocioException
    {
        if (produto == null)
        {
            throw new NegocioException("Produto invalido");
        }

        if (produtos.containsKey(produto))
        {
            Integer quantidade = produtos.get(produto) - 1;

            if (quantidade == 0)
            {
                produtos.remove(produto);
            }
            else
            {
                produtos.put(produto, quantidade);
            }

            valorTotal = valorTotal.subtract(produto.getPreco());
        }
    }

    public Map<Produto, Integer> getProdutos()
    {
        return produtos;
    }

    public BigDecimal getValorTotal()
    {
        return valorTotal;
    }

    public int getQuantidadeItens()
    {
        return produtos.size();
    }

}
