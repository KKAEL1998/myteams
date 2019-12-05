<%@page import="java.util.List"%>
<%@page import="br.com.myteams.model.usuario.Produto"%>
<%@include file="header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="row">
        <div class="col-12"><h1>Carrinho</h1></div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Produtos</div>
                <div class="card-body">
                    <c:if test="${sessionScope.carrinho == null || sessionScope.carrinho.produtos.size == 0}">Não possui produtos no carrinho</c:if>
                    <c:if test="${sessionScope.carrinho != null}">
                        <div class="media produto-carrinho">
                            <div class="espaco-img"></div>
                            <div class="media-body">
                                <div class="row font-weight-bold">
                                    <div class="col-6 text-center">Produto</div>
                                    <div class="col-3 text-center">Quantidade</div>
                                    <div class="col-3 text-center">Preço unitario</div>
                                </div>
                            </div>
                        </div>
                        <c:forEach items="${sessionScope.carrinho.produtos}" var="produto">
                            <div class="media produto-carrinho">
                                <a href="${pageContext.request.contextPath}/produto/${produto.key.id}"><img src="${produto.key.imagem}" class="mr-3"></a>
                                <div class="media-body">
                                    <div class="row">
                                        <div class="col-6 centered">${produto.key.nome}</div>
                                        <div class="col-3 text-center">
                                            <div class="row text-center">
                                                <div class="col-12"><a href="${pageContext.request.contextPath}/carrinho/adiciona/${produto.key.id}" class="btn btn-secondary"><i class="fas fa-chevron-up"></i></a></div>
                                            </div>
                                            <div class="row">
                                                <div class="col-12 text-center">${produto.value}</div>
                                            </div>
                                            <div class="row text-center">
                                                <div class="col-12"><a href="${pageContext.request.contextPath}/carrinho/remove/${produto.key.id}" class="btn btn-secondary"><i class="fas fa-chevron-down"></i></a></div>
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
                        <div class="row">
                            <div class="col-12 text-right">
                                <a href="${pageContext.request.contextPath}/pedido" class="btn btn-primary">Finalizar compra</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>