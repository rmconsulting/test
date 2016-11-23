var controllers = angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $state, $ionicModal, $timeout, $ionicHistory, Server) {
    //$scope.activeRol={selected:""};
    $scope.logged = false;
    $scope.usertoken={};
    $scope.admin={mode:false, ciudad:{id:-1,nombre:""}, roles:[]};
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
 // $scope.$on('$ionicView.enter', function(e) {
  //  $ionicHistory.clearCache();
  //  $ionicHistory.clearHistory();
 // });

  // Form data for the login modal
  $scope.loginData = {username:"", password:""};
  $scope.loginError={"message":"", "status":false};

    $scope.hasElectedCity=function(){
      return $scope.admin.ciudad.id!=-1;
    }

    // Perform the login action when the user submits the logout form
    $scope.doLogout = function() {
      $scope.logged = false;
      $scope.usertoken={};
      $scope.admin={mode:false, ciudad:{id:-1,nombre:""}, roles:[]};
      $scope.loginData = {username:"", password:""};
      $scope.loginError={"message":"", "status":false};

      $ionicHistory.clearCache();
      $ionicHistory.clearHistory();


      $ionicHistory.nextViewOptions({
        disableBack: true
      });
      $state.go('app.login');
    }

    function adminuser(){
      if($scope.admin.roles.length <= 0){
        return false;
      }else{
        var index = $scope.admin.roles.indexOf('user_admin')
        if(index<0){
          return false;
        }else{
          $scope.admin.mode=true;
          return true;
        }
      }
    }

    $scope.validateRol=function(rol) {

      if ($scope.logged) {
        if ($scope.usertoken.user.root) {
          $scope.admin.mode = true;
          return true;
        } else {
          if ($scope.admin.roles.length <= 0) {
            return false;
          } else {
            if (adminuser()) {
              return true;
            } else {
              var index = $scope.admin.roles.indexOf(rol);
              if (index < 0) {
                return false;
              } else {
                return true;
              }
            }
          }
        }
      }
    }

    $scope.notValidateRol=function(rol) {
      return !$scope.validateRol(rol);
    }

    $scope.clearError=function(){
      $scope.loginError.message="";
      $scope.loginError.status=false;
    }
  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    $scope.loginError.message="";
    $scope.loginError.status=false;

    var request={"user":$scope.loginData.username,"pass":$scope.loginData.password};
    var object = Server.post("login/new", request, function(response){
      if(response.operacion=="OK"){
        $scope.logged = true;
        $scope.usertoken=response.data;
        //adminuser();
        $ionicHistory.nextViewOptions({
          disableBack: true
        });
        $state.go('app.home');
        return;
      }else{
        if(response.status==500){
          $scope.loginError.message="Problemas de conexion, intente en unos minutos";
        }else{
          if(response.status==204){
            $scope.loginError.message="Usuario o password incorrecto";
          }else{
            $scope.loginError.message="Se produjo un error interno";
          }
        }
        $scope.loginError.status=true;
      }
    });
    return;
  };
});
