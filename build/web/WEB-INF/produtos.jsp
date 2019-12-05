<%@page import="java.util.List"%>
<%@page import="br.com.myteams.model.usuario.Produto"%>
<%@include file="header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">

    <!--    <div class="row col">
            <div class="dropdown float-right">
    
            <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                Ordenar por:
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Popularidade</a>
                <a class="dropdown-item" href="#">Maior preço</a>
                <a class="dropdown-item" href="#">Menor preço</a>
            </div>
        </div>-->
    <div class="row">
        <c:forEach var="produto" items="${produtos}">
            <div class="col-md-6 col-lg-3">
                <a href="${pageContext.request.contextPath}/produto/${produto.id}">
                    <div class="card">
                        <img class="card-img-top" src="${produto.imagem}" alt="Foto produto">
                        <div class="card-body">
                            <h4 class="card-title"><a href="${pageContext.request.contextPath}/produto/${produto.id}" class="text-dark" title="Ver produto">${produto.nome}</a></h4>
                            <div class="row card-infos">
                                <div class="col">
                                    <p class="item-price"><span>R$${produto.preco}</span></p>
                                </div>
                                <div class="col">
                                    <a href="${pageContext.request.contextPath}/carrinho/adiciona/${produto.id}" class="btn btn-success btn-block">Add ao carrinho</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<!--Fim Pesquisa -->

<%@include file="footer.jsp" %>