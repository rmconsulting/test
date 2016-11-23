controllers.controller('DistrictsCtrl', function($scope, Server) {
    $scope.personas = [];
    $scope.error={"message":"", "status":false};

    $scope.del=function(designer){
      Server.update("distrito/del",designer, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Registro borrado: ' + response.data.nombre + ' cuit:' + response.data.cuit;
          var index = $scope.personas.indexOf(sector);
          $scope.personas.splice(index, 1);
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo borrar el diseñador";
          }
          $scope.error.status=true;
        }
      });
    }

    Server.get("distrito", function(response) {
      if (response.operacion == "OK") {
        //compatibilidad
        for (i=0; i< response.data.length; i++) {
          $scope.personas.push(response.data[i]);
        }
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo obtener la lista de diseñadores";
        }
        $scope.error.status=true;
      }
    });

  })

  .controller('DistrictCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.distrito={concentradores:[]};
    $scope.concentradores=[];
    $scope.mensaje="";
    $scope.showform=true;
    $scope.error={"message":"", "status":false};
    service="upd";

    $scope.pais = {selected: "None"};
    $scope.paises = [{id:"None", nombre:"None"}];
    $scope.estado = {selected: "None"};
    $scope.estados = [{id:"None", nombre:"None"}];
    $scope.ciudad = {selected: "None"};
    $scope.ciudades = [{id:"None", nombre:"None"}];

    $scope.loadEstado=function(idPais){
      if(idPais!='None') {
        Server.get("pais/" + idPais + "/estado", function (response) {
          if (response.operacion == "OK") {
            //compatibilidad
            $scope.estados = [{id:"None", nombre:"None"}];
            for (i=0; i< response.data.length; i++) {
              $scope.estados.push(response.data[i]);
            }
          } else {
            if (response.status == 500) {
              $scope.error.message = "Problemas de conexion, intente en unos minutos";
            } else {
              $scope.error.message = "No se pudo obtener los datos de los estados";
            }
            $scope.error.status = true;
          }
        });
      }else{
        alert('Debe seleccionar un pais valido');
      }
    };

    $scope.loadCiudad=function(idEstado){
      if(idEstado!='None') {
        Server.get("estado/" + idEstado + "/ciudad", function (response) {
          if (response.operacion == "OK") {
            $scope.ciudades = [{id:"None", nombre:"None"}];
            for (i = 0; i < response.data.length; i++) {
              $scope.ciudades.push(response.data[i]);
            }
          } else {
            if (response.status == 500) {
              $scope.error.message = "Problemas de conexion, intente en unos minutos";
            } else {
              $scope.error.message = "No se pudo obtener los datos del distrito";
            }
            $scope.error.status = true;
          }
        });
      }else{
        alert('Debe seleccionar un estado valido');
      }
    }

    //Carga de los paises
    Server.get("pais", function(response) {
      if (response.operacion == "OK") {
        //compatibilidad
        $scope.paises = [{id:"None", nombre:"None"}];
        for (i=0; i< response.data.length; i++) {
          $scope.paises.push(response.data[i]);
        }
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo obtener la lista de paises";
        }
        $scope.error.status=true;
      }
    });
    //


    Server.get("concentrador/avl", function(response) {
      if (response.operacion == "OK") {
        //compatibilidad
        for (i=0; i< response.data.length; i++) {
          $scope.concentradores.push(response.data[i]);
        }
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo obtener la lista de concentradores";
        }
        $scope.error.status=true;

      }
    });

    if($stateParams.id=="new"){
      service="new";
    }else{
      Server.get("distrito/"+$stateParams.id, function(response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.distrito = response.data;
          $scope.pais.selected=$scope.distrito.ciudad.estado.pais.id;
          $scope.estado.selected=$scope.distrito.ciudad.estado.id;
          $scope.ciudad.selected=$scope.distrito.ciudad.id;
          $scope.loadEstado($scope.pais.selected);
          $scope.loadCiudad($scope.estado.selected);
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


    $scope.addSector=function(sector){
      var index = $scope.distrito.concentradores.indexOf(sector);
      if(! exist(sector.id)){
        $scope.distrito.concentradores.push(sector);
      }
    }

    function exist(id){
      for(i=0; i< $scope.distrito.concentradores.length;i++){
        if($scope.distrito.concentradores[i].id==id){
          return true;
        }
      }
      return false;
    }

    $scope.delSector=function(sector){
      var index = $scope.distrito.concentradores.indexOf(sector);
      $scope.distrito.concentradores.splice(index, 1);
    }

    $scope.save=function(){
      if(service=="new"){
        return insert();
      }else{
        return update();
      }
    }

    function insert(){
      Server.insert("distrito/"+service,$scope.distrito, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados Nombre: ' + response.data.nombre;;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el distrito";
          }
          $scope.error.status=true;
        }
      });
    }

    function update(){
      Server.update("distrito/"+service,$scope.distrito, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados Nombre: ' + response.data.nombre;;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el distrito";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.cancel=function(){
      $ionicHistory.goBack();
    }

  });
