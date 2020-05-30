<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!--Inicio Cadastro -->
<div id="app">
    <section class="section">
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">

                    <div class="card card-primary">
                        <div class="card-header"><h4>Cadastro</h4></div>

                        <div class="card-body">
                            <form method="POST" autocomplete="off">

                                <%
                                    if (pageContext.getRequest().getAttribute("exception") != null) {
                                %>
                                <div class="alert alert-danger">${exception.message}</div>
                                <%
                                    }
                                %>


                                <div class="row">
                                    <div class="form-group col-6">
                                        <label for="nome">Nome</label>
                                        <input id="nome" type="text" class="form-control" name="nome" autofocus required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="sobrenome">Sobrenome</label>
                                        <input id="sobrenome" type="text" class="form-control" name="sobrenome" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="cpf">CPF</label>
                                        <input id="cpf" type="text" class="form-control" name="cpf" onkeypress="aplicaMascaraCpf('cpf')" required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" maxlength="14">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="dataNascimento">Data de Nascimento</label>
                                        <input id="dataNascimento" type="text" class="form-control" name="dataNascimento" onkeypress="aplicaMascaraData('dataNascimento')" pattern="\d{2}/\d{2}/\d{4}" maxlength="10">
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="cep">CEP</label>
                                        <input id="cep" type="text" class="form-control" name="cep" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="rua">Endereço</label>
                                        <input id="rua" type="text" class="form-control" name="rua" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="last_name">Número</label>
                                        <input id="last_name" type="text" class="form-control" name="numero" required>
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="complemento">Complemento</label>
                                        <input id="complemento" type="text" class="form-control" name="complemento" required>
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="cidade">Cidade</label>
                                        <input id="cidade" type="text" class="form-control" name="cidade" required>
                                    </div>

                                    <div class="form-group col-6">
                                        <label for="estado">Estado</label>
                                        <input id="estado" type="text" class="form-control" name="estado" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="email">Email</label>
                                        <input id="email" type="email" class="form-control" name="email" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="telefone">Telefone</label>
                                        <input id="telefone" type="text" class="form-control" name="telefone" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-6">
                                        <label for="senha" class="d-block">Senha</label>
                                        <input id="senha" type="password" class="form-control pwstrength" name="senha" required>
                                    </div>
                                </div>
                                <div class="col-sm-12">
                                    <button type="submit" class="botaozin">Cadastrar</button>
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