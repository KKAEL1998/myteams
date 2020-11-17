<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">


    <div class="row">
        <div class="col-12 col-sm-3">
            <div class="card bg-info mb-3">
                <div class="card-header bg-info text-white"><i class="fa fa-list"></i> Categorias</div>
                <ul class="list-group category_block">
                    <c:forEach var="categoria" items="${categorias}">
                        <li class="list-group-item"><a href="?categoria=${categoria.id}">${categoria.nome}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <c:if test="${produtoMaisVendido != null}">
                <div class="card bg-light mb-3">
                    <div class="card-header bg-secondary text-white text-uppercase">Mais popular</div>
                    <a href="${pageContext.request.contextPath}/produto/${produtoMaisVendido.id}">
                        <div class="card-body">
                            <img class="img-fluid" src="${produtoMaisVendido.imagem}"/>
                            <h5 class="card-title">${produtoMaisVendido.nome}</h5>
                            <p class="item-price"><span>R$${produtoMaisVendido.preco}</span></p>
                        </div>
                    </a>
                </div>
            </c:if>
        </div>
        <div class="col-12 col-sm-9">
            <div class="row">
                <c:forEach var="produto" items="${produtos}">
                    <div class="col-md-4 col-lg-3">
                        <a href="${pageContext.request.contextPath}/produto/${produto.id}">
                            <div class="card">
                                <img class="card-img-top" src="${produto.imagem}" alt="Foto produto">
                                <div class="card-body">
                                    <h4 class="card-title"><a
                                            href="${pageContext.request.contextPath}/produto/${produto.id}"
                                            class="text-dark" title="Ver produto">${produto.nome}</a></h4>
                                    <div class="row card-infos">
                                        <div class="col">
                                            <p class="item-price"><span>R$${produto.preco}</span></p>
                                        </div>
                                        <div class="col">
                                            <a href="${pageContext.request.contextPath}/carrinho/adiciona/${produto.id}"
                                               class="btn btn-success btn-block">Add ao carrinho</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<!--Fim Pesquisa -->

<%@include file="footer.jsp" %>
