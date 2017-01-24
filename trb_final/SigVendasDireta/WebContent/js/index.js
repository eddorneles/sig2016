var vectorLayer;
var view;
var map;

/************************************************************
    Inicia a execução do do JS quando a página tiver sido
completamente carregada
*************************************************************/
$(document).ready( function(){
    //fnStartServlet();
    fnHandleEvents();
    fnPrepareMap();
});

function fnStartServlet(){
    $.get( 'IndexControl', function( response ){
        $('#txtConnection').html( response );
    });
}//END fnStartServlet

function fnHandleEvents(){
    $( '#btnExecutaSql' ).on( "click", fnSubmeteSql );
    $( '#btnTestaWkt').on( 'click' , fnTestaWkt );

}//END fnHandleEvents

function fnSubmeteSql(){
    //Seleciona o conteúdo armazenado no textAreaSql e retorna à sqlText
    var sqlText = $('#txtAreaSql').val();
    /*Realiza uma requisição Ajax get
        param1: Servlet a ser executado
        param2: Conjunto de parâmetros a serem recebidos no servlet
        param3: função a ser executada após a resposta do servidor */
    $.get( 'IndexControl' , {"sql":sqlText,"metodo":"createHtmlFromQuery"}, function(response){
        $( '#sqlQueryResult' ).html(response);
    });

}//END fnSubmeteSql

function fnTestaWkt(){
    var v = $( '#txtAreaSql').val();
    $.get( 'IndexControl' , {'json': v , 'metodo':'testaWkt'}, function( response ){
        $( '#sqlQueryResult').text( response );
    });
}

function fnPrepareMap(){
        view = new ol.View({
            center: [-4921181.7116422355, -288364.72289168416],
            zoom: 11,
            maxZoom: 18,
            minZoom: 8
        });

        var baseLayer = new ol.layer.Tile({
            source: new ol.source.OSM({ layer: 'osm' })
        });

        map = new ol.Map({
            target: 'map',
            controls: ol.control.defaults().extend([
                new ol.control.ScaleLine(),
                new ol.control.ZoomSlider()
            ]),
            renderer: 'canvas',
            layers: [baseLayer],
            view: view
        });
}//END fnPrepareMap
