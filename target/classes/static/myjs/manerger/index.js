/**
 * 路由配置
 */
var app = angular.module('indexApp', ['ngRoute']); 
app.config(['$routeProvider', function($routeProvider){
    $routeProvider
    .when('/',{ templateUrl: 'page/manerger/userManerger.html',controller: 'userManergerController'})
    .when('/userManerger',{ templateUrl: 'page/manerger/userManerger.html',controller: 'userManergerController'})
    .when('/roleManerger',{ templateUrl: 'page/manerger/roleManerger.html',controller: 'roleManergerController'})
    .when('/menuManerger',{ templateUrl: 'page/manerger/menuManerger.html',controller: 'menuManergerController'})
    .when('/permissionManerger',{ templateUrl: 'page/manerger/permissionManerger.html',controller: 'permissionManergerController'})
    
    .otherwise({redirectTo:'/'});
}]);