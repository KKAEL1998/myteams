<!DOCTYPE html>
<html lang="en" >

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">  

    </head>

    <body>

        <div class="login-page">
            <div class="form">
                <c:if test="${exception != null}">
                    <div style="color: firebrick">
                        ${exception.message}
                    </div>
                </c:if>
                <form id="form-login" class="login-form" action="${pageContext.request.contextPath}/login" method="POST">
                    <center>
                        <img src="${pageContext.request.contextPath}/img/Screenshot_1.png">
                    </center>
                    <p></p>
                    <h4 class="messagem">E-mail:</h4>
                    <input type="text" placeholder="Digite seu Email" name="email" required/>
                    <h4 class="messagem">Senha:</h4>
                    <input type="password" placeholder="Digite sua Senha" name="senha" required/>
                    <button><b>login</b></button>
                </form> 
            </div>
            <div class="cadastre">
                <b>
                    <h5>
                        <p class="message"><a href="../Recuperar.html" style="text-decoration:none" >Recuperar Senha</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ainda não tem conta? <a href="${pageContext.request.contextPath}/novo-usuario" style="text-decoration:none">Cadastre-se</a></p>
                    </h5>
                </b
            </div>
        </div>
        <script>
            var pathName = document.location.pathname.split("/");
            if (pathName[pathName.length - 2] === 'login') {
                document.getElementById('form-login').action = '${pageContext.request.contextPath}/login/' + pathName[pathName.length - 1];
            }
        </script>
    </body>
</html>
