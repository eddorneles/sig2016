var map;
var markerMinhaCasa;
var markerBunkerBurger;
var markerAssociacao;
var minhaCasaLatLng = { lat: -2.500609, lng: -44.255038 };
var associaoAtleticaACLatLng = { lat: -2.501762, lng: -44.253809 };
var bunkerBurgerLatLng = { lat: -2.5111122, lng: -44.259677 };

var infoWindowMinhaCasa;
var infoWindowBunkerBurger;
var infoWindowAssociacao;
var txtInfoWindowMinhaCasa = '<div><h3>Minha Casa</h3></div>';
var txtInfoWindowBunkerBurger = '<h3>Bunker Burger</h3><p>Venha comer no Bunker '  +
                                'Burger, várias opções de hamburguer gourmet com ' +
                                'deliciosos molhos e ótimas combinações de ingredientes. ' +
                                'Recrute um hamburguer com pão '+
                                'australiano acompanhado de uma caneca de batata ' +
                                'frita. </p>' +
                                '<img style="width: 50%; height:50%; display: block; margin:auto;" src="http://scontent.cdninstagram.com/t51.2885-15/s480x480/e35/12677445_778177285648321_1976979289_n.jpg?ig_cache_key=MTE4NjU1MDQ0NTk2Mjc0MjMzNQ%3D%3D.2"/>'
var txtInfoWindowAssociacao = '<h3>Associação Atlética Alto do Calhau</h3>' +
                                 '<p>Venha assistir aos jogos de futebol dos clubes ' +
                                 'brasileiros, se deliciar com  ótimas refeições ' +
                                 'e tira-gostos, jogar futebol às terças e quintas, '+
                                 'e apreciar aos domingos um delicioso prato de feijoada ' +
                                 'ao som de samba ao vivo </p>'
/*var txtInfoWindowAssociacaoTab2 = '<h3>Jogar Futebol</h3> <p>Todas as terças '  +
                                  'e quintas venha jogar futebol na Associação '+
                                  'Atlética do Alto Calhau a partir das 19h. ' +
                                  '<p>Pagamento por dia: R$ 10,00 </p>' +
                                  '<p>Pagamento mensal: R$ 50,00 </p></p>';
                                  */
var txtInfoWindowAssociacaoTab3;



function initMap(){
    mapProp = {
        zoom: 14,
        center: minhaCasaLatLng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map( document.getElementById('gMap'), mapProp );
    fnCarregarMarcadores();
}

function fnCarregarMarcadores(){
    //Carregamento do Marcador da minha casa
    markerMinhaCasa = new google.maps.Marker({
            position: minhaCasaLatLng,
            map: map,
            title: 'Localização da minha Casa'
        });
    //Instanciação do infoWindowMinhaCasa com texto
    infoWindowMinhaCasa = new google.maps.InfoWindow({
            content: txtInfoWindowMinhaCasa
    });
    //Adicionar listener para mostrar InfoWindow do markerMinhaCasa
    markerMinhaCasa.addListener( 'click' , function(){
        infoWindowMinhaCasa.open( map , markerMinhaCasa );
        infoWindowAssociacao.close();
        infoWindowBunkerBurger.close();
    });
    //Carregamento do marcador do Bunker Burger
    markerBunkerBurger = new google.maps.Marker({
            position: bunkerBurgerLatLng,
            map: map,
            title: 'Bunker Burger'
        });
    //Instanciação do infoWindowBunkerBurger com texto
    infoWindowBunkerBurger = new google.maps.InfoWindow({
        content: txtInfoWindowBunkerBurger
    });
    //Adicionar listener para mostrar InfoWindow do markerBunkerBurger
    markerBunkerBurger.addListener( 'click' , function(){
        infoWindowBunkerBurger.open( map , markerBunkerBurger );
        infoWindowAssociacao.close();
        infoWindowMinhaCasa.close();
    });
    //Carregamento do marcador da Associação Atlética do Alto do Calhau
    markerAssociacao = new google.maps.Marker({
            position: associaoAtleticaACLatLng,
            map: map,
            title: 'Associação Atlética Alto do Calhau'
        });
    //Instanciação do infoWindowAssociacao com texto
    infoWindowAssociacao = new google.maps.InfoWindow({
        content: txtInfoWindowAssociacao
    });
    //Abertura do InfoWindow da Associação
    markerAssociacao.addListener( 'click', function(){
        infoWindowAssociacao.open( map, markerAssociacao );
        infoWindowBunkerBurger.close();
        infoWindowMinhaCasa.close();
    });
}
