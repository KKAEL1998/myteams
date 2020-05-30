<%@include file="header.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--Inicio Cadastro -->
<div id="app">
    <section class="section">
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">

                    <div class="card card-primary">
                        <div class="card-header"><h4>Alterar Cadastro</h4></div>

                        <div class="card-body">
                            <form method="POST" autocomplete="off">

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


                                <div class="row">
                                    <div class="form-group col-6">
                                        <label for="nome">Nome</label>
                                        <input id="nome" type="text" class="form-control" name="nome" autofocus required value="${usuario.nome}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="sobrenome">Sobrenome</label>
                                        <input id="sobrenome" type="text" class="form-control" name="sobrenome" required value="${usuario.sobrenome}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="cpf">CPF</label>
                                        <input id="cpf" type="text" class="form-control" name="cpf" onkeypress="aplicaMascaraCpf('cpf')" required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" maxlength="14" value="${usuario.cpf}" disabled="true">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="dataNascimento">Data de Nascimento</label>
                                        <input id="dataNascimento" type="text" class="form-control" name="dataNascimento" onkeypress="aplicaMascaraData('dataNascimento')" pattern="\d{2}/\d{2}/\d{4}" maxlength="10"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${usuario.dataNascimento}"/>">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="cep">CEP</label>
                                        <input id="cep" type="text" class="form-control" name="cep" required value="${usuario.endereco.cep}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="rua">Endereço</label>
                                        <input id="rua" type="text" class="form-control" name="rua" required value="${usuario.endereco.rua}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="last_name">Número</label>
                                        <input id="last_name" type="text" class="form-control" name="numero" required value="${usuario.endereco.numero}">
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="complemento">Complemento</label>
                                        <input id="complemento" type="text" class="form-control" name="complemento" required value="${usuario.endereco.complemento}">
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="cidade">Cidade</label>
                                        <input id="cidade" type="text" class="form-control" name="cidade" required  value="${usuario.endereco.cidade}">
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="estado">Estado</label>
                                        <input id="estado" type="text" class="form-control" name="estado" required value="${usuario.endereco.estado}">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="email">Email</label>
                                        <input id="email" type="email" class="form-control" name="email" required value="${usuario.email}" disabled>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="telefone">Telefone</label>
                                        <input id="telefone" type="text" class="form-control" name="telefone" required value="${usuario.telefone}">
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <button type="submit" class="botaozin">Alterar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<!--Fim Cadastro -->
<%@include file="footer.jsp" %>