// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic','googlechart','starter.controllers','starter.services', 'starter.directives', 'pascalprecht.translate', 'ionic-multiselect'])

  .constant('ApiEndpoint', {
    url: 'http://localhost:8100/api'
  })
  .constant('availableLanguages',['en-us', 'es-ar'])
  .constant('defaultLanguage', 'es-ar')
  .constant('CONSTANTS', {
    LIMITE: 10
  })



.run(function($ionicPlatform) {
    function setLanguage(){
      //Ver como hacer para setear el lenguaje
      $translate.use(defaultLanguage);
    }
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})
  .config(function(multiselectProvider) {
    multiselectProvider.setTemplateUrl('templates/multi/item-template.html');
    multiselectProvider.setModalTemplateUrl('templates/multi/modal-template.html');
  })
  .config(function($translateProvider, defaultLanguage){
    $translateProvider.useStaticFilesLoader({"prefix":"i18n/", "suffix":".json"});

    $translateProvider.determinePreferredLanguage();
   // $translateProvider.preferredLanguage(defaultLanguage);
})
.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })
    .state('app.home', {
      url: '/home',
      cache: false,
      views: {
        'menuContent': {
          templateUrl: 'templates/home.html',
          controller: 'HomeCtrl'
        }
      }
    })
    .state('app.account',{
      url: '/account',
        views: {
        'menuContent': {
          templateUrl: 'templates/cuenta.html',
            controller: 'CuentaCtrl'
        }
      }
    })
    .state('app.login', {
      url: '/login',
      views: {
        'menuContent': {
          templateUrl: 'templates/login.html'
        }
      }
    })
  .state('app.search', {
    url: '/search',
    views: {
      'menuContent': {
        templateUrl: 'templates/search.html'
      }
    }
  })
    .state('app.luminaries', {
      cache: false,
      url: '/luminaries',
      views: {
        'menuContent': {
          templateUrl: 'templates/luminaries.html',
          controller: 'LuminariesCtrl'
        }
      }
    })
    .state('app.mapa', {
      cache: false,
      url: '/mapa',
      views: {
        'menuContent': {
          templateUrl: 'templates/mapa.html',
          controller: 'MapaCtrl'
        }
      }
    })
    .state('app.grafico', {
      cache: false,
      url: '/grafico',
      views: {
        'menuContent': {
          templateUrl: 'templates/chart.html',
          controller: 'ChartCtrl'
        }
      }
    })
    .state('app.concentrators', {
      cache: false,
      url: '/concentrators',
      views: {
        'menuContent': {
          templateUrl: 'templates/concentrators.html',
          controller: 'ConcentratorsCtrl'
        }
      }
    })
    .state('app.singconc', {
      cache: false,
      url: '/concentrators/:id',
      views: {
        'menuContent': {
          templateUrl: 'templates/concentrator.html',
          controller: 'ConcentratorCtrl'
        }
      }
    })
    .state('app.singlumi', {
      cache: false,
      url: '/luminaries/:id',
      views: {
        'menuContent': {
          templateUrl: 'templates/luminary.html',
          controller: 'LuminaryCtrl'
        }
      }
    })
    .state('app.users', {
      cache: false,
      url: '/users',
      views: {
        'menuContent': {
          templateUrl: 'templates/users.html',
          controller: 'UsersCtrl'
        }
      }
    })
    .state('app.pdls', {
      cache: false,
      url: '/pdl',
      views: {
        'menuContent': {
          templateUrl: 'templates/puntos.html',
          controller: 'PdlsCtrl'
        }
      }
    })
    .state('app.pdl', {
      cache: false,
      url: '/pdl/:id',
      views: {
        'menuContent': {
          templateUrl: 'templates/punto.html',
          controller: 'PdlCtrl'
        }
      }
    })
    .state('app.ctrls', {
      cache: false,
      url: '/controladores',
      views: {
        'menuContent': {
          templateUrl: 'templates/controladores.html',
          controller: 'ControladoresCtrl'
        }
      }
    })
    .state('app.ctrl', {
      cache: false,
      url: '/controladores/:id',
      views: {
        'menuContent': {
          templateUrl: 'templates/controlador.html',
          controller: 'ControladorCtrl'
        }
      }
    })
    .state('app.user', {
      cache: false,
      url: '/users/:id',
      views: {
        'menuContent': {
          templateUrl: 'templates/user.html',
          controller: 'UserCtrl'
        }
      }
    })
    .state('app.districts', {
      cache: false,
      url: '/districts',
      views: {
        'menuContent': {
          templateUrl: 'templates/districts.html',
          controller: 'DistrictsCtrl'
        }
      }
    })

  .state('app.single', {
    url: '/districts/:id',
    views: {
      'menuContent': {
        templateUrl: 'templates/district.html',
        controller: 'DistrictCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/login');
});
