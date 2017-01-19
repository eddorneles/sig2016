<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="./css/materialize.css" media="screen,projection" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Título da página</title>
</head>
<body>

    <nav>
        <div class="nav-wrapper blue darken-3">
            <div class="col s12">
                <a href="#" class="brand-logo left" >Sig Vendas Direta</a>
            </div>

        </div>
    </nav>
    <div class="container">
    <h1>Teste de JSP!!!</h1>
    <%= "Bem vindo ao jsp!!!" %>
    <p>
        Estou testando o funcionamento do jsp para poder seguir adiante com
        minha aplicação
    </p>
    <div id="txtConnection">

    </div>
    <!-- div em que o servidor retornará a tabela com o resultado da consulta -->
    <div id="sqlQueryResult" class="card-panel blue lighten-2">
    </div>
    <div>
        <textarea id="txtAreaSql" name="txtAreaSql" rows="8" cols="40"></textarea>
        <a id="btnExecutaSql" class="waves-effect waves-light btn">Executar</a>
    </div>
    </div>
<script type="text/javascript" src="js/jquery/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/ol/ol.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
