<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div id="app">
    <section class="section">
        <div class="container mt-5">
            <div class="row">
                <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">

                    <div class="card card-primary">
                        <div class="card-header">

                            <c:if test="${produto != null}">
                                <h4>Alterar Produto</h4>
                            </c:if>
                            <c:if test="${produto == null}">
                                <h4>Adicionar Produto</h4>
                            </c:if>
                        </div>

                        <div class="card-body">

                            <c:if test="${exception != null}">
                                <div class="alert alert-danger">
                                        ${exception.message}
                                </div>
                            </c:if>

                            <div class="row">
                                <div class="col-6">
                                    <c:if test="${produto != null}">
                                        <img id="imagemUpload" style="width: 100%" src="${produto.imagem}"
                                             alt="Imagem"/>
                                    </c:if>
                                    <c:if test="${produto == null}">
                                        <img id="imagemUpload" style="display: none; width: 100%" src="#" alt="Imagem"/>
                                    </c:if>
                                </div>
                                <div class="col-6">
                                    <form id="form" action="#">
                                        <input type="file" name="imagem" accept="image/*" onchange="sendImage()">
                                    </form>
                                </div>
                            </div>

                            <form method="POST">
                                <div class="row">
                                    <div class="form-group col-6">
                                        <label for="nome">Nome</label>
                                        <input id="nome" type="text" class="form-control" name="nome"
                                               value="${produto.nome}" autofocus required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="descricao">Descrição do jogo</label>
                                        <textarea id="descricao" type="text" class="form-control" name="descricao"
                                                  required>${produto.descricao}</textarea>
                                    </div>
                                    <input id="imagem" type="text" style="display: none" class="form-control"
                                           name="imagem" value="${produto.imagem}" required>
                                    <input id="id" type="text" style="display: none" class="form-control" name="id"
                                           value="${produto.id}">
                                    <div class="form-group col-6">
                                        <label for="preco">Preço</label>
                                        <input id="preco" class="form-control" name="preco" autofocus
                                               value="${produto.preco}" required>
                                    </div>
                                    <div class="form-group col-6">
                                        <label for="categoria">Categoria</label>
                                        <select name="categoria" class="form-control" id="categoria">
                                            <c:forEach var="categoria" items="${categorias}">
                                                <option value="${categoria.id}">${categoria.nome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                </div>
                                <div class="col-sm-12">
                                    <c:if test="${produto != null}">
                                        <button type="submit" class="botaozin">Alterar</button>
                                    </c:if>
                                    <c:if test="${produto == null}">
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
<script>
    function sendImage() {

        var img = document.getElementById('imagemUpload');
        var input = document.getElementById('imagem');

        img.style.display = 'none';
        input.value = '';

        var PATH = '${pageContext.request.contextPath}' + '/imagem/';
        var DONE = 4;
        var STATUS_OK = 200;
        var METHOD_POST = 'POST';

        var httpRequest = new XMLHttpRequest();

        function completed(response) {
            return response.readyState === DONE && response.status === STATUS_OK;
        }

        httpRequest.onreadystatechange = function () {
            if (completed(this)) {
                img.style.display = 'block';
                img.src = JSON.parse(this.responseText).url;
                input.value = JSON.parse(this.responseText).url;

                console.log(input);
            }
        };

        httpRequest.open(METHOD_POST, PATH, true);
        httpRequest.send(new FormData(document.getElementById('form')))
    }
</script>

<%@include file="footer.jsp" %>
