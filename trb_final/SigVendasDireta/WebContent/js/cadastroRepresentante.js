var _btnConfirmar = '#btnConfirmar'; var _btnReSelecionar = '#btnReSelecionar';
var _btnLista = '#btnListar';
var _inpNome = '#inpNome' ; var _inpCpf = '#inpCpf'; var _inpRg = '#inpRg';
var _inpDataNascimento = '#inpDataNascimento'; var _inpLogradouro = '#inpLogradouro';
var _inpNumero = '#inpNumero'; var _inpBairro = '#inpBairro';
var _inpCidade = '#inpCidade'; var _inpUf = '#inpUf' ; var _inpCep = '#inpCep';

const MSG_CONFIRM = 'Confirma cadastro?';
const APPLICATION_NAME = '/sigVendasDireta';

var gl_features;
var gl_enableCadastro = false;


$(document).ready( function(){
    fnHandleEvents();
    fnPrepareMap();
    fnShowGeoLocation();
    fnEnableDrawing();
});

function fnPrepareMap(){
        view = new ol.View({
            center: [-4921181.7116422355, -288364.72289168416],
            zoom: 11,
            maxZoom: 18,
            minZoom: 8
        });
        var baseLayer = new ol.layer.Tile({
            /*De onde está se extraindo os mapas, onde o mapa será renderizado*/
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

function fnShowGeoLocation(){
    var geolocation = new ol.Geolocation({
        projection: view.getProjection(),
        tracking : true
      });

      geolocation.on('error', function(error) {
        alert( error.message );
        console.log(error.message);
      });

      var accuracyFeature = new ol.Feature();
      geolocation.on('change:accuracyGeometry', function() {
        accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
      });

      var positionFeature = new ol.Feature();
      positionFeature.setStyle(new ol.style.Style({
        image: new ol.style.Circle({
          radius: 6,
          fill: new ol.style.Fill({
            color: '#3399CC'
          }),
          stroke: new ol.style.Stroke({
            color: '#fff',
            width: 2
          })
        })
      }));

      geolocation.on('change:position', function() {
        var coordinates = geolocation.getPosition();
        positionFeature.setGeometry(coordinates ?
            new ol.geom.Point(coordinates) : null);
        view.setCenter( coordinates );
        view.setZoom( 14 );
      });

      new ol.layer.Vector({
        map: map,
        source: new ol.source.Vector({
          features: [accuracyFeature, positionFeature]
        })
      });
}//END fnShowGeoLocation

function fnHandleEvents(){
    $('.datepicker').pickadate({
       //selectMonths: true, // Creates a dropdown to control month
       selectYears: 70, // Creates a dropdown of 15 years to control year
       maxDate: new Date( 2000, 11, 31 ),
     });
     $( _btnConfirmar ).on( 'click' , function(){
         if( gl_enableCadastro === true && confirm( MSG_CONFIRM ) ){
             fnSubmeteCadastro();
         }else{
             alert( "Preencha os campos necessários e adicione um local no mapa");
         }
     });
     $( _btnReSelecionar ).on( 'click' , function(){
        gl_features.clear();
        gl_enableCadastro = false;
        /* Enquanto não se seleciona um novo local deve-se bloquear o botão de
        re-seleção */
        $( _btnReSelecionar ).addClass( 'disabled' );
        fnAddInteraction();
     });
     $( '#btnListar').on( 'click' , function(){
         gl_features.forEach( function( feature ){
             console.log( feature.getGeometry().getCoordinates() );
         });
     });
}//END fnHandleDrawEvents

function fnEnableDrawing(){
    gl_features = new ol.Collection();
    var featureOverlay = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: gl_features
        })
    });
    featureOverlay.setMap( map );
    fnAddInteraction();
}//END fnEnableDrawing

function fnAddInteraction( ){
    gl_draw = new ol.interaction.Draw({
        features: gl_features,
        type: /** @type {ol.geom.GeometryType} */ "Point"
    });
    map.addInteraction( gl_draw );
    var isConfirmed = "";
    /* Ao adicionar um novo local (Ponto) */
    gl_draw.on( "drawend" , function( evt ){
        var justNowFeature = evt.feature;
        var featureGeom = justNowFeature.getGeometry().getCoordinates();
        console.log( "Feature: " + featureGeom );
        var geomTrj = justNowFeature.getGeometry().clone();
        geomTrj.transform( "EPSG:3857" , "EPSG:4326" );
        map.removeInteraction( gl_draw );
        /* Ao selecionar um novo ponto como localidade do representante deve-se liberar
        o botão de re-seleção */
        $( _btnReSelecionar ).removeClass( 'disabled' );
        gl_enableCadastro = true;
        //fnLoadJson( geomTrj );
    });
}//END fnAddInteraction

function fnConfirm(){
    var isConfirmed = confirm( "Confirma cadastro?" );
    if( isConfirmed === true ){
        fnAddInteraction();
    }
}//END fnConfirmLocation


function fnSubmeteCadastro(){
    var geom = gl_features.item( 0 ).getGeometry();
    /* Converte-se a geometria (Ponto) para o sistema de coordenadas do BD*/
    geom.transform( 'EPSG:3857' , 'EPSG:4326' );
    var writer = new ol.format.GeoJSON();
    console.log( geom );
    var jsonStr = writer.writeGeometry( geom );
    console.log( jsonStr );
    var nome = $( _inpNome ).val();
    var rg = $( _inpRg ).val();
    var cpf = $( _inpCpf ).val();
    var logradouro = $( _inpLogradouro ).val();
    var numero = $( _inpNumero ).val();
    var bairro = $( _inpBairro ).val();
    var cidade = $( _inpCidade ).val();
    var uf = $( _inpUf ).val();
    var cep = $( _inpCep ).val();
    $.post( APPLICATION_NAME + '/CadastroRepresentante' , {
        'nome' : nome,
        'rg' : rg,
        'cpf' : cpf,
        'logradouro' : logradouro,
        'numero' : numero,
        'bairro' : bairro,
        'cidade' : cidade,
        'uf' : uf,
        'cep' : cep,
        'json' : jsonStr
    });
}
