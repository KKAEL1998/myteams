<%@include file="header.jsp" %>
<div class="container">
    <!--add container -->
    <div class="row">
        <!--add linhas do sistema grid -->

        <div class="col-sm-12 sobre_espa�o">
            <!--add as colunas + espa�o abaixo -->
            <h1 class="testando">${produto.nome}</h1>
        </div>
        <div class="col-6 col-md-6 col-lg-5">
            <div class="row">
                <div class="card-body">
                    <img class="card-img-top" src="${produto.imagem}" alt="Foto produto">

                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <!--add as colunas -->
            <p class="tintaf">${produto.descricao}</p>
            <p class="item-price"><span>R$ ${produto.preco}</span></p>
            <a href="${pageContext.request.contextPath}/carrinho/adiciona/${produto.id}"
               class="btn btn-success btn-block">Add ao carrinho</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
