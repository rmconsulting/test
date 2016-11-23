controllers.controller('MapaCtrl', function($scope, $ionicLoading, $compile, Server) {
  $scope.error={"message":"", "status":false};
  $scope.ok={"message":"", "status":false};;

  $scope.id="EL ID";
  $scope.tipo="EL TIPO";
  $scope.showmenu=false;

  $ionicLoading.show({
    template: 'Loading Google Maps'
  });

  var latLng = new google.maps.LatLng(37.3000, -120.4833);

  var mapOptions = {
    center: latLng,
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  map = new google.maps.Map(document.getElementById("map"), mapOptions);

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };
      map.setCenter(pos);
    }, function() {

    });
  }

  //Wait until the map is loaded
  google.maps.event.addListenerOnce(map, 'idle', function(){
    loadMarkers();
  });

  function loadMarkers(){
      var records = [{lat:"-34.6022781",lng:"-58.382175", name:"gaston", id:1},{lat:"-34.6025781",lng:"-58.402175", name:"gaston",id:2},{lat:"-34.6025781",lng:"-58.392175", name:"gaston2",id:3}];
      for (var i = 0; i < records.length; i++) {
        var record = records[i];
        var markerPos = new google.maps.LatLng(record.lat, record.lng);
        // Add the markerto the map

       var luminaryIcon = {
          url: "../img/luminaria.png", // url
          scaledSize: new google.maps.Size(50, 50), // scaled size
          origin: new google.maps.Point(0,0), // origin
          anchor: new google.maps.Point(0, 0) // anchor
        };

        var marker = new google.maps.Marker({
          map: map,
          animation: google.maps.Animation.DROP,
          icon: luminaryIcon,
          position: markerPos
        });

        var infoWindowContent="<div ng-click=\"show("+ record.id + ")\">Informacion del artefacto coordenadas <br> hacer click para desplegar menu</div>";

        var compiled=$compile(infoWindowContent)($scope);

        addInfoWindow(marker, compiled[0], record);
        $ionicLoading.hide();
      }
  }

  $scope.show=function(idlu){
    $scope.id=idlu;
    $scope.showmenu=true;
  }

  $scope.onLuminaria=function(){
    luminariaAction("on","encendio");
  }

  $scope.offLuminaria=function(){
    luminariaAction("off","apago");
  }

  $scope.dimmerLuminaria=function(){
    luminariaAction("dimmer","regulo intensidad de ");
  }

  function luminariaAction(action, text){
    Server.post("luminaria/" + action + "/"+$scope.id, $scope.usertoken.token,function (response) {
      if (response.operacion == "OK") {
        $scope.ok.message="Se " + text + " la luminaria" + $scope.id;
        $scope.ok.status=true;
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
        } else {
          $scope.error.message = "No se " + text + " la luminaria";
        }
        $scope.error.status = true;
      }
      $scope.showmenu=false;
    });
  }

  function addInfoWindow(marker, message, record) {
    var infoWindow = new google.maps.InfoWindow({
      title: "Menu",
      content: message
    });

    google.maps.event.addListener(marker, 'click', function () {
      infoWindow.open(map, marker);
    });

  }
});
