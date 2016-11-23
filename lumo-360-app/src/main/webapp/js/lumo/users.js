controllers.controller('UsersCtrl', function($scope, Server) {
  $scope.usuarios = [];
  $scope.error={"message":"", "status":false};
  $scope.mensaje="";


  findUsuarioByCiudad();

  function findUsuarioByCiudad() {
    var ciudadID = $scope.admin.ciudad.id;

    if($scope.admin.ciudad.nombre == "all"){
      ciudadID="all";
    }

    Server.get("usuario/ciudad/" + ciudadID, function (response) {
      if (response.operacion == "OK") {
        //compatibilidad
        if(response.data.length==0){
          $scope.error.message="No hay usuarios para " + $scope.admin.ciudad.nombre ;
          $scope.error.status = true;
        }else{
          for (i = 0; i < response.data.length; i++) {
            $scope.usuarios.push(response.data[i]);
          }
        }
      } else {
        if (response.status == 500) {
          $scope.error.message = "Problemas de conexion, intente en unos minutos";
        } else {
          $scope.error.message = "No se pudo obtener la lista de diseñadores";
        }
        $scope.error.status = true;
      }
    });
  }

  $scope.del=function(designer){
    Server.update("usuario/del",designer, function(response) {
      if (response.operacion == "OK") {
        $scope.mensaje='Registro borrado: ' + response.data.username;
        var index = $scope.usuarios.indexOf(designer);
        $scope.usuarios.splice(index, 1);
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

  $scope.clearError=function(){
    $scope.error={"message":"", "status":false};
  }

})

  .controller('UserCtrl', function($scope, $ionicHistory,$ionicScrollDelegate, $stateParams, Server) {
    $scope.usuario={"roles":[]};
    $scope.roles=[];
    $scope.selectedRoles=[];
    $scope.mensaje="";
    $scope.showform=true;
    $scope.profile={selected:-1};
    $scope.profiles=[{id:-1, nombre:" "}];

    $scope.error={"message":"", "status":false};
    $scope.service="upd";
    service="upd";

    Server.get("profile", function(response) {
      if (response.operacion == "OK") {
        //compatibilidad
        for (i=0; i< response.data.length; i++) {
          var profile = response.data[i];
          $scope.profiles.push(profile);
        }
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          $scope.error.message="No se pudo obtener la lista de profiles";
        }
        $scope.error.status=true;

      }
    });

    if($stateParams.id=="new"){
      service="new";
      $scope.service="new";
      loadRoles();
    }else {
      Server.get("usuario/" + $stateParams.id, function (response) {
        if (response.operacion == "OK") {
          //compatibilidad
          $scope.usuario = response.data;
          $scope.profile.selected=$scope.usuario.profile.id;

          for(var i=0; i<$scope.usuario.roles.length; i++){
            $scope.selectedRoles.push($scope.usuario.roles[i].role);
          }
          loadRoles();
        } else {
          if (response.status == 500) {
            $scope.error.message = "Problemas de conexion, intente en unos minutos";
          } else {
            $scope.error.message = "No se pudo obtener los datos del usuario";
          }
          $scope.error.status = true;
        }
      });
    }

    function loadRoles(){
      Server.get("rol", function(response) {
        if (response.operacion == "OK") {
          //compatibilidad
          for (i=0; i< response.data.length; i++) {
            var thevalue={id:response.data[i].id,name:response.data[i].name};
            var rol = response.data[i];
            var alreadyChecked=exist(rol.id);
            rol.checked=alreadyChecked;
            rol.original=thevalue;
            $scope.roles.push(rol);
          }
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo obtener la lista de roles";
          }
          $scope.error.status=true;
        }
      });
    }
    $scope.clearError=function(){
      $scope.error={"message":"", "status":false};
    }

    $scope.setProfile=function(){
      $scope.usuario.profile=$scope.profiles[$scope.profile.selected];
    }

    $scope.addSector=function(sectores){
      $scope.selectedRoles=[];
      for(var j=0; j<sectores.length; j++){
        if(sectores[j].checked){
          if(!exist(sectores[j].id)){
            $scope.selectedRoles.push(sectores[j].original);
          }
        }else{
          delSector(sectores[j].original);
        }
      }
      /*
      if(sector.checked){
        if(! exist(sector.id)){
          $scope.usuario.roles.push(sector.original);
        }
      }else{
        delSector(sector.original);
      }
      var index = $scope.usuario.roles.indexOf(sector);
      if(! exist(sector.id)){
        $scope.usuario.roles.push(sector);
      }*/
    }

    function exist(id){
      for(var i=0; i< $scope.selectedRoles.length;i++){
        if($scope.selectedRoles[i].id==id){
          return true;
        }
      }
      return false;
    }

    function delSector(sector){
      var index = $scope.selectedRoles.indexOf(sector);
      $scope.selectedRoles.splice(index, 1);
    }

    $scope.save=function(){
      $scope.usuario.roles=[];
      for(var i=0; i< $scope.selectedRoles.length;i++){
        var rol = $scope.selectedRoles[i];
        $scope.usuario.roles.push({role:{id:rol.id}, ciudad:{id:$scope.admin.ciudad.id}});
      }
      if(service=="new"){
        return insert();
      }else{
        return update();
      }
    }

    function insert(){
      Server.insert("usuario/"+service,$scope.usuario, function(response) {
        if (response.operacion == "OK") {
          //updateRoles();
          $scope.mensaje='Datos guardados Nombre: ' + response.data.name;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else {
          if (response.status == 409) {
            $scope.error.message = "Conflicto de datos, usuario ya existente";
          } else {
              if (response.status == 500) {
                $scope.error.message = "Problemas de conexion, intente en unos minutos";
              } else {
                $scope.error.message = "No se pudo guardar el usuario";
              }
          }
          $scope.error.status=true;
        }
      });

    }

    function updateRoles(){
      Server.insert("usuario/updrole/"+$scope.usuario+"/"+$scope.admin.ciudad.id,$scope.roles, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados Nombre: ' + response.data.name;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el usuario";
          }
          $scope.error.status=true;
        }
      });
    }
    function update(){
      Server.update("usuario/"+service,$scope.usuario, function(response) {
        if (response.operacion == "OK") {
          $scope.mensaje='Datos guardados Nombre: ' + response.data.name;
          $scope.showform=false;
          $ionicScrollDelegate.scrollTop();
        }else{
          if(response.status==500){
            $scope.error.message="Problemas de conexion, intente en unos minutos";
          }else{
            $scope.error.message="No se pudo guardar el usuario";
          }
          $scope.error.status=true;
        }
      });
    }

    $scope.cancel=function(){
      $ionicHistory.goBack();
    }
  });
