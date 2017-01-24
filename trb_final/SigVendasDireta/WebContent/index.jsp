<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="css/ol.css" />
    <link type="text/css" rel="stylesheet" href="css/materialize.css" media="screen,projection" />
    <link type="text/css" rel="stylesheet" href="css/main.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Título da página</title>
</head>
<body>
    <nav class="top-nav">
        <div class="nav-wrapper blue darken-3">
            <div class="container">
                <a class="page-title brand-logo left">SIG Vendas Direta</a>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div id="map" class="col s5">

            </div>
        </div>
    <div id="txtConnection">

    </div>
    <!-- div em que o servidor retornará a tabela com o resultado da consulta -->
    <div id="sqlQueryResult" class="card-panel blue lighten-5">
    </div>
    <div>
        <textarea id="txtAreaSql" name="txtAreaSql" rows="8" cols="40"></textarea>
        <a id="btnExecutaSql" class="waves-effect waves-light btn">Executar</a>
        <a id="btnTestaWkt" class="waves-effect waves-light btn">Testar Wkt</a>
    </div>
    </div>

    <!-- Importações de script JS -->

    <!--
    <script type="text/javascript" src="js/olTest.js"></script>
    -->

    <script type="text/javascript" src="js/ol/ol-debug.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>
    <script type="text/javascript" src="js/index.js"></script>


</body>
</html>
