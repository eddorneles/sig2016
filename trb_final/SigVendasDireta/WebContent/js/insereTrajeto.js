const APPLICATION_NAME = '/sigVendasDireta';

var _btnCriarTrajeto = '#btnCriarTrajeto';
var _btnCancelarTrajeto = '#btnCancelarTrajeto';
var view;
var map;
var position;
var featureOverlay;
var gl_draw;
var gl_source;
var gl_features;
var drawingFeatures;
var gl_json;



$(document).ready( function(){
    //fnStartServlet();
    //fnHandleEvents();
    fnPrepareMap();
    fnShowGeoLocation();
    fnEnableDrawing();
//    fnAddInteraction();
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

    //   // update the HTML page when the position changes.
    //   geolocation.on('change', function() {
    //     el('accuracy').innerText = geolocation.getAccuracy() + ' [m]';
    //     el('altitude').innerText = geolocation.getAltitude() + ' [m]';
    //     el('altitudeAccuracy').innerText = geolocation.getAltitudeAccuracy() + ' [m]';
    //     el('heading').innerText = geolocation.getHeading() + ' [rad]';
    //     el('speed').innerText = geolocation.getSpeed() + ' [m/s]';
    //   });

      // handle geolocation error.
      geolocation.on('error', function(error) {
        //var info = document.getElementById('info');
        //info.innerHTML =
        alert( error.message );
        console.log(error.message);
        //info.style.display = '';
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
}

function fnEnableDrawing(){
    gl_features = new ol.Collection();
    featureOverlay = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: gl_features
        })
    });
    featureOverlay.setMap( map );
    fnHandleDrawEvents();
}

function fnHandleDrawEvents(){
    $( _btnCriarTrajeto ).on( "click" , function(){
        fnAddInteraction( "LineString" );
    });
    $( _btnCancelarTrajeto).on( "click" , function(){
        //Cancela inserção de um novo trajeto
        map.removeInteraction( gl_draw );
    });
    $( '#btnShowFeatures').on( "click" , function(){
        gl_features.forEach( function( feature ){
            console.log( feature.getGeometry().getCoordinates() );
        })
    });
}

function fnAddInteraction( type ){
    gl_draw = new ol.interaction.Draw({
        //source: gl_source,
        features: gl_features,
        type: /** @type {ol.geom.GeometryType} */ "LineString"
    });
    map.addInteraction( gl_draw );
    gl_draw.on( "drawend" , function( evt ){
        //alert( "terminou de desenhar trajeto");
        var justNowFeature = evt.feature;
        var featureGeom = justNowFeature.getGeometry().getCoordinates();
        console.log( "Feature: " + featureGeom );
        var geomTrj = justNowFeature.getGeometry().clone();
        geomTrj.transform( 'EPSG:3857' , "EPSG:4326" );
        fnLoadJson( geomTrj );
        /**/
    });
}//END fnAddInteraction

function fnLoadJson( geom ){
    var writer = new ol.format.GeoJSON();
    var str = writer.writeGeometry( geom );
    console.log( str );
    gl_json = str;
    $( '#geojson' ).text( str );
}

function submitTrajeto(){
    $.post( APPLICATION_NAME + '/')
}
