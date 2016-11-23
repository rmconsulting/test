controllers.controller('CuentaCtrl', function($scope, $translate, Server) {
  $scope.error = {"message": "", "status": false};
  $scope.ok = {"message": "", "status": false};
  $scope.showform=false;
  $scope.showpref=false;
  $scope.securityToken={"user":"", "pass":"", "newPassword":""};
  $scope.passwordVerify="";
  $scope.error={"message":"", "status":false};
  $scope.language={selected:0};

  $scope.clearError=function(){
    $scope.error={"message":"", "status":false};
  }
  $scope.clearMessage=function(){
    $scope.ok = {"message": "", "status": false};
  }
  $scope.showPass=function(){
    $scope.showform=!$scope.showform;
  }

  $scope.showPreferences=function(){
    $scope.showpref=!$scope.showpref;
  }

  $scope.chgLang=function(){
    alert(' -- > '+$scope.language.selected + "----" +$translate.language);
    $translate.use($scope.language.selected);
  }

  $scope.accept=function(){
    $scope.securityToken.user=$scope.usertoken.user.username;
    Server.post("change/pwd", $scope.securityToken, function(response){
      if(response.operacion=="OK"){
        $scope.ok.message="Clave cambiada con exito";
        $scope.ok.status=true;
        return;
      }else{
        if(response.status==500){
          $scope.error.message="Problemas de conexion, intente en unos minutos";
        }else{
          if(response.status==204){
            $scope.error.message="El password actual no es correcto";
          }else{
            $scope.error.message="Se produjo un error interno";
          }
        }
        $scope.error.status=true;
      }
    });
  }

  $scope.cancel=function(){
    $scope.securityToken={"user":"", "pass":"", "newPassword":""};
    $scope.showform=false;
  }
});
