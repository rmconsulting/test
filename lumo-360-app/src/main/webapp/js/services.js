angular.module('starter.services', [])

  .factory('Server', ['$http', function ($http) {
    //La variable que mantiene la base de datos a la que se accede
    var http_database='/api';

    function header(){
      return {'Authorization': 'xxxyyyzzz'};
    }

    return {
      find: function(urlCouch) {
        return $http({
          method: 'GET',
          headers: header(),
          url: http_database + urlCouch
        }) .success(function (response){
            var array=response.data;
            return array;
          }).error(function (data,status,header,config){
            alert('dd ' + status);
          });
      },
      get: function(urlCouch, callback) {
        return $http({
          method: 'GET',
          headers: header(),
          url: http_database + '/' + urlCouch
        })
          .success(function (data, status, headers, config) {
            return callback({operacion:'OK', data:data});
          })
          .error(function (data, status, headers, config) {
            return callback({operacion:'ERROR',url:config.url, headers:headers, status:status});
          });
      },
      getByID: function(documentID, callback) {
        return $http({
          method: 'GET',
          headers: header(),
          url: http_database + '/' +  documentID
        })
          .success(function (data, status, headers, config) {
            return callback({operacion:'OK', data:data});
          })
          .error(function (data, status, headers, config) {
            return callback({operacion:'ERROR',url:config.url, headers:headers, status:status, id:documentID});
          });
      },
      post: function(servicio, jsonToSave,callback) {
        return $http({
          method: 'POST',
          headers: header(),
          url: http_database + '/' + servicio,
          data: jsonToSave
        })
          .success(function (data, status, headers, config) {  //Para convertir el json a string angular.toJson(jsonToSave, false);
            if(status==204) { //para marcar que no hay datos disponibles
              callback({operacion: 'ERROR', json: jsonToSave, data: data, status: status});
            }else{
              callback({operacion: 'OK', json: jsonToSave, data: data, status: status});
            }
          })
          .error(function (data, status, headers, config) {
            callback({operacion:'ERROR',url:config.url,json:jsonToSave,datasaved:data, status:status});
          });
      },
      put: function(servicio, jsonToSave,callback) {
        return $http({
          method: 'PUT',
          headers: header(),
          url: http_database + servicio + '/' + jsonToSave._id,
          data: jsonToSave
        })
          .success(function (data, status, headers, config) {  //Para convertir el json a string angular.toJson(jsonToSave, false);
            callback({operacion:'OK',json:jsonToSave,datasaved:data, status:status});
          })
          .error(function (data, status, headers, config) {
            callback({operacion:'ERROR',url:config.url,json:jsonToSave,datasaved:data, status:status});
          });
      },
      insert: function(servicio, jsonToSave,callback) {
        return $http({
          method: 'POST',
          headers: header(),
          url: http_database + '/' + servicio,
          data: jsonToSave
        })
          .success(function (data, status, headers, config) {  //Para convertir el json a string angular.toJson(jsonToSave, false);
            callback({operacion:'OK',json:jsonToSave,data:data, status:status});
          })
          .error(function (data, status, headers, config) {
            callback({operacion:'ERROR',url:config.url,json:jsonToSave,datasaved:data, status:status});
          });
      },
      update: function(servicio, jsonToSave,callback) {
        return $http({
          method: 'PUT',
          headers: header(),
          url: http_database + "/" + servicio,
          data: jsonToSave
        })
          .success(function (data, status, headers, config) {  //Para convertir el json a string angular.toJson(jsonToSave, false);
            callback({operacion:'OK',json:jsonToSave,data:data, status:status});
          })
          .error(function (data, status, headers, config) {
            callback({operacion:'ERROR',url:config.url,json:jsonToSave,data:data, status:status});
          });
      }
    };
  }]);

