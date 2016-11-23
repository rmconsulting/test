controllers.controller('ControladoresCtrl', function($scope, Server) {
  $scope.controladores = [];
  $scope.error={"message":"", "status":false};
  $scope.hasMore=true;
  $scope.page=-1;
  var limit=20;

  $scope.del=function(controlador){
    Server.update("Controladores/del",controlador, function(response) {
      if (response.operacion == "OK") {
        $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
        var index = $scope.controladores.indexOf(controlador);
        $scope.controladores.splice(index, 1);
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo borrar el controlador";
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

    Server.get("controlador/"+from+"/"+limit, function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length<limit){
          $scope.hasMore=false;
        }
        for (i = 0; i < response.data.length; i++) {
          $scope.controladores.push(response.data[i]);
        }
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
          $scope.hasMore=false;
        } else {
          $scope.error.message = "No se pudo obtener la lista de controladores";
          $scope.hasMore=false;
        }
        $scope.error.status = true;
      }
    });
    $scope.$broadcast('scroll.infiniteScrollComplete');
  }

})
  .controller('ControladorCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.controlador={nombre:""};
    $scope.mensaje="";
    $scope.showform=true;
    $scope.showformNav=false;
    $scope.hasMore=true;
    $scope.page=0;
    $scope.luminarias=[];
    //Controladores disponibles
    $scope.availables=[];
    var limit=10;
    service="upd";
    $scope.error={"message":"", "status":false};

    if($stateParams.id=="new"){
      service="new";
    }else {
      Server.get("controlador/" + $stateParams.id, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.controlador = response.data;
          $scope.loadMoreData();
        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
          } else {
            $scope.error.message = "No se pudo obtener los datos del controlador de luz";
          }
          $scope.error.status = true;
        }
      });
    }

    $scope.moreDataCanBeLoaded=function(direccion){
      return $scope.hasMore;
    }

    $scope.delluminaria=function(luminaria) {
      $scope.luminaria.controlador=null;
      Server.update("luminaria/upd",luminaria, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
          var index = $scope.luminarias.indexOf(luminaria);
          $scope.luminarias.splice(index, 1);
          $scope.availables.push(luminaria);
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo quitar la luminaria del controlador";
          }
          $scope.error.status=true;
        }
      });
    }
    $scope.addluminaria=function(luminaria) {
      if(angular.isUndefined($scope.controlador.id)){
        alert('Debe guardar primero el controlador');
      }else {
        Server.post("controlador/set/" + $scope.controlador.id, controlador, function (response) {
          if (response.operacion == "OK") {
            var index = $scope.availables.indexOf(luminaria);
            $scope.availables.splice(index, 1);
            $scope.luminarias.push(luminaria);
          } else {
            if (response.status == 500) {
              $scope.error.message = "Problemas de conexion, intente en unos minutos";
            } else {
              $scope.error.message = "No se pudo agregar la luminaria al controlador";
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

    Server.get("luminaria/avl", function (response) {
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
      Server.insert("controlador/"+service,$scope.punto, function(response) {
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
      Server.update("controlador/"+service,$scope.punto, function(response) {
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
