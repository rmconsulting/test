controllers.controller('ConcentratorsCtrl', function($scope, Server, CONSTANTS) {
  $scope.concentradores = [];
  $scope.error={"message":"", "status":false};
  $scope.hasMore=true;
  $scope.page=-1;
  $scope.showformNav=false


  load();

  $scope.del=function(concentrador){
    Server.update("concentrador/del",concentrador, function(response) {
      if (response.operacion == "OK") {
        $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
        var index = $scope.concentradores.indexOf(concentrador);
        $scope.concentradores.splice(index, 1);
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo borrar el concentrador";
        }
        $scope.error.status=true;
      }
    });
  }

  $scope.moreDataCanBeLoaded=function(){
      return $scope.hasMore;
  }



  $scope.loadMoreData=function() {
    load();
  }

function load(){
    $scope.page+=1;
    var from = ($scope.page*CONSTANTS.LIMITE)+1;

    Server.get("concentrador/"+from+"/"+CONSTANTS.LIMITE, function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length<CONSTANTS.LIMITE){
          $scope.hasMore=false;
        }
        for (i = 0; i < response.data.length; i++) {
          $scope.concentradores.push(response.data[i]);
        }
        $scope.showformNav=true;
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
          $scope.hasMore=false;
        } else {
          $scope.error.message = "No se pudo obtener la lista de concentradores";
          $scope.hasMore=false;
        }
        $scope.error.status = true;
      }
    });
    $scope.$broadcast('scroll.infiniteScrollComplete');
  }

})
  .controller('ConcentratorCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.concentrador={nombre:""};
    $scope.mensaje="";
    $scope.showform=true;
    $scope.showformNav=false;
    $scope.hasMore=true;
    $scope.page=0;
    $scope.puntosdeluz=[];
    //Para manejar los puntos de luz disponibles
    $scope.availables=[];
    service="upd";
    $scope.error={"message":"", "status":false};

    if($stateParams.id=="new"){
      service="new";
    }else {
      Server.get("concentrador/" + $stateParams.id, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.concentrador = response.data;
          $scope.loadMoreData();
        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
          } else {
            $scope.error.message = "No se pudo obtener los datos del concentrador";
          }
          $scope.error.status = true;
        }
      });
    }

    $scope.moreDataCanBeLoaded=function(direccion){
      return $scope.hasMore;
    }

    $scope.delpdl=function(pdl) {
      pdl.concentrador=null;
      Server.update("puntodeluz/upd",pdl, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Registro borrado: ' + response.data.nombre + "";
          var index = $scope.puntosdeluz.indexOf(pdl);
          $scope.puntosdeluz.splice(index, 1);
          $scope.availables.push(pdl);
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo quitar el pdl del concentrador";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.addpdl=function(pdl) {
        Server.post("puntodeluz/set/"+$scope.concentrador.id,pdl, function(response) {
         if (response.operacion == "OK") {
           var index = $scope.availables.indexOf(pdl);
           $scope.availables.splice(index, 1);
           $scope.puntosdeluz.push(pdl);
         }else{
           if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
           }else{
            $scope.error.message="No se pudo agregar el punto de luz al concentrador";
           }
         $scope.error.status=true;
         }
       });
    }

    $scope.loadMoreData=function(direccion) {
      if(direccion=='sig') {
        $scope.page += 1;
      }else{
        if($scope.page>0){
          $scope.page -=1;
        }
      }
      var from = ($scope.page*CONSTANTS.LIMITE)+1;

      Server.get("puntodeluz/"+ $stateParams.id+ "/" +from+"/"+CONSTANTS.LIMITE, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          if(response.data.length<CONSTANTS.LIMITE){
            $scope.hasMore=false;
          }else{
            $scope.showformNav=true;
          }
          if(response.data.length>0){
            $scope.puntosdeluz=response.data;
          }

        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
            $scope.hasMore=false;
          } else {
            $scope.error.message = "No se pudo obtener la lista de puntos de luz del concentrador";
            $scope.hasMore=false;
          }
          $scope.error.status = true;
        }
      });

      Server.get("puntodeluz/avl", function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          if(response.data.length<CONSTANTS.LIMITE){
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
            $scope.error.message = "No se pudo obtener la lista de puntos de luz disponibles";
            $scope.hasMore=false;
          }
          $scope.error.status = true;
        }
      });
    }

    $scope.save=function(){
      $scope.showformNav=false;
      if(service=="new"){
        return insert();
      }else{
        return update();
      }
    }


    function insert(){
      Server.insert("concentrador/"+service,$scope.concentrador, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el concentrador";
          }
          $scope.error.status=true;
        }
      });
    }

    function update(){
      Server.update("concentrador/"+service,$scope.concentrador, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados nombre: ' + response.data.nombre;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el concentrador";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.cancel=function(){
      $ionicHistory.goBack();
    }

  });
