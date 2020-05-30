<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div id="app">
    <section class="section">
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">

                    <div class="card card-primary">
                        <div class="card-header">

                            <c:if test="${categoria != null}">
                                <h4>Alterar Categoria</h4>
                            </c:if>
                            <c:if test="${categoria == null}">
                                <h4>Nova Categoria</h4>
                            </c:if>
                        </div>

                        <div class="card-body">

                            <c:if test="${exception != null}">
                                <div class="alert alert-danger">
                                        ${exception.message}
                                </div>
                            </c:if>

                            <form method="POST">
                                <div class="row">
                                    <div class="form-group col-6">
                                        <label for="nome">Nome</label>
                                        <input id="nome" type="text" class="form-control" name="nome"
                                               value="${categoria.nome}" autofocus required>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <c:if test="${categoria != null}">
                                        <button type="submit" class="botaozin">Alterar</button>
                                    </c:if>
                                    <c:if test="${categoria == null}">
                                        <button type="submit" class="botaozin">Cadastrar</button>
                                    </c:if>
                                </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="footer.jsp" %>
