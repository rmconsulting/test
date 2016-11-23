controllers.controller('HomeCtrl', function($scope, Server) {
  $scope.error={"message":"", "status":false};
  $scope.pais = {selected: 0};
  $scope.paises = [{nombre:"None"}];
  $scope.estado = {selected: -1};
  $scope.estados = [];
  $scope.ciudad = {selected: -1};
  $scope.ciudades = [];

  $scope.$watch("ciudad.selected", function(newValue, oldValue){
    if(newValue!=-1) {

      if ($scope.ciudades.length > 0) {
        $scope.admin.ciudad.id = $scope.ciudades[$scope.ciudad.selected].id;
        $scope.admin.ciudad.nombre = $scope.ciudades[$scope.ciudad.selected].nombre;
        if ($scope.usertoken.user.root) {
          //no hago nada
        } else {
          $scope.admin.roles = $scope.ciudades[$scope.ciudad.selected].roles;
        }
      }
    }
  });


  if($scope.validateRol('user_admin')) {
    loadPais();
  }else{
      $scope.ciudades = [];
      Server.get("usercity/" + $scope.usertoken.user.id, function (response) {
        if (response.operacion == "OK") {
          $scope.ciudad = {selected: -1};
          $scope.ciudades = [];
          //$scope.ciudades = [{idciudad:"None", ciudad:"None"}];
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
    return;
  }

  $scope.setCiudad=function(){
    $scope.admin.ciudad.id=$scope.ciudades[$scope.ciudad.selected].id;
    $scope.admin.ciudad.nombre=$scope.ciudades[$scope.ciudad.selected].nombre;
    if($scope.usertoken.user.root){
      //no hago nada
    }else{
      $scope.admin.roles=$scope.ciudades[$scope.ciudad.selected].roles;
    }
  }

  $scope.loadEstado=function(idPais){
    $scope.ciudad = {selected: -1};
    $scope.ciudades = [];
    if(idPais!='None') {
      Server.get("pais/" + idPais + "/estado", function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.estado = {selected: -1};
          $scope.estados = [];
          for (var i=0; i< response.data.length; i++) {
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
    return;
  };

  $scope.loadCiudad=function(idEstado){
    if(idEstado!='None') {
      Server.get("estado/" + idEstado + "/ciudad", function (response) {
        if (response.operacion == "OK") {
          $scope.ciudades = [];
          for (var i = 0; i < response.data.length; i++) {
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
    return;
  }

  $scope.clearError=function(){
    $scope.error={"message":"", "status":false};
  }

  function loadPais() {

    $scope.estado = {selected: -1};
    $scope.estados = [];
    $scope.ciudad = {selected: 0};
    $scope.ciudades = [];

    Server.get("pais", function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        $scope.paises = [];
        for (i = 0; i < response.data.length; i++) {
          $scope.paises.push(response.data[i]);
        }
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
        } else {
          $scope.error.message = "No se pudo obtener la lista de paises";
        }
        $scope.error.status = true;
      }
    });
    return;
  }
});
