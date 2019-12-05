<%@include file="header.jsp" %>
<div class="container">
    <h1>Alterar/Excluir Produtos</h1>

    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">Produtos</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="accordion" id="accordionExample">
                                <c:forEach var="produto" items="${produtos}">
                                    <div class="media produto-carrinho">
                                        <img src="${produto.imagem}" class="mr-3">
                                        <div class="media-body">
                                            <div class="row">
                                                <div class="col-6 centered">${produto.nome}</div>
                                                <div class="col-3 text-center centered">
                                                    <a href="${pageContext.request.contextPath}/admin/produtos/editar/${produto.id}" class="btn btn-primary">Editar</a>
                                                </div>
                                                <div class="col-3 centered text-right">
                                                    <a href="${pageContext.request.contextPath}/admin/produtos/remover/${produto.id}" class="btn btn-danger">Remover</a>
                                                </div>
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
<script>
    if (location.search === '?erro=fk') {
        alert('Você não pode remover esse produto, pois ele já foi incluido em um pedido!');
    }
</script>
<%@include file="footer.jsp" %>