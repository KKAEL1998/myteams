package br.com.myteams.model.pedido;

import br.com.myteams.exception.NegocioException;
import br.com.myteams.model.usuario.Produto;
import br.com.myteams.model.usuario.Usuario;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pedido {
    
    private long id;
    private Usuario usuario;
    private List<ProdutoPedido> produtos;
    private BigDecimal valorTotal;
    private StatusPedido status;

    public Pedido() {
    }

    public Pedido(Usuario usuario, Map<Produto, Integer> produtos) {
        this.usuario = usuario;
        this.produtos = criaListaProdutos(produtos);
        this.valorTotal = this.produtos.stream().map(ProdutoPedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.status = StatusPedido.SOLICITADO;
    }
    
    public StatusPedido getProximaEtapa() {
        if (status.getValor() == 5) {
            return null;
        }
        return StatusPedido.get(status.getValor() + 1);
    }
    
    public void avancaEtapa() throws NegocioException {
        StatusPedido status = getProximaEtapa();
        if (status == null) {
            throw new NegocioException("O pedido já completou todas etapas");
        }
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ProdutoPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoPedido> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    private List<ProdutoPedido> criaListaProdutos(Map<Produto, Integer> produtos) {
        return produtos.entrySet().stream().map((produto) -> new ProdutoPedido(this, produto.getKey(), produto.getValue()))
                .collect(Collectors.toList());
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    
    
    
}
