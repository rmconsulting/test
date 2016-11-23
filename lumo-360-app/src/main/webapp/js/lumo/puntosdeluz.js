controllers.controller('PdlsCtrl', function($scope, Server) {
  $scope.puntos = [];
  $scope.error={"message":"", "status":false};
  $scope.hasMore=true;
  $scope.page=-1;
  var limit=20;

  $scope.del=function(pdl){
    Server.update("puntodeluz/del",pdl, function(response) {
      if (response.operacion == "OK") {
        $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
        var index = $scope.puntos.indexOf(pdl);
        $scope.puntos.splice(index, 1);
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo borrar el punto de luz";
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

    Server.get("puntodeluz/"+from+"/"+limit, function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length<limit){
          $scope.hasMore=false;
        }
        for (i = 0; i < response.data.length; i++) {
          $scope.puntos.push(response.data[i]);
        }
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
          $scope.hasMore=false;
        } else {
          $scope.error.message = "No se pudo obtener la lista de puntos de luz";
          $scope.hasMore=false;
        }
        $scope.error.status = true;
      }
    });
    $scope.$broadcast('scroll.infiniteScrollComplete');
  }

})
  .controller('PdlCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.punto={nombre:""};
    $scope.mensaje="";
    $scope.showform=true;
    $scope.showformNav=false;
    $scope.hasMore=true;
    $scope.page=0;
    $scope.controladores=[];
    //Controladores disponibles
    $scope.availables=[];
    var limit=10;
    service="upd";
    $scope.error={"message":"", "status":false};

    if($stateParams.id=="new"){
      service="new";
    }else {
      Server.get("puntodeluz/" + $stateParams.id, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.punto = response.data;
          $scope.loadMoreData();
        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
          } else {
            $scope.error.message = "No se pudo obtener los datos del punto de luz";
          }
          $scope.error.status = true;
        }
      });
    }

    $scope.moreDataCanBeLoaded=function(direccion){
      return $scope.hasMore;
    }

    $scope.delcontrolador=function(controlador) {
      controlador.puntoLuz=null;
      Server.update("controlador/upd",controlador, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
          var index = $scope.controladores.indexOf(controlador);
          $scope.controladores.splice(index, 1);
          $scope.availables.push(controlador);
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo quitar el controlador del punto de luz";
          }
          $scope.error.status=true;
        }
      });
    }
    $scope.addcontrolador=function(controlador) {
      if(angular.isUndefined($scope.punto.id)){
        alert('Debe guardar primero el punto de luz');
      }else {
        Server.post("controlador/set/" + $scope.punto.id, controlador, function (response) {
          if (response.operacion == "OK") {
            var index = $scope.availables.indexOf(controlador);
            $scope.availables.splice(index, 1);
            $scope.controladores.push(controlador);
          } else {
            if (response.status == 500) {
              $scope.error.message = "Problemas de conexion, intente en unos minutos";
            } else {
              $scope.error.message = "No se pudo agregar el punto de luz al concentrador";
            }
            $scope.error.status = true;
          }
        });
      }
    }
    $scope.loadMoreData=function(direccion) {
      if(direccion=='sig') {
        $scope.page += 1;
      }else{
        if($scope.page>0){
          $scope.page -=1;
        }
      }
      var from = ($scope.page*limit)+1;

      Server.get("controlador/"+ $stateParams.id+ "/" +from+"/"+limit, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          if(response.data.length<limit){
            $scope.hasMore=false;
          }else{
            $scope.showformNav=true;
          }
          if(response.data.length>0){
            $scope.controladores=response.data;
          }

        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
            $scope.hasMore=false;
          } else {
            $scope.error.message = "No se pudo obtener la lista de controladores para el punto de luz";
            $scope.hasMore=false;
          }
          $scope.error.status = true;
        }
      });
    }

    Server.get("controlador/avl", function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length<limit){
          $scope.hasMore=false;
        }else{
          $scope.showformNav=true;
        }
        if(response.data.length>0){
          $scope.availables=response.data;
        }

      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
          $scope.hasMore=false;
        } else {
          $scope.error.message = "No se pudo obtener la lista de controladores disponibles";
          $scope.hasMore=false;
        }
        $scope.error.status = true;
      }
    });
    $scope.save=function(){
      $scope.showformNav=false;
      if(service=="new"){
        return insert();
      }else{
        return update();
      }
    }


    function insert(){
      Server.insert("puntodeluz/"+service,$scope.punto, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el punto de luz";
          }
          $scope.error.status=true;
        }
      });
    }

    function update(){
      Server.update("puntodeluz/"+service,$scope.punto, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el punto de luz";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.cancel=function(){
      $ionicHistory.goBack();
    }

  });
