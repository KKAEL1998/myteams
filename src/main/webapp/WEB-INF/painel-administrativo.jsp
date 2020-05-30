<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="container">
    <h1>Painel Administrativo</h1>
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Cadastro</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/admin/novo-produto" class="btn btn-primary d-block"><i class="fas fa-plus"></i> Novo Produto</a>
                        </div>
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/admin/novo-admin" class="btn btn-primary d-block"><i class="fas fa-user-plus"></i> Novo Administrador</a>
                        </div>
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/admin/produtos" class="btn btn-primary d-block"><i class="fas fa-user-plus"></i> Alterar/Excluir Produtos</a>
                        </div>
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/admin/nova-categoria" class="btn btn-primary d-block"><i class="fas fa-user-plus"></i> Nova Categoria</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-5">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Pedidos pendentes</div>
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
                                                    <div class="col text-right">
                                                        <a href="${pageContext.request.contextPath}/admin/pedido/avanca/${pedido.id}" class="btn btn-primary">Avan�ar para ${pedido.proximaEtapa.descricao}</a>
                                                    </div>
                                                </div>
                                            </h2>
                                        </div>
                                        <div id="collapsePedido${pedido.id}" class="collapse" aria-labelledby="headingPedido${pedido.id}" data-parent="#accordionExample">
                                            <div class="card-body">
                                                <h3>Cliente</h3>
                                                <div class="row">
                                                    <div class="col-4">
                                                        <p><strong>Nome</strong></p>
                                                        <p>${pedido.usuario.nome} ${pedido.usuario.sobrenome}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>CPF</strong></p>
                                                        <p>${pedido.usuario.cpf}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>Telefone</strong></p>
                                                        <p>${pedido.usuario.telefone}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>E-mail</strong></p>
                                                        <p>${pedido.usuario.email}</p>
                                                    </div>

                                                </div>
                                                <h3>Endere�o de entrega</h3>
                                                <div class="row">
                                                    <div class="col-4">
                                                        <p><strong>Endere�o</strong></p>
                                                        <p>${pedido.usuario.endereco.rua}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>N�mero</strong></p>
                                                        <p>${pedido.usuario.endereco.numero}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>Complemento</strong></p>
                                                        <p>${pedido.usuario.endereco.complemento}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>CEP</strong></p>
                                                        <p>${pedido.usuario.endereco.cep}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>Cidade</strong></p>
                                                        <p>${pedido.usuario.endereco.cidade}</p>
                                                    </div>
                                                    <div class="col-4">
                                                        <p><strong>Estado</strong></p>
                                                        <p>${pedido.usuario.endereco.estado}</p>
                                                    </div>
                                                </div>
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
