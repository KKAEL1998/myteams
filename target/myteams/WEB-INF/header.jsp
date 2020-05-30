<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${pageContext.request.getAttribute("title") != null ? pageContext.request.getAttribute("title") : "MyTeams"}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/main.css">
    <script src="https://kit.fontawesome.com/885c3a2f9b.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <!--Caso precise usar o js, crie um arquivo .js e altere essa linha para src="js/_cadastro.js"  -->

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>
<nav class="navbar bg-info navbar-expand-sm navbar-dark justify-content-between">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">
        <img src="${pageContext.request.contextPath}/img/logoo.png">
        MyTeams
    </a>
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/sobre">Sobre</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/contato">Contato</a>
        </li>
        <c:if test="${sessionScope.usuario != null}">

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/conta">Olá, ${sessionScope.usuario.nome}!
                    Acesse sua conta</a>
            </li>
            <c:if test="${sessionScope.usuario.tipoUsuario == 'ADMINISTRADOR'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin">Administrativo</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sair</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.usuario == null}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/novo-usuario">Cadastro</a>
            </li>
        </c:if>
        <c:if test="${sessionScope.carrinho != null}">
            <li class="nav-item">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/carrinho">Carrinho <span
                        class="badge badge-light">${sessionScope.carrinho.quantidadeItens}</span></a>
            </li>
        </c:if>

    </ul>
    <form class="form-inline">
        <input class="form-control mr-sm-2" type="text" placeholder="Pesquisar">
        <button class="btn btn-outline-light" type="submit">Pesquisar</button>
    </form>
</nav>
