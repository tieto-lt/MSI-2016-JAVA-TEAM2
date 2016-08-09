//Main module which gets required 'module = require('main_module')' in all custom written angular modules
require('angular');
require('angular-ui-bootstrap');
require('angular-animate');
require('angular-touch');
require('angular-ui-router');
require('angular-messages');
require('angular-cookies');
require('angular-jwt');
require("bootstrap/dist/css/bootstrap.css");

var _module = angular.module('AngularSpringRestDemo', [ 'ngAnimate', 'ngTouch', 'ui.bootstrap', 'ui.router', 'ngMessages', 'ngCookies', 'angular-jwt']);

_module.config(['$httpProvider', function($httpProvider) {
   $httpProvider.interceptors.push('myInterceptor');
}]);

module.exports = _module;
