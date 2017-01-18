var vectorLayer;

$(document).ready( fnStartJS() );

function fnStartJS(){
    //fnPrepareMap();
    fnStartServlet();

}

function fnStartServlet(){
    $.get( 'IndexControl', function( response ){
        $('#txtConnection').html( response );
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

        var map = new ol.Map({
            target: 'mapa',
            controls: ol.control.defaults().extend([
                new ol.control.ScaleLine(),
                new ol.control.ZoomSlider()
            ]),
            renderer: 'canvas',
            layers: [baseLayer, vectorLayer],
            view: view
        });
}

function fnCarregaBairros(){
    vectorLayer = new ol.layer.Vector({
        source: new ol.source.Vector({
            format: new ol.format.GeoJSON,
            url: 'http://localhost/bairros.geojson'
        })
    });
}
