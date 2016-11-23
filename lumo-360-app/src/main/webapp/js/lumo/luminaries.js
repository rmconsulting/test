controllers.controller('LuminariesCtrl', function($scope, Server) {
  $scope.luminarias = [];
  $scope.error={"message":"", "status":false};
  $scope.hasMore=true;
  $scope.page=-1;
  var limit=20;
  $scope.del=function(luminaria){
    Server.update("luminaria/del",luminaria, function(response) {
      if (response.operacion == "OK") {
        $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
        var index = $scope.luminarias.indexOf(luminaria);
        $scope.luminarias.splice(index, 1);
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo borrar la luminaria";
        }
        $scope.error.status=true;
      }
    });
  }

  $scope.moreDataCanBeLoaded=function(){
      return $scope.hasMore;
  }

  $scope.loadMoreData=function() {
    $scope.page+=1;
    var from = ($scope.page*limit)+1;

    Server.get("luminaria/"+from+"/"+limit, function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length<limit){
          $scope.hasMore=false;
        }
        for (i = 0; i < response.data.length; i++) {
          $scope.luminarias.push(response.data[i]);
        }
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
          $scope.hasMore=false;
        } else {
          $scope.error.message = "No se pudo obtener la lista de luminarias";
          $scope.hasMore=false;
        }
        $scope.error.status = true;
      }
    });
    $scope.$broadcast('scroll.infiniteScrollComplete');
  }

})
  .controller('LuminaryCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.luminaria={nombre:"",latitud:"",longitud:""};
    $scope.mensaje="";
    $scope.showform=true;
    service="upd";
    $scope.error={"message":"", "status":false};

    if($stateParams.id=="new"){
      service="new";
    }else {
      Server.get("luminaria/" + $stateParams.id, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.luminaria = response.data;
        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
          } else {
            $scope.error.message = "No se pudo obtener los datos del distrito";
          }
          $scope.error.status = true;
        }
      });
    }


    $scope.save=function(){
      if(service=="new"){
        return insert();
      }else{
        return update();
      }
    }


    function insert(){
      Server.insert("luminaria/"+service,$scope.luminaria, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar la luminaria";
          }
          $scope.error.status=true;
        }
      });
    }

    function update(){
      Server.update("luminaria/"+service,$scope.luminaria, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar la luminaria";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.cancel=function(){
      $ionicHistory.goBack();
    }

  });
