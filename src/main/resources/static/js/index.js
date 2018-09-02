var app = angular.module('indexApp', ['ngRoute']); 
app.config(['$routeProvider', function($routeProvider){
    $routeProvider
    .when('/',{template:'这是首页页面'})
    .when('/demand_apply',{ templateUrl: 'page/business/demand_apply.html',controller: 'demandApplyController'})
    .when('/demand_rev',{templateUrl: 'page/business/demand_rev.html',controller: 'demandRevController'})
    .when('/demand_query',{templateUrl: 'page/business/demand_query.html',controller: 'demandQueryController'})
    .when('/demand_apply_review',{templateUrl: 'page/business/demand_apply_review.html',controller: 'demandApplyReviewController'})
    .when('/instore_apply',{templateUrl: 'page/business/instore_apply.html',controller: 'instoreApplyController'})
    .when('/instore_review',{templateUrl: 'page/business/instore_review.html',controller: 'instoreReviewController'})
    .when('/outstore',{templateUrl: 'page/business/outstore.html',controller: 'outstoreController'})
    .when('/purch_apply_review',{templateUrl: 'page/business/purch_apply_review.html',controller: 'purchApplyReviewController'})
    .when('/purch_apply',{templateUrl: 'page/business/purch_apply.html',controller: 'purchApplyController'})
    .when('/purch_query',{templateUrl: 'page/business/purch_query.html',controller: 'purchQueryController'})
    .when('/upload',{templateUrl: 'page/photo/upload.html'})
    .when('/showUpload',{templateUrl: 'page/photo/showPhoto.html'})
    
    .otherwise({redirectTo:'/'});
}]);