<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="container">
    <div class="card card-primary">
        <div class="card-header"><h4>Confirma��o do Pedido</h4></div>

        <div class="card-body">
            <fieldset>
                <legend>Endere�o de entrega</legend>
                <div><b>Rua:</b> ${sessionScope.usuario.endereco.rua}</div>
                <div><b>Numero:</b> ${sessionScope.usuario.endereco.numero}</div>
                <div><b>Complemento:</b> ${sessionScope.usuario.endereco.complemento}</div>
                <div><b>Cidade:</b> ${sessionScope.usuario.endereco.cidade}</div>
                <div><b>Estado:</b> ${sessionScope.usuario.endereco.estado}</div>
                <div><b>CEP:</b> ${sessionScope.usuario.endereco.cep} </div>
            </fieldset>
            <fieldset>
                <legend>Produtos</legend>
                <div class="media produto-carrinho">
                    <div class="espaco-img"></div>
                    <div class="media-body">
                        <div class="row font-weight-bold">
                            <div class="col-6 text-center">Produto</div>
                            <div class="col-3 text-center">Quantidade</div>
                            <div class="col-3 text-center">Pre�o unitario</div>
                        </div>
                    </div>
                </div>
                <c:forEach items="${sessionScope.carrinho.produtos}" var="produto">
                    <div class="media produto-carrinho">
                        <img src="${produto.key.imagem}" class="mr-3">
                        <div class="media-body">
                            <div class="row">
                                <div class="col-6 centered">${produto.key.nome}</div>
                                <div class="col-3 text-center centered">
                                    <div class="row">
                                        <div class="col-12 text-center">${produto.value}</div>
                                    </div>
                                </div>
                                <div class="col-3 centered text-right">R$ ${produto.key.preco}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="row">
                    <div class="col-12 text-right">
                        <h4>Valor total: R$ ${sessionScope.carrinho.valorTotal}</h4>
                    </div>
                </div>
            </fieldset>
            <fieldset>
                <legend>Dados de pagamento</legend>
                <form action="${pageContext.request.contextPath}/pedido" method="post">
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="nome">Numero do cart�o</label>
                            <input id="nome" type="text" class="form-control" name="numero_cartao" autofocus required>
                        </div>

                        <div class="form-group col-6">
                            <label for="nome">Nome no cart�o</label>
                            <input id="nome" type="text" class="form-control" name="nome_cartao" required>
                        </div>


                        <div class="form-group col-6">
                            <label for="nome">C�digo de seguranca</label>
                            <input id="nome" type="text" class="form-control" name="codigo_seguranca" required>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary">Finalizar Compra</button>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
