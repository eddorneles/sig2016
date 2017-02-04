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
        <h2>Cadastro de Representante</h2>
        <div class="row">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                        <input placeholder="" id="inpNome" type="text" class="validate"/>
                        <label for="inpNome">Nome</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="" id="inpCpf" type="text" class="validate"/>
                        <label for="inpCpf">CPF</label>
                    </div>
                    <div class="input-field col s6">
                        <input placeholder="" id="inpRg" type="text" class="validate"/>
                        <label for="inpRg">RG</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="inpDataNascimento" type="date" class="datepicker" />
                        <label for="inpDataNascimento">Data de Nascimento</label>
                    </div>
                    <div class="input-field col s3">
                        <input placeholder="Logradouro" id="inpLogradouro" type="text" class="validate"/>
                        <label for="inpLogradouro">Logradouro</label>
                    </div>
                    <div class="input-field col s1">
                        <input placeholder="Numero" id="inpNumero" type="text" class="validate"/>
                        <label for="inpNumero">Numero</label>
                    </div>
                    <div class="input-field col s2">
                        <input placeholder="Bairro" id="inpBairro" type="text" class="validate"/>
                        <label for="inpBairro">Bairro</label>
                    </div>
                    <div class="input-field col s3">
                        <input placeholder="Cidade" id="inpCidade" type="text" class="validate"/>
                        <label for="inpCidade">Cidade</label>
                    </div>
                    <div class="input-field col s1">
                        <input placeholder="UF" id="inpUf" type="text" class="validate"/>
                        <label for="inpUf">UF</label>
                    </div>
                    <div class="input-field col s2">
                        <input placeholder="CEP" id="inpCep" type="text" class="validate"/>
                        <label for="inpCep">CEP</label>
                    </div>

                </div>
                <div class="row">
                    <p>
                        Selecione o local de seu endereço:
                    </p>
                    <!-- div onde o mapa será carregado -->
                    <div id="map"></div>
                    <div>
                        <a id="btnConfirmar" class="waves-effect waves-light btn">Confirmar</a>
                        <a id="btnReSelecionar" class="waves-effect waves-light btn disabled">Re-Selecionar</a>
                        <!--<a id="btnListar" class="waves-effect waves-light btn">Listar</a>-->
                    </div>

                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="../../js/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/materialize.js"></script>
    <script type="text/javascript" src="../../js/ol/ol-debug.js"></script>
    <script type="text/javascript" src="../../js/cadastroRepresentante.js"></script>
</body>

</html>
