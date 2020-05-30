<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<section id="section-contato">
    <div class="contact">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="contact__h1 contact__h1--font"><span class="contact__h1--colorGreen">CONTATO</span>
                    </h1>
                </div>
                <div class="col-sm-6">
                    <form id="form_contato" class="contato_form grid-12">

                        <input type="text" class="nome" name="name" placeholder="Nome" required>

                        <input class="email" type="email" name="email" placeholder="E-mail" required>

                        <input type="text" name="telefone" class="telefone" placeholder="Telefone" required>

                        <textarea id="Mensagem" class="mensagembox" placeholder="Mensagem" required></textarea>
                    </form>
                    <button type="submit" class="botao">Enviar</button>
                </div>
                <div class="col-sm-6">
                    <h3>Endere�o</h3>
                    <p class="contact__p">Av. das Castanheiras, S/N - Lote 3700 - �guas Claras <br>Bras�lia - DF,
                        70297-400 Centro Universit�rio Euro-Americano</p>
                    <h3>Redes Sociais</h3>
                    <a href="http://facebook.com" target="_blank"><img class="botaofb" src="img/fbbotao.png"></a>
                    </li>
                    <a href="https://www.linkedin.com" target="_blank"><img class="botaoin"
                                                                            src="img/ikbotao.jpg"></a></li>
                    <a href="http://instagram.com" target="_blank"><img class="botaoig" src="img/igbotao.png"></a>
                    </li>
                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="footer.jsp" %>
