<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>
<div class="container">

    <div class="row">
        <div class="col-12">
            <div class="card card-primary">
                <div class="card-header">
                    Incluir novo administrador
                </div>
                <div class="card-body">

                    <c:if test="${exception != null}">
                        <div class="alert alert-danger">
                                ${exception.message}
                        </div>
                    </c:if>

                    <c:if test="${success != null}">
                        <div class="alert alert-success">
                                ${success}
                        </div>
                    </c:if>

                    <p>Para incluir um novo administrador, a pessoa já deve estar previamente cadastrada na loja como
                        cliente.</p>
                    <form action="${pageContext.request.contextPath}/admin/novo-admin" method="get">
                        <div class="form-group">
                            <label for="cpf">CPF</label>
                            <input id="cpf" type="text" class="form-control" name="cpf"
                                   onkeypress="aplicaMascaraCpf('cpf')" required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                                   maxlength="14">
                        </div>
                        <button class="btn btn-primary" type="submit">Consultar</button>
                    </form>

                    <c:if test="${usuario_consultado != null}">
                        <div class="row">
                            <div class="col-4">
                                <p><strong>Nome</strong></p>
                                <p>${usuario_consultado.nome} ${usuario_consultado.sobrenome}</p>
                            </div>
                            <div class="col-4">
                                <p><strong>CPF</strong></p>
                                <p>${usuario_consultado.cpf}</p>
                            </div>
                            <div class="col-4">
                                <p><strong>Telefone</strong></p>
                                <p>${usuario_consultado.telefone}</p>
                            </div>
                            <div class="col-4">
                                <p><strong>E-mail</strong></p>
                                <p>${usuario_consultado.email}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <form action="${pageContext.request.contextPath}/admin/novo-admin" method="post">
                                    <input type="hidden" name="cpf" value="${usuario_consultado.cpf}">
                                    <button type="submit" class="btn btn-primary">
                                        Elevar cliente à administrador
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="footer.jsp" %>
