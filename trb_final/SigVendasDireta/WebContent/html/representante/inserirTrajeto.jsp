<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="../../css/ol.css" />
    <link type="text/css" rel="stylesheet" href="../../css/materialize.css" media="screen,projection" />
    <link type="text/css" rel="stylesheet" href="../../css/main.css" />
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
        <h2>Inserir Trajeto</h2>
        <div class="row">
            <div id="map" class="col s5">
            </div>
        </div>
        <div style="margin: 0 0 10px 0;">
            <div class="input-field">
                <input placeholder="" id="inpCpf" type="text" class="validate"/>
                <label for="inpCpf">Cpf Representante</label>
            </div>
            <a id="btnCriarTrajeto" class="waves-effect waves-light btn">Criar Trajeto</a>
            <a id="btnCancelarTrajeto" class="waves-effect waves-light btn">Cancelar Trajeto</a>

        </div>
    </div>

    <!-- Importações de script JS -->

    <!--
    <script type="text/javascript" src="js/olTest.js"></script>
    -->

    <script type="text/javascript" src="../../js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../..//js/materialize.js"></script>
    <script type="text/javascript" src="../../js/ol/ol-debug.js"></script>
    <script type="text/javascript" src="../../js/insereTrajeto.js"></script>

</body>
</html>
