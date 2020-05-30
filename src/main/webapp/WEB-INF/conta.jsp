<%@include file="header.jsp" %>
<div class="container">
    <h1>Minha Conta</h1>
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Cadastro</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/conta/alterar" class="btn btn-primary d-block"><i class="fas fa-user-edit"></i> Alterar Dados</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Pedidos</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="accordion" id="accordionExample">
                                <c:forEach items="${pedidos}" var="pedido">
                                    <div class="card">
                                        <div class="card-header" id="headingPedido${pedido.id}">
                                            <h2 class="mb-0">
                                                <div class="row">
                                                    <div class="col">
                                                        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapsePedido${pedido.id}" aria-expanded="false" aria-controls="collapsePedido${pedido.id}">
                                                            #${pedido.id} - ${pedido.status.descricao}
                                                        </button>
                                                    </div>
                                                </div>
                                            </h2>
                                        </div>
                                        <div id="collapsePedido${pedido.id}" class="collapse" aria-labelledby="headingPedido${pedido.id}" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <h3>Produtos</h3>
                                                <c:forEach var="produto" items="${pedido.produtos}">
                                                    <div class="media produto-carrinho">
                                                        <img src="${produto.produto.imagem}" class="mr-3">
                                                        <div class="media-body">
                                                            <div class="row">
                                                                <div class="col-6 centered">${produto.produto.nome}</div>
                                                                <div class="col-3 text-center centered">${produto.quantidade}</div>
                                                                <div class="col-3 centered text-right">R$ ${produto.valorTotal}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                                <h3>Valor Total: R$ ${pedido.valorTotal}</h3>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>